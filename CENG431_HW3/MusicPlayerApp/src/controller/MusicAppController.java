package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import helpers.Song;
import helpers.SongGenres;
import helpers.User;
import model.MusicAppModel;
import views.AddRemoveSongPage;
import views.FollowUnfollowOtherUsersPage;
import views.LikeAndListenSongPage;
import views.LoginPage;
import views.NavigationPage;
import views.PlayListCreationPage;
import views.ProfilePage;
import views.StatisticsPage;
import views.ViewFollowedUsersPlaylistsPage;

public class MusicAppController {

	private LoginPage loginPage;
	private ProfilePage profilePage;
	private PlayListCreationPage playListCreationPage;
	private ViewFollowedUsersPlaylistsPage viewFollowedUsersPlaylistsPage;
	private NavigationPage navigationPage;
	private StatisticsPage statisticsPage;
	private LikeAndListenSongPage likeAndListenSongPage;
	private FollowUnfollowOtherUsersPage followUnfollowOtherUsersPage;
	private AddRemoveSongPage addRemoveSongPage;
	private MusicAppModel model;

	public MusicAppController(LoginPage loginPage, ProfilePage profilePage, PlayListCreationPage playListCreationPage,
			ViewFollowedUsersPlaylistsPage viewFollowedUsersPlaylistsPage, NavigationPage navigationPage,
			StatisticsPage statisticsPage, LikeAndListenSongPage likeAndListenSongPage,
			FollowUnfollowOtherUsersPage followUnfollowOtherUsersPage, AddRemoveSongPage addRemoveSongPage,
			MusicAppModel model) {

		this.loginPage = loginPage;
		this.profilePage = profilePage;
		this.playListCreationPage = playListCreationPage;
		this.viewFollowedUsersPlaylistsPage = viewFollowedUsersPlaylistsPage;
		this.navigationPage = navigationPage;
		this.statisticsPage = statisticsPage;
		this.likeAndListenSongPage = likeAndListenSongPage;
		this.followUnfollowOtherUsersPage = followUnfollowOtherUsersPage;
		this.addRemoveSongPage = addRemoveSongPage;
		this.model = model;

		this.loginPage.setController(this);
		this.profilePage.setController(this);
		this.playListCreationPage.setController(this);
		this.viewFollowedUsersPlaylistsPage.setController(this);
		this.navigationPage.setController(this);
		this.statisticsPage.setController(this);
		this.likeAndListenSongPage.setController(this);
		this.followUnfollowOtherUsersPage.setController(this);
		this.addRemoveSongPage.setController(this);
		this.model.setController(this);
	}

	public void loadXMLFile() {
		model.loadXMLFile();
	}

