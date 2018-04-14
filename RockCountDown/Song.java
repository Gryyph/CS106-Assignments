package rockcountdown;

import org.apache.commons.lang3.*;

public class Song {
	int rank;
	String title;
	String artist;


	public Song(String song) {
		super();
		song = song.trim();
		String[] songSplit = song.split("\t");
		rank = Integer.parseInt(songSplit[0]);
		title = songSplit[1];
		artist = songSplit[2];

	}


	public Song(int rank, String title, String artist) {
		this.rank = rank;
		this.title = title;
		this.artist = artist;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}

}
