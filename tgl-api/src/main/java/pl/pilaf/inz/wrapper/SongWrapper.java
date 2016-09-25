package pl.pilaf.inz.wrapper;

import java.io.Serializable;

import pl.pilaf.inz.model.Song;

public class SongWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;

	private String path;

	private long band;

	private long album;

	private String description;

	public SongWrapper(Song song) {
		super();
		this.id = song.getId();
		this.name = song.getName();
		this.path = song.getPath();
		this.band = song.getBand().getId();
		this.album = song.getAlbum().getId();
		this.description = song.getDescription();
	}

	public SongWrapper(String name, String path, long band, long album, String description) {
		super();
		this.name = name;
		this.path = path;
		this.band = band;
		this.album = album;
		this.description = description;
	}

	public SongWrapper() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getBand() {
		return band;
	}

	public void setBand(long band) {
		this.band = band;
	}

	public long getAlbum() {
		return album;
	}

	public void setAlbum(long album) {
		this.album = album;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
