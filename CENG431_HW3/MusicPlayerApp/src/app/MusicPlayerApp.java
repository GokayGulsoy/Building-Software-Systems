package app;

import javax.swing.JFrame;

import controller.MusicAppController;
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

public class MusicPlayerApp extends JFrame {

	private static final long serialVersionUID = 7555812599316871617L;

	public static void main(String[] args) {
		// application starts via creating
		// instance of views, controller, and model
		LoginPage loginPage = new LoginPage();
		ProfilePage profilePage = new ProfilePage();
		PlayListCreationPage playListCreationPage = new PlayListCreationPage();
		ViewFollowedUsersPlaylistsPage viewFollowedUsersPlaylistsPage = new ViewFollowedUsersPlaylistsPage();
		NavigationPage navigationPage = new NavigationPage();
		StatisticsPage statisticsPage = new StatisticsPage();
		LikeAndListenSongPage likeAndListenSongPage = new LikeAndListenSongPage();
		FollowUnfollowOtherUsersPage followUnfollowOtherUsersPage = new FollowUnfollowOtherUsersPage();
		AddRemoveSongPage addRemoveSongPage = new AddRemoveSongPage();

		MusicAppModel model = new MusicAppModel();

		MusicAppController controller = new MusicAppController(loginPage, profilePage, playListCreationPage,
				viewFollowedUsersPlaylistsPage, navigationPage, statisticsPage, likeAndListenSongPage,
				followUnfollowOtherUsersPage, addRemoveSongPage, model);

		controller.loadXMLFile();
		loginPage.setVisible(true);
	}

}
