package helpers;

public class Song {

	private int genre_id;
	private int track_id;
	private String song;
	private String artist;
	private int duration;
	private int popularity;
	private int numberOfLikes;

	public Song(int genre_id, int track_id, String song, String artist, int duration, int popularity,
			int numberOfLikes) {
		this.genre_id = genre_id;
		this.track_id = track_id;
		this.song = song;
		this.artist = artist;
		this.duration = duration;
		this.popularity = popularity;
		this.numberOfLikes = numberOfLikes;
	}

	public int getGenreId() {
		return genre_id;
	}

	public int getTrackId() {
		return track_id;
	}

	public String getSong() {
		return song;
	}

	public String getArtist() {
		return artist;
	}

	public int getDuration() {
		return duration;
	}

	public int getPopularity() {
		return popularity;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

}