	public void navigateFromLoginToProfilePage(String loginUserName, String loginPassword) {

		ArrayList<User> userList = model.getUserList();
		boolean isLoggedIn = false;

		for (int i = 0; i < userList.size(); i++) {
			User anyUser = userList.get(i);

			String username = anyUser.getUserName();
			String password = anyUser.getPassword();

			if (username.equals(loginUserName) && password.equals(loginPassword)) {
				isLoggedIn = true;

				JTable followingUsersTable = profilePage.getFollowingUsersTable();
				JTable followerUsersTable = profilePage.getFollowerUsersTable();

				DefaultTableModel followingUsersTableModel = (DefaultTableModel) followingUsersTable.getModel();
				DefaultTableModel followerUsersTableModel = (DefaultTableModel) followerUsersTable.getModel();

				ArrayList<String> followingUsers = anyUser.getFollowingUsers();
				ArrayList<String> followerUsers = anyUser.getFollowerUsers();

				for (int j = 0; j < followingUsers.size(); j++) {
					followingUsersTableModel.addRow(new Object[] { followingUsers.get(j) });
				}

				for (int j = 0; j < followerUsers.size(); j++) {
					followerUsersTableModel.addRow(new Object[] { followerUsers.get(j) });
				}

				profilePage.setVisible(true);
				profilePage.setProfileNameLabel(loginUserName);
				loginPage.setVisible(false);
			}

		}

		if (!isLoggedIn) {
			JOptionPane.showMessageDialog(loginPage, "Your username and password does not match try again",
					"Invalid Login Info", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void createPlaylist() {

		String playlistType = playListCreationPage.getSelectedPlaylistType();
		String playlistName = playListCreationPage.getPlaylistName().getText();
		String profileName = profilePage.getProfileName();

		if (playlistType != null && !playlistName.equals("")) {
			// only in this case create a playlist
			// and save it into JSON file
			model.createPlaylist(playlistType, playlistName, profileName);
			JOptionPane.showMessageDialog(playListCreationPage,
					"You have created playlist with name " + playlistName + " successfully",
					"Playlist Created Successfully", JOptionPane.INFORMATION_MESSAGE);
		}

		else if (playlistType == null) {
			JOptionPane.showMessageDialog(playListCreationPage,
					"You haven't selected any playlist type, please select playlist type first",
					"Playlist Type Not Selected", JOptionPane.WARNING_MESSAGE);
		}

		else if (playlistName.equals("")) {
			JOptionPane.showMessageDialog(playListCreationPage,
					"You haven't entered playlist name, please enter a name for playlist", "Playlist Name Not Provided",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void addSong() {

		String songName = addRemoveSongPage.getSongNameTextField().getText();
		String playlistName = addRemoveSongPage.getPlaylistNameTextField().getText();

		String songTobeAdded = getSongTobeAddedOrRemoved(songName);

		if (songTobeAdded == null) {
			JOptionPane.showMessageDialog(addRemoveSongPage, "Song to be added does not exist in tracks.txt file",
					"Song Not Found", JOptionPane.WARNING_MESSAGE);
		}

		else {
			try {

				byte[] playlistData = Files.readAllBytes(Paths.get("playlists.json"));

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(playlistData);

				boolean isExists = model.checkForPlaylistExistence(objectMapper, rootNode, playlistName);

				if (!isExists) {
					JOptionPane.showMessageDialog(addRemoveSongPage,
							"Playlist with given name for song to be added does not exist", "Playlist Not Found",
							JOptionPane.WARNING_MESSAGE);
				}

				else { // case in which both song to be added exists in tracks.txt file and playlist
						// with given name exists in playlists.json file
					model.addSong(rootNode, objectMapper, songTobeAdded, playlistName);
				}

			} catch (IOException e) {
				System.err.println("Error has occured while processing playlists.json file");
				System.exit(0);
			}
		}

		addRemoveSongPage.getSongNameTextField().setText("");
		addRemoveSongPage.getPlaylistNameTextField().setText("");
	}

	public void removeSong() {

		String songName = addRemoveSongPage.getSongNameTextField().getText();
		String playlistName = addRemoveSongPage.getPlaylistNameTextField().getText();

		String songTobeRemoved = getSongTobeAddedOrRemoved(songName);

		if (songTobeRemoved == null) {
			JOptionPane.showMessageDialog(addRemoveSongPage, "Song to be removed does not exist in tracks.txt file",
					"Song Not Found", JOptionPane.WARNING_MESSAGE);
		}

		else {
			try {

				byte[] playlistData = Files.readAllBytes(Paths.get("playlists.json"));

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(playlistData);

				boolean isExists = model.checkForPlaylistExistence(objectMapper, rootNode, playlistName);

				if (!isExists) {
					JOptionPane.showMessageDialog(addRemoveSongPage,
							"Playlist with given name for song to be removed does not exist", "Playlist Not Found",
							JOptionPane.WARNING_MESSAGE);
				}

				else { // case in which both song to be removed exists in tracks.txt file and playlist
						// with given name exists in playlists.json file
						// TO DO put this part of code block inside the model as a public method
					model.removeSong(rootNode, objectMapper, songTobeRemoved, playlistName);
				}

			} catch (IOException e) {
				System.err.println("Error has occured while processing playlists.json file");
				System.exit(0);
			}
		}

		addRemoveSongPage.getSongNameTextField().setText("");
		addRemoveSongPage.getPlaylistNameTextField().setText("");
	}

	public void bringSongsOnPlaylist() {

		try {

			DefaultTableModel playlistSongsTable = (DefaultTableModel) profilePage.getPlaylistSongsTable().getModel();

			playlistSongsTable.setRowCount(0);
			String playlistName = profilePage.getPlaylistName().getText();
			byte[] playlistData = Files.readAllBytes(Paths.get("playlists.json"));
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(playlistData);

			boolean isPlaylistExists = model.checkForPlaylistExistence(objectMapper, rootNode, playlistName);

			if (playlistName.equals("")) {
				JOptionPane.showMessageDialog(profilePage, "You haven't provided any playlist name",
						"Playlist Name Not Provided", JOptionPane.WARNING_MESSAGE);
			}

			else if (!isPlaylistExists) {
				JOptionPane.showMessageDialog(profilePage, "Playlist with given name does not exist",
						"Playlist Not Found", JOptionPane.WARNING_MESSAGE);
			}

			else {
				ArrayList<Song> playlistSongs = model.bringSongsOnPlaylist(objectMapper, rootNode, playlistName);

				for (Song song : playlistSongs) {
					playlistSongsTable.addRow(new Object[] { song.getGenreId(), song.getTrackId(), song.getSong(),
							song.getArtist(), song.getDuration(), song.getPopularity(), song.getNumberOfLikes() });
				}

			}

		}

		catch (IOException e) {
			System.err.println("playlists.json file does not exist");
			System.exit(0);
		}

		profilePage.getPlaylistName().setText("");
	}

	public void bringSongsForGivenGenre() {

		DefaultTableModel songTable = (DefaultTableModel) likeAndListenSongPage.getSongTable().getModel();
		songTable.setRowCount(0);
		String genre = likeAndListenSongPage.getSongGenre().getText().toLowerCase();
		int songGenreToBeFetched = 0;

		if (SongGenres.ACOUSTIC.genre.equals(genre)) {
			songGenreToBeFetched = 1;
		}

		else if (SongGenres.INSTRUMENTAL.genre.equals(genre)) {
			songGenreToBeFetched = 2;
		}

		else if (SongGenres.ROCK.genre.equals(genre)) {
			songGenreToBeFetched = 3;
		}

		else if (SongGenres.HIPHOP.genre.equals(genre)) {
			songGenreToBeFetched = 4;
		}

		else if (SongGenres.JAZZ.genre.equals(genre)) {
			songGenreToBeFetched = 5;
		}

		else if (SongGenres.POP.genre.equals(genre)) {
			songGenreToBeFetched = 6;
		}

		else {
			JOptionPane.showMessageDialog(likeAndListenSongPage, "You have provided an invalid genre", "Invalid Genre",
					JOptionPane.WARNING_MESSAGE);
		}

		if (songGenreToBeFetched != 0) {

			ArrayList<Song> songsBelongToGivenGenre = model.bringSongsForGivenGenre(songGenreToBeFetched);

			for (Song song : songsBelongToGivenGenre) {
				songTable.addRow(new Object[] { song.getGenreId(), song.getTrackId(), song.getSong(), song.getArtist(),
						song.getDuration(), song.getPopularity(), song.getNumberOfLikes() });
			}

		}
	}

	public void likeSong() {

		int rowIndex = likeAndListenSongPage.getSongTable().getSelectedRow();

		if (rowIndex != -1) {
			DefaultTableModel songTable = (DefaultTableModel) likeAndListenSongPage.getSongTable().getModel();
			String songName = songTable.getValueAt(rowIndex, 2).toString();

			model.updateTracksFileAfterLikeOrListenSong(songName, 0);
			JOptionPane.showMessageDialog(likeAndListenSongPage, "You have liked the song " + songName,
					"Liked Song Sucessfully", JOptionPane.INFORMATION_MESSAGE);
			bringSongsForGivenGenre();
		}

		else {
			JOptionPane.showMessageDialog(likeAndListenSongPage, "You haven't selected a row in order to like a song",
					"Row Not Selected", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void listenSong() {

		int rowIndex = likeAndListenSongPage.getSongTable().getSelectedRow();

		if (rowIndex != -1) {
			DefaultTableModel songTableModel = (DefaultTableModel) likeAndListenSongPage.getSongTable().getModel();
			String songName = songTableModel.getValueAt(rowIndex, 2).toString();

			model.updateTracksFileAfterLikeOrListenSong(songName, 1);
			JOptionPane.showMessageDialog(likeAndListenSongPage, "You have listened the song " + songName,
					"Listened Song Sucessfully", JOptionPane.INFORMATION_MESSAGE);
			bringSongsForGivenGenre();
		}

		else {
			JOptionPane.showMessageDialog(likeAndListenSongPage, "You haven't selected a row in order to listen a song",
					"Row Not Selected", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void loadUserTable() {

		ArrayList<User> userList = model.getUserList();

		DefaultTableModel userTable = (DefaultTableModel) followUnfollowOtherUsersPage.getUserTable().getModel();
		userTable.setRowCount(0);

		for (User user : userList) {
			userTable.addRow(new Object[] { user.getUserName() });
		}
	}

	public void followUser() {

		int rowIndex = followUnfollowOtherUsersPage.getUserTable().getSelectedRow();
		String currentUser = profilePage.getProfileName();

		if (rowIndex != -1) {
			String userToBeFollowed = followUnfollowOtherUsersPage.getUserTable().getValueAt(rowIndex, 0).toString();

			if (currentUser.equals(userToBeFollowed)) {
				JOptionPane.showMessageDialog(followUnfollowOtherUsersPage, "You can not follow yourself",
						"Following Yourself Not Allowed", JOptionPane.WARNING_MESSAGE);
			}

			else {

				boolean isFollowerAdded = model.followUser(currentUser, userToBeFollowed);

				if (isFollowerAdded) {
					reloadFollowingAndFollowerUsersInProfilePage();
					JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
							"User with the name " + userToBeFollowed + " added to your following users",
							"Following User Added", JOptionPane.INFORMATION_MESSAGE);
				}

				else {
					JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
							"User with the name " + userToBeFollowed + " already exists in your following users",
							"Following User Already Exists", JOptionPane.WARNING_MESSAGE);
				}
			}

		}

		else {
			JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
					"You haven't selected row in order to follow the user", "Row Not Selected",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void unfollowUser() {

		int rowIndex = followUnfollowOtherUsersPage.getUserTable().getSelectedRow();
		String currentUser = profilePage.getProfileName();

		if (rowIndex != -1) {
			String userToBeUnFollowed = followUnfollowOtherUsersPage.getUserTable().getValueAt(rowIndex, 0).toString();

			if (currentUser.equals(userToBeUnFollowed)) {
				JOptionPane.showMessageDialog(followUnfollowOtherUsersPage, "You can not unfollow yourself",
						"Unfollowing Yourself Not Allowed", JOptionPane.WARNING_MESSAGE);
			}

			else {

				boolean isFollowerRemoved = model.unfollowUser(currentUser, userToBeUnFollowed);

				if (isFollowerRemoved) {
					reloadFollowingAndFollowerUsersInProfilePage();
					JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
							"User with the name " + userToBeUnFollowed + " removed from your following users",
							"Following User Removed", JOptionPane.INFORMATION_MESSAGE);
				}

				else {
					JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
							"User with the name " + userToBeUnFollowed + " does not exists in your following users",
							"Following User Does Not Exist", JOptionPane.WARNING_MESSAGE);
				}
			}

		}

		else {
			JOptionPane.showMessageDialog(followUnfollowOtherUsersPage,
					"You haven't selected row in order to unfollow the user", "Row Not Selected",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void setFollowedUsersTable() {

		ArrayList<User> users = model.getUserList();
		DefaultTableModel followedUsersTable = (DefaultTableModel) viewFollowedUsersPlaylistsPage
				.getFollowedUsersTable().getModel();
		followedUsersTable.setRowCount(0);
		String currentUser = profilePage.getProfileName();

		for (User user : users) {
			if (currentUser.equals(user.getUserName())) {
				ArrayList<String> followedUsers = user.getFollowingUsers();
				for (int i = 0; i < followedUsers.size(); i++) {
					followedUsersTable.addRow(new Object[] { followedUsers.get(i) });
				}

				break;
			}
		}
	}

	public void loadPlaylistsOfFollowedUser() {

		JTable follwedUsersTable = viewFollowedUsersPlaylistsPage.getFollowedUsersTable();

		int rowIndex = follwedUsersTable.getSelectedRow();

		if (rowIndex != -1) {

			try {
				DefaultTableModel playlistTable = (DefaultTableModel) viewFollowedUsersPlaylistsPage.getPlaylistsTable()
						.getModel();
				playlistTable.setRowCount(0);
				String followedUserName = follwedUsersTable.getValueAt(rowIndex, 0).toString();
				byte[] jsonFile = Files.readAllBytes(Paths.get("playlists.json"));
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(jsonFile);

				Iterator<JsonNode> nodeIterator = rootNode.iterator();

				while (nodeIterator.hasNext()) {
					JsonNode playlistNode = nodeIterator.next();
					String creatorName = playlistNode.get("creator_user_name").asText();

					if (creatorName.equals(followedUserName)) {
						String playlistName = playlistNode.get("playlist_name").asText();
						String playlistType = playlistNode.get("playlist_type").asText();

						playlistTable.addRow(new Object[] { playlistName, playlistType });
					}
				}
			}

			catch (IOException e) {
				System.err.println("Error has occured while processing playlists.json file");
				System.exit(0);
			}

		}

		else {
			JOptionPane.showMessageDialog(viewFollowedUsersPlaylistsPage,
					"You haven't selected a row for followed user", "Followed User Not Selected",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void loadSongsOfSelectedPlaylist() {

		JTable followedUserPlaylistsTable = viewFollowedUsersPlaylistsPage.getPlaylistsTable();
		int rowIndex = followedUserPlaylistsTable.getSelectedRow();

		if (rowIndex != -1) {
			DefaultTableModel songTable = (DefaultTableModel) viewFollowedUsersPlaylistsPage.getSongsTable().getModel();
			songTable.setRowCount(0);
			String playlisName = followedUserPlaylistsTable.getValueAt(rowIndex, 0).toString();
			ArrayList<Song> playlistSongsList = model.loadSongsOfSelectedPlaylist(playlisName);

			for (int i = 0; i < playlistSongsList.size(); i++) {
				Song playlistSong = playlistSongsList.get(i);
				songTable.addRow(new Object[] { playlistSong.getGenreId(), playlistSong.getTrackId(),
						playlistSong.getSong(), playlistSong.getArtist(), playlistSong.getDuration(),
						playlistSong.getPopularity(), playlistSong.getNumberOfLikes() });
			}
		}

		else {
			JOptionPane.showMessageDialog(viewFollowedUsersPlaylistsPage, "You haven't selected a row for playlist",
					"Playlist Not Selected", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void fillStatisticalInformation() {

		String mostLikedSong = model.getMostLikedSong();
		String mostPopularSong = model.getMostPopularSong();
		String mostFollowedUser = model.getMostFollowedUser();

		Map<String, Map<String, String>> shortestAndLongestPlaylistMap = model.getShortestAndLongestPlaylists();

		JLabel mostLikedSongLabel = statisticsPage.getMostLikedSong();
		mostLikedSongLabel.setText(mostLikedSong);

		JLabel mostPopularSongLabel = statisticsPage.getMostPopularSong();
		mostPopularSongLabel.setText(mostPopularSong);

		JLabel mostFollowedUserLabel = statisticsPage.getMostFollowedUser();
		mostFollowedUserLabel.setText(mostFollowedUser);

		DefaultTableModel shortestAngLongestPlaylistsTable = (DefaultTableModel) statisticsPage
				.getShortestAndLongestPlaylistsTable().getModel();
		shortestAngLongestPlaylistsTable.setRowCount(0);

		String longestPlaylistName = null;
		String longestPlaylistGenre = null;

		String shortestPlaylistName = null;
		String shortestPlaylistGenre = null;

		for (Map.Entry<String, Map<String, String>> mapEntry : shortestAndLongestPlaylistMap.entrySet()) {
			if (mapEntry.getKey().equals("Longest Playlist")) {
				for (String key : mapEntry.getValue().keySet()) {
					longestPlaylistName = key;
					longestPlaylistGenre = mapEntry.getValue().get(key);
				}
			}

			else if (mapEntry.getKey().equals("Shortest Playlist")) {
				for (String key : mapEntry.getValue().keySet()) {
					shortestPlaylistName = key;
					shortestPlaylistGenre = mapEntry.getValue().get(key);
				}
			}

		}

		shortestAngLongestPlaylistsTable.addRow(new Object[] { shortestPlaylistName, shortestPlaylistGenre,
				longestPlaylistName, longestPlaylistGenre });

	}

	public void setProfilePageToDefault() {
		JTextField playlistName = profilePage.getPlaylistName();
		playlistName.setText("");

		DefaultTableModel playlistTable = (DefaultTableModel) profilePage.getPlaylistSongsTable().getModel();
		playlistTable.setRowCount(0);
	}

	public void setPlaylistCreationPageToDefault() {
		JTextField playlistName = playListCreationPage.getPlaylistName();
		playlistName.setText("");

		ButtonGroup playlistButtonGroup = playListCreationPage.getPlaylistButtons();
		playlistButtonGroup.clearSelection();
	}

	public void setAddRemoveSongPageToDefault() {
		JTextField playlistName = addRemoveSongPage.getPlaylistNameTextField();
		playlistName.setText("");

		JTextField songName = addRemoveSongPage.getSongNameTextField();
		songName.setText("");
	}

	public void setLikeAndListenSongPageToDefault() {
		JTextField songGenre = likeAndListenSongPage.getSongGenre();
		songGenre.setText("");

		DefaultTableModel songTable = (DefaultTableModel) likeAndListenSongPage.getSongTable().getModel();
		songTable.setRowCount(0);
	}

	public void setViewFollowedUsersPageToDefault() {
		DefaultTableModel playlistTable = (DefaultTableModel) viewFollowedUsersPlaylistsPage.getPlaylistsTable()
				.getModel();
		playlistTable.setRowCount(0);

		DefaultTableModel songTable = (DefaultTableModel) viewFollowedUsersPlaylistsPage.getSongsTable().getModel();
		songTable.setRowCount(0);
	}

	public void setStatisticsPageToDefault() {
		DefaultTableModel shortestAndLongestPlaylistsTable = (DefaultTableModel) statisticsPage
				.getShortestAndLongestPlaylistsTable().getModel();
		shortestAndLongestPlaylistsTable.setRowCount(0);

	}

	public void moveToNavigationPage(JFrame currentPage) {
		currentPage.setVisible(false);
		navigationPage.setVisible(true);
	}

	public void moveFromNavigationPageToProfilePage() {
		navigationPage.setVisible(false);
		profilePage.setVisible(true);
	}

	public void moveFromNavigationPageToPlaylistCreationPage() {
		navigationPage.setVisible(false);
		playListCreationPage.setVisible(true);
	}

	public void moveFromNavigationPageToStatisticsPage() {
		navigationPage.setVisible(false);
		statisticsPage.setVisible(true);
	}

	public void moveFromNavigationPageToFollowedUsersPage() {
		navigationPage.setVisible(false);
		viewFollowedUsersPlaylistsPage.setVisible(true);
	}

	public void moveFromNavigationPageToLikeSongPage() {
		navigationPage.setVisible(false);
		likeAndListenSongPage.setVisible(true);
	}

	public void moveFromNavigationPageToFollowUnfollowOtherUsersPage() {
		navigationPage.setVisible(false);
		followUnfollowOtherUsersPage.setVisible(true);
	}

	public void moveFromNavigationPageToAddRemoveSongPage() {
		navigationPage.setVisible(false);
		addRemoveSongPage.setVisible(true);
	}

	public void giveSongAddedSuccessfullyMessage(String songName, String playlistName) {
		JOptionPane.showMessageDialog(addRemoveSongPage,
				"Song with name " + songName + " added to playlist with name " + playlistName + " successfully",
				"Song Added Successfully", JOptionPane.INFORMATION_MESSAGE);
	}

	public void giveSongRemovedSuccessfullyMessage(String songName, String playlistName) {
		JOptionPane.showMessageDialog(addRemoveSongPage,
				"Song with name " + songName + " removed from playlist with name " + playlistName + " successfully",
				"Song Removed Successfully", JOptionPane.INFORMATION_MESSAGE);
	}

	public void giveAdditionNotPossibleWarning() {

		JOptionPane.showMessageDialog(addRemoveSongPage, "Song to be added already exists in the playlist",
				"Addition of Song Not Possible", JOptionPane.WARNING_MESSAGE);
	}

	public void giveRemovalNotPossibleWarning() {
		JOptionPane.showMessageDialog(addRemoveSongPage, "Song to be removed does not exist in the playlist",
				"Removal of Song Not Possible", JOptionPane.WARNING_MESSAGE);
	}

	public void giveDuplicatePlaylistWarning() {
		JOptionPane.showMessageDialog(playListCreationPage, "Playlist with same name already exists",
				"Duplicate Playlist Name", JOptionPane.WARNING_MESSAGE);
	}

	public void reloadFollowingAndFollowerUsersInProfilePage() {

		ArrayList<User> userList = model.getUserList();

		for (int i = 0; i < userList.size(); i++) {
			User anyUser = userList.get(i);

			String username = anyUser.getUserName();
			String currentlyLoggedInUser = profilePage.getProfileName();

			if (username.equals(currentlyLoggedInUser)) {

				JTable followingUsersTable = profilePage.getFollowingUsersTable();
				JTable followerUsersTable = profilePage.getFollowerUsersTable();

				DefaultTableModel followingUsersTableModel = (DefaultTableModel) followingUsersTable.getModel();
				DefaultTableModel followerUsersTableModel = (DefaultTableModel) followerUsersTable.getModel();

				followingUsersTableModel.setRowCount(0);
				followerUsersTableModel.setRowCount(0);

				ArrayList<String> followingUsers = anyUser.getFollowingUsers();
				ArrayList<String> followerUsers = anyUser.getFollowerUsers();

				for (int j = 0; j < followingUsers.size(); j++) {
					followingUsersTableModel.addRow(new Object[] { followingUsers.get(j) });
				}

				for (int j = 0; j < followerUsers.size(); j++) {
					followerUsersTableModel.addRow(new Object[] { followerUsers.get(j) });
				}

			}
		}
	}

	private String getSongTobeAddedOrRemoved(String songName) {

		ArrayList<Song> songList = model.getSongList();

		for (Song song : songList) {
			if (song.getSong().equalsIgnoreCase(songName)) {
				return song.getSong();
			}
		}

		return null;
	}

}
