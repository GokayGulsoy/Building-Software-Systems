package helpers;

public enum SongGenres {

	ACOUSTIC("acoustic"), INSTRUMENTAL("instrumental"), ROCK("rock"), HIPHOP("hip-hop"), JAZZ("jazz"), POP("pop");

	public final String genre;

	private SongGenres(String genre) {
		this.genre = genre;
	}

}
