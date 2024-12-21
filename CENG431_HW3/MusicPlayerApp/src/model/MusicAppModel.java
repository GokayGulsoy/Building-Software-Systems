package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controller.MusicAppController;
import helpers.Song;
import helpers.User;

public class MusicAppModel {

	private MusicAppController controller;
	private ArrayList<User> userList;
	private ArrayList<Song> songList;
	private int numOfRegisteredPlaylists;

	public MusicAppModel() {
		this.controller = null;
		userList = new ArrayList<User>();
		songList = new ArrayList<Song>();
		numOfRegisteredPlaylists = 1;
		loadNumOfRegisteredPlaylistsFromJsonFile();
		loadTracksFromFile();
	}

	public void loadXMLFile() {

		// method that loads users from XML
		// file into userList of model
		userList.clear();

		try {

			// Creating a DocumentBuilder object
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			// Reading the XML file
			File xmlFile = new File("users.xml");

			// Parsing the XML file
			Document document = documentBuilder.parse(xmlFile);

			NodeList nodeList = document.getElementsByTagName("user");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element userNode = (Element) nodeList.item(i);

					String username = userNode.getElementsByTagName("username").item(0).getTextContent();
					String password = userNode.getElementsByTagName("password").item(0).getTextContent();

					User user = new User(username, password);

					Node followingUsersNode = userNode.getElementsByTagName("following_user_names").item(0);
					Element followingUsers = (Element) followingUsersNode;
					NodeList followingUsersList = followingUsers.getElementsByTagName("following_user");

					for (int j = 0; j < followingUsersList.getLength(); j++) {
						user.addFollowingUser(followingUsersList.item(j).getTextContent());
					}

					Node followerUsersNode = userNode.getElementsByTagName("follower_user_names").item(0);
					Element followerUsers = (Element) followerUsersNode;
					NodeList followerUsersList = followerUsers.getElementsByTagName("follower_user");

					for (int j = 0; j < followerUsersList.getLength(); j++) {
						user.addFollowerUser(followerUsersList.item(j).getTextContent());
					}

					// adding user to model's userList
					userList.add(user);
				}
			}
		}

		catch (Exception e) {
			System.out.println("Error occured while processing XML file");
			e.printStackTrace();
			System.exit(0);
		}

	}

	public boolean followUser(String currentUser, String userToBeFollowed) {

		boolean ableToAddFollowingUser = false;
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			// Reading the XML file
			File inputFile = new File("users.xml");

			// Parsing the XML Document
			Document document = documentBuilder.parse(inputFile);
			Element rootElement = document.getDocumentElement();

			NodeList users = rootElement.getElementsByTagName("user");

			boolean alreadyFollowed = false;
			for (int i = 0; i < users.getLength(); i++) {
				Node node = users.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element userNode = (Element) node;

					if (currentUser.equals(userNode.getElementsByTagName("username").item(0).getTextContent())) {
						Element followingUsersNode = (Element) userNode.getElementsByTagName("following_user_names")
								.item(0);
						NodeList followingUsersList = followingUsersNode.getElementsByTagName("following_user");

						for (int j = 0; j < followingUsersList.getLength(); j++) {

							String followingUserName = followingUsersList.item(j).getTextContent();
							if (followingUserName.equals(userToBeFollowed)) {
								alreadyFollowed = true;
							}
						}
					}

				}
			}

			if (!alreadyFollowed) {
				for (int i = 0; i < users.getLength(); i++) {
					Node node = users.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element userNode = (Element) node;
						String userName = userNode.getElementsByTagName("username").item(0).getTextContent();

						if (userName.equals(currentUser)) {
							Node followingUsers = userNode.getElementsByTagName("following_user_names").item(0);
							Element newFollowingUserElement = document.createElement("following_user");
							newFollowingUserElement.appendChild(document.createTextNode(userToBeFollowed));

							followingUsers.appendChild(newFollowingUserElement);
						}

						else if (userName.equals(userToBeFollowed)) {
							Node followerUsers = userNode.getElementsByTagName("follower_user_names").item(0);
							Element newFollowerUserElement = document.createElement("follower_user");
							newFollowerUserElement.appendChild(document.createTextNode(currentUser));

							followerUsers.appendChild(newFollowerUserElement);
						}

					}
				}

				// Creating transformer object
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				// Writing updated content to XML file
				DOMSource source = new DOMSource(document);
				FileOutputStream outputStream = new FileOutputStream(inputFile);
				StreamResult result = new StreamResult(outputStream);
				transformer.transform(source, result);

				userList.clear();
				loadXMLFile();
				ableToAddFollowingUser = true;
			}

		}

		catch (Exception e) {
			System.err.println("Error has occured while adding following user to XML file");
			System.exit(0);
		}

		return ableToAddFollowingUser;
	}

	public boolean unfollowUser(String currentUser, String userToBeUnFollowed) {

		boolean ableToRemoveFollowingUser = false;
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			// Reading the XML file
			File inputFile = new File("users.xml");

			// Parsing the XML Document
			Document document = documentBuilder.parse(inputFile);
			Element rootElement = document.getDocumentElement();

			NodeList users = rootElement.getElementsByTagName("user");

			boolean isFollowed = false;
			for (int i = 0; i < users.getLength(); i++) {
				Node node = users.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element userNode = (Element) node;
					Element followingUsersNode = (Element) userNode.getElementsByTagName("following_user_names")
							.item(0);
					NodeList followingUsersList = followingUsersNode.getElementsByTagName("following_user");

					for (int j = 0; j < followingUsersList.getLength(); j++) {

						String followingUserName = followingUsersList.item(j).getTextContent();
						if (followingUserName.equals(userToBeUnFollowed)) {
							isFollowed = true;
						}
					}

				}
			}

			if (isFollowed) {
				for (int i = 0; i < users.getLength(); i++) {
					Node node = users.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element userNode = (Element) node;
						String userName = userNode.getElementsByTagName("username").item(0).getTextContent();

						if (userName.equals(currentUser)) {
							Node followingUsers = userNode.getElementsByTagName("following_user_names").item(0);
							Element followingUserElementNode = (Element) followingUsers;
							NodeList followingUsersList = followingUserElementNode
									.getElementsByTagName("following_user");

							for (int j = 0; j < followingUsersList.getLength(); j++) {
								String userToBeRemoved = followingUsersList.item(j).getTextContent();

								if (userToBeRemoved.equals(userToBeUnFollowed)) {
									followingUsersList.item(j).getParentNode().removeChild(followingUsersList.item(j));
								}
							}

						}

						else if (userName.equals(userToBeUnFollowed)) {
							Node followerUsers = userNode.getElementsByTagName("follower_user_names").item(0);
							Element followerUserElementNode = (Element) followerUsers;
							NodeList followerUsersList = followerUserElementNode.getElementsByTagName("follower_user");

							for (int j = 0; j < followerUsersList.getLength(); j++) {
								String userToBeRemoved = followerUsersList.item(j).getTextContent();

								if (userToBeRemoved.equals(currentUser)) {
									followerUsersList.item(j).getParentNode().removeChild(followerUsersList.item(j));
								}
							}

						}

					}
				}

				// Creating transformer object
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				// Writing updated content to XML file
				DOMSource source = new DOMSource(document);
				FileOutputStream outputStream = new FileOutputStream(inputFile);
				StreamResult result = new StreamResult(outputStream);
				transformer.transform(source, result);

				userList.clear();
				loadXMLFile();
				ableToRemoveFollowingUser = true;
			}

		}

		catch (Exception e) {
			System.err.println("Error has occured while adding following user to XML file");
			System.exit(0);
		}

		return ableToRemoveFollowingUser;

	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<Song> getSongList() {
		return songList;
	}

	public int getNumOfRegisteredPlaylists() {
		return numOfRegisteredPlaylists;
	}

	public void setNumberOfRegisteredPlaylists(int numOfRegisteredPlaylists) {
		this.numOfRegisteredPlaylists = numOfRegisteredPlaylists;
	}

	public void setController(MusicAppController controller) {
		this.controller = controller;
	}

	public boolean checkForPlaylistExistence(ObjectMapper objectMapper, JsonNode rootNode,
			String searchedPlaylistName) {

		Iterator<JsonNode> nodeIterator = rootNode.iterator();

		while (nodeIterator.hasNext()) {

			JsonNode playlistNode = nodeIterator.next();
			String playlistName = playlistNode.get("playlist_name").asText();

			if (searchedPlaylistName.equalsIgnoreCase(playlistName)) {
				return true;
			}
		}

		return false;
	}

	public void createPlaylist(String playlistType, String playlistName, String profileName) {

		try {
			File jsonFile = new File("playlists.json");

			ObjectMapper objectMapper = new ObjectMapper();
			ArrayNode playlistNodes = objectMapper.createArrayNode();
			ObjectNode playlistNode = objectMapper.createObjectNode();
			// ArrayList<Song> playlistSongs = new ArrayList<Song>();
			ArrayNode songList = objectMapper.createArrayNode();

			playlistNode.put("playlist_id", getNumOfRegisteredPlaylists());
			playlistNode.put("creator_user_name", profileName);
			playlistNode.put("playlist_name", playlistName);
			playlistNode.put("playlist_type", playlistType);
			playlistNode.put("number_of_songs", 0);
			playlistNode.put("duration", 0);

			if (!jsonFile.exists()) {
				jsonFile.createNewFile();

				playlistNode.set("song_list", songList);

				playlistNodes.add(playlistNode);
				// write created JSON mapping to .json file
				objectMapper.writeValue(jsonFile, playlistNodes);
				setNumberOfRegisteredPlaylists(getNumOfRegisteredPlaylists() + 1);
			}

			else { // if json file already exists

				byte[] playlistData = Files.readAllBytes(Paths.get("playlists.json"));

				// read JSON data
				JsonNode rootNode = objectMapper.readTree(playlistData);
				Iterator<JsonNode> playlistIterator = rootNode.iterator();

				boolean isDuplicatePlaylistName = false;

				while (playlistIterator.hasNext()) {
					JsonNode currentPlaylistNode = playlistIterator.next();
					String creatorUserName = currentPlaylistNode.get("creator_user_name").asText();
					String playlistJsonName = currentPlaylistNode.get("playlist_name").asText();

					if (creatorUserName.equals(profileName) && playlistJsonName.equals(playlistName)) {
						controller.giveDuplicatePlaylistWarning();
						isDuplicatePlaylistName = true;
					}
				}

				if (!isDuplicatePlaylistName) {

					playlistNode.set("song_list", songList);

					ArrayNode rootNodeAsArrayNode = (ArrayNode) rootNode;
					rootNodeAsArrayNode.add(playlistNode);
					// write created JSON mapping to .json file
					objectMapper.writeValue(jsonFile, rootNodeAsArrayNode);
					setNumberOfRegisteredPlaylists(getNumOfRegisteredPlaylists() + 1);
				}

			}

		}

		catch (IOException e) {
			System.out.println("Error occured while processing playlists.json file");
			System.exit(0);
		}
	}

	public void addSong(JsonNode rootNode, ObjectMapper objectMapper, String songName, String playlistName) {

		if (!checkForSongInPlaylist(songName, rootNode, playlistName)) {

			Iterator<JsonNode> nodeIterator = rootNode.iterator();
			ObjectNode playlistNodeToBeModified = null;
			ArrayNode modifiedSongList = null;

			int playlistDuration = 0;
			int numberOfSongs = 0;

			while (nodeIterator.hasNext()) {

				JsonNode playlistNode = nodeIterator.next();
				String currentNodePlaylistName = playlistNode.get("playlist_name").asText();

				if (playlistName.equalsIgnoreCase(currentNodePlaylistName)) {

					JsonNode songListNode = playlistNode.get("song_list");

					if (songListNode.isArray()) {

						ArrayNode songArrayNode = (ArrayNode) songListNode;
						ArrayList<String> songNames = new ArrayList<String>();

						for (int i = 0; i < songArrayNode.size(); i++) {
							String existingSongName = songArrayNode.get(i).asText();
							songNames.add(existingSongName);
							playlistDuration += getSongDuration(existingSongName);
							numberOfSongs++;
						}

						songNames.add(songName);
						playlistDuration += getSongDuration(songName);
						numberOfSongs++;

						ArrayNode songListArrayNode = objectMapper.createArrayNode();

						for (int i = 0; i < songNames.size(); i++) {
							songListArrayNode.add(songNames.get(i));
						}

						modifiedSongList = songListArrayNode;
						playlistNodeToBeModified = (ObjectNode) playlistNode;
					}

				}

			}

			playlistNodeToBeModified.set("song_list", modifiedSongList);
			playlistNodeToBeModified.put("duration", playlistDuration);
			playlistNodeToBeModified.put("number_of_songs", numberOfSongs);

			try {
				objectMapper.writeValue(new File("playlists.json"), rootNode);
			}

			catch (IOException e) {
				System.err.println("playlists.json file does not exist");
				System.exit(0);
			}

			controller.giveSongAddedSuccessfullyMessage(songName, playlistName);
		}

		else {
			controller.giveAdditionNotPossibleWarning();
		}
	}

	public void removeSong(JsonNode rootNode, ObjectMapper objectMapper, String songName, String playlistName) {

		if (checkForSongInPlaylist(songName, rootNode, playlistName)) {

			Iterator<JsonNode> nodeIterator = rootNode.iterator();
			ObjectNode playlistNodeToBeModified = null;
			ArrayNode modifiedSongList = null;

			int playlistDuration = 0;
			int numberOfSongs = 0;

			while (nodeIterator.hasNext()) {

				JsonNode playlistNode = nodeIterator.next();
				String currentNodePlaylistName = playlistNode.get("playlist_name").asText();

				if (playlistName.equalsIgnoreCase(currentNodePlaylistName)) {

					JsonNode songListNode = playlistNode.get("song_list");

					if (songListNode.isArray()) {

						ArrayNode songArrayNode = (ArrayNode) songListNode;
						ArrayList<String> songNames = new ArrayList<String>();

						for (int i = 0; i < songArrayNode.size(); i++) {
							String existingSongName = songArrayNode.get(i).asText();

							if (!existingSongName.equals(songName)) {
								songNames.add(existingSongName);
								playlistDuration += getSongDuration(existingSongName);
								numberOfSongs++;
							}
						}

						ArrayNode songListArrayNode = objectMapper.createArrayNode();

						for (int i = 0; i < songNames.size(); i++) {
							songListArrayNode.add(songNames.get(i));
						}

						modifiedSongList = songListArrayNode;
						playlistNodeToBeModified = (ObjectNode) playlistNode;
					}

				}

			}

			playlistNodeToBeModified.set("song_list", modifiedSongList);
			playlistNodeToBeModified.put("duration", playlistDuration);
			playlistNodeToBeModified.put("number_of_songs", numberOfSongs);

			try {
				objectMapper.writeValue(new File("playlists.json"), rootNode);
			}

			catch (IOException e) {
				System.err.println("playlists.json file does not exist");
				System.exit(0);
			}

			controller.giveSongRemovedSuccessfullyMessage(songName, playlistName);
		}

		else {

			controller.giveRemovalNotPossibleWarning();
		}

	}

	public ArrayList<Song> bringSongsOnPlaylist(ObjectMapper objectMapper, JsonNode rootNode, String playlistName) {

		ArrayList<Song> playlistSongs = new ArrayList<Song>();
		ArrayList<String> songNames = new ArrayList<String>();
		Iterator<JsonNode> nodeIterator = rootNode.iterator();

		while (nodeIterator.hasNext()) {

			JsonNode playlistNode = nodeIterator.next();
			String currentPlaylistName = playlistNode.get("playlist_name").asText();
			System.out.println("playlisName variable value: " + playlistName);
			System.out.println("currentPlaylistName variable value: " + currentPlaylistName);

			if (playlistName.equalsIgnoreCase(currentPlaylistName)) {
				JsonNode songListNode = playlistNode.get("song_list");

				if (songListNode.isArray()) {

					ArrayNode songListArrayNode = (ArrayNode) songListNode;

					for (int i = 0; i < songListArrayNode.size(); i++) {
						String songName = songListArrayNode.get(i).asText();
						songNames.add(songName);
					}

				}
			}
		}

		for (int i = 0; i < songNames.size(); i++) {
			for (int j = 0; j < songList.size(); j++) {
				if (songNames.get(i).equals(songList.get(j).getSong())) {
					playlistSongs.add(songList.get(j));
				}
			}
		}

		return playlistSongs;
	}

	public ArrayList<Song> bringSongsForGivenGenre(int genreId) {

		ArrayList<Song> songsBelongToGivenGenre = new ArrayList<Song>();

		for (Song song : songList) {
			if (song.getGenreId() == genreId) {
				songsBelongToGivenGenre.add(song);
			}
		}

		return songsBelongToGivenGenre;
	}

	public void updateTracksFileAfterLikeOrListenSong(String songName, int likeOrListen) {

		File tracksFile = new File("tracks.txt");
		File tempUpdatedTracksFile = new File("temp_tracks.txt");
		Scanner filerReader;
		FileWriter writer;

		if (!tracksFile.exists()) {
			System.err.println("tracks.txt file does not exist");
			System.exit(0);
		}

		try {

			filerReader = new Scanner(tracksFile);
			tempUpdatedTracksFile.createNewFile();
			writer = new FileWriter(tempUpdatedTracksFile, true);

			while (filerReader.hasNextLine()) {

				String line = filerReader.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");

				String genreId = tokenizer.nextToken();
				String trackId = tokenizer.nextToken();
				String song = tokenizer.nextToken();
				String artist = tokenizer.nextToken();
				String duration = tokenizer.nextToken();
				int popularity = Integer.parseInt(tokenizer.nextToken());
				int numberOfLikes = Integer.parseInt(tokenizer.nextToken());

				if (song.equals(songName)) {

					if (likeOrListen == 0) {
						numberOfLikes++;
					}

					else { // case in which likeOrListen is 1
						popularity++;
					}

					String updatedSongLine = genreId + "," + trackId + "," + song + "," + artist + "," + duration + ","
							+ popularity + "," + numberOfLikes + "\n";
					writer.write(updatedSongLine);
				}

				else {
					writer.write(line + "\n");
				}
			}

			filerReader.close();
			writer.close();

			filerReader = new Scanner(tempUpdatedTracksFile);

			tracksFile.delete();
			tracksFile.createNewFile();
			writer = new FileWriter(tracksFile, true);
			// writing updated data back to original tracks.txt file
			while (filerReader.hasNextLine()) {

				String line = filerReader.nextLine();
				writer.write(line + "\n");
			}

			filerReader.close();
			writer.close();
			tempUpdatedTracksFile.delete();

			songList.clear();
			loadTracksFromFile();
		}

		catch (IOException e) {
			System.err.println("Error has occured while processing tracks.txt file");
			System.exit(0);
		}

	}

	public ArrayList<Song> loadSongsOfSelectedPlaylist(String playlistName) {

		ArrayList<Song> playlistSongsList = new ArrayList<Song>();
		try {

			byte[] jsonFile = Files.readAllBytes(Paths.get("playlists.json"));
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonFile);

			ArrayList<String> songNameList = new ArrayList<String>();
			Iterator<JsonNode> nodeIterator = rootNode.iterator();

			while (nodeIterator.hasNext()) {

				JsonNode playlistNode = nodeIterator.next();
				String searchedPlaylistName = playlistNode.get("playlist_name").asText();

				if (playlistName.equals(searchedPlaylistName)) {

					JsonNode songListNode = playlistNode.get("song_list");

					if (songListNode.isArray()) {
						ArrayNode songListArrayNode = (ArrayNode) songListNode;

						for (int i = 0; i < songListArrayNode.size(); i++) {
							String songName = songListArrayNode.get(i).asText();
							songNameList.add(songName);
						}
					}
				}
			}

			for (String songInPlaylist : songNameList) {
				for (Song song : songList) {
					if (song.getSong().equals(songInPlaylist)) {
						playlistSongsList.add(song);
					}
				}
			}

		}

		catch (IOException e) {
			System.err.println("Error has occured while accessing playlists.json file");
			System.exit(0);
		}

		return playlistSongsList;
	}

	public String getMostLikedSong() {

		Song mostLikedSong = songList.get(0);

		for (int i = 1; i < songList.size(); i++) {
			if (songList.get(i).getNumberOfLikes() > mostLikedSong.getNumberOfLikes()) {
				mostLikedSong = songList.get(i);
			}
		}

		return mostLikedSong.getSong();
	}

	public String getMostPopularSong() {

		Song mostPopularSong = songList.get(0);

		for (int i = 1; i < songList.size(); i++) {
			if (songList.get(i).getPopularity() > mostPopularSong.getPopularity()) {
				mostPopularSong = songList.get(i);
			}
		}

		return mostPopularSong.getSong();
	}

	public String getMostFollowedUser() {

		User mostFollowedUser = userList.get(0);

		for (int i = 1; i < userList.size(); i++) {
			if (userList.get(i).getFollowerUsers().size() > mostFollowedUser.getFollowerUsers().size()) {
				mostFollowedUser = userList.get(i);
			}
		}

		return mostFollowedUser.getUserName();
	}

	public Map<String, Map<String, String>> getShortestAndLongestPlaylists() {

		Map<String, Map<String, String>> playlistNameGenreMap = new HashMap<String, Map<String, String>>();
		byte[] playlistData;
		String longestPlaylistName = null;
		String shortestPlaylistName = null;
		String longestPlaylistGenre = null;
		String shortestPlaylistGenre = null;

		try {

			playlistData = Files.readAllBytes(Paths.get("playlists.json"));
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(playlistData);

			Iterator<JsonNode> nodeIterator = rootNode.iterator();
			JsonNode playlistNode = nodeIterator.next();

			longestPlaylistName = playlistNode.get("playlist_name").asText();
			shortestPlaylistName = playlistNode.get("playlist_name").asText();

			longestPlaylistGenre = playlistNode.get("playlist_type").asText();
			shortestPlaylistGenre = playlistNode.get("playlist_type").asText();

			ArrayNode songListArrayNode = (ArrayNode) playlistNode.get("song_list");
			int shortestPlaylistSize = songListArrayNode.size();
			int longestPlaylistSize = songListArrayNode.size();

			while (nodeIterator.hasNext()) {

				JsonNode currentPlaylistNode = nodeIterator.next();
				JsonNode currentSongListNode = currentPlaylistNode.get("song_list");

				if (currentSongListNode.isArray()) {
					ArrayNode currentSongListArrayNode = (ArrayNode) currentSongListNode;

					if (currentSongListArrayNode.size() > longestPlaylistSize) {
						longestPlaylistName = currentPlaylistNode.get("playlist_name").asText();
						longestPlaylistGenre = currentPlaylistNode.get("playlist_type").asText();
						longestPlaylistSize = currentSongListArrayNode.size();
					}

					if (currentSongListArrayNode.size() < shortestPlaylistSize) {
						shortestPlaylistName = currentSongListArrayNode.get("playlist_name").asText();
						shortestPlaylistGenre = currentSongListArrayNode.get("playlist_type").asText();
						shortestPlaylistSize = currentSongListArrayNode.size();
					}
				}
			}
		}

		catch (IOException e) {
			System.err.println("Error has occured while accessing playlists.json file");
			System.exit(0);
		}

		Map<String, String> longestPlaylistMap = new HashMap<String, String>();
		longestPlaylistMap.put(longestPlaylistName, longestPlaylistGenre);

		Map<String, String> shortestPlaylistMap = new HashMap<String, String>();
		shortestPlaylistMap.put(shortestPlaylistName, shortestPlaylistGenre);

		playlistNameGenreMap.put("Longest Playlist", longestPlaylistMap);
		playlistNameGenreMap.put("Shortest Playlist", shortestPlaylistMap);

		return playlistNameGenreMap;
	}

	private void loadTracksFromFile() {

		File tracksFile = new File("tracks.txt");

		if (!tracksFile.exists()) {
			System.err.println("tracks.txt file does not exist");
			System.exit(0);
		}

		try {

			Scanner filerReader = new Scanner(tracksFile);

			while (filerReader.hasNextLine()) {

				String line = filerReader.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");

				int genreId = Integer.parseInt(tokenizer.nextToken());
				int trackId = Integer.parseInt(tokenizer.nextToken());
				String song = tokenizer.nextToken();
				String artist = tokenizer.nextToken();
				int duration = Integer.parseInt(tokenizer.nextToken());
				int popularity = Integer.parseInt(tokenizer.nextToken());
				int numberOfLikes = Integer.parseInt(tokenizer.nextToken());

				Song anySong = new Song(genreId, trackId, song, artist, duration, popularity, numberOfLikes);
				songList.add(anySong);
			}

			filerReader.close();
		}

		catch (FileNotFoundException e) {
			System.err.println("Error has occured while processing tracks.txt file");
			System.exit(0);
		}

	}

	private void loadNumOfRegisteredPlaylistsFromJsonFile() {

		File jsonFile = new File("playlists.json");

		if (jsonFile.exists()) {
			this.numOfRegisteredPlaylists = 1;

			byte[] playlistData;

			try {
				playlistData = Files.readAllBytes(Paths.get("playlists.json"));

				ObjectMapper objectMapper = new ObjectMapper();

				// read JSON data
				JsonNode rootNode = objectMapper.readTree(playlistData);
				Iterator<JsonNode> playlistIterator = rootNode.iterator();

				while (playlistIterator.hasNext()) {
					playlistIterator.next();
					numOfRegisteredPlaylists++;
				}

			} catch (IOException e) {
				System.out.println("Error while processing playlists.json file");
				System.exit(0);

			}
		}

	}

	private boolean checkForSongInPlaylist(String songName, JsonNode rootNode, String playlistName) {

		Iterator<JsonNode> nodeIterator = rootNode.iterator();

		while (nodeIterator.hasNext()) {

			JsonNode playlistNode = nodeIterator.next();
			String currentNodePlaylistName = playlistNode.get("playlist_name").asText();

			if (playlistName.equalsIgnoreCase(currentNodePlaylistName)) {

				JsonNode songListNode = playlistNode.get("song_list");
				ArrayNode songArrayNode = (ArrayNode) songListNode;

				if (songListNode.isArray()) {
					;

					for (int i = 0; i < songArrayNode.size(); i++) {
						String existingSongName = songArrayNode.get(i).asText();

						if (existingSongName.equals(songName)) {
							return true;
						}

					}
				}

			}

		}

		return false;
	}

	private int getSongDuration(String songName) {

		int songDuration = 0;
		for (Song song : songList) {
			if (song.getSong().equals(songName)) {
				songDuration = song.getDuration();
				break;
			}
		}

		return songDuration;
	}

}
