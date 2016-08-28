package pl.pilaf.inz.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;

public class AlbumWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String name;

	private String description;

	private List<Long> songs;

	private Long band;

	private Date createdDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getSongs() {
		return songs;
	}

	public void setSongs(List<Long> songs) {
		this.songs = songs;
	}

	public Long getBand() {
		return band;
	}

	public void setBand(Long band) {
		this.band = band;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AlbumWrapper() {
		super();
	}

	public AlbumWrapper(long id, String name, String description, List<Long> songs, Band band, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.songs = songs;
		this.createdDate = createdDate;

	}

	public AlbumWrapper(Album album) {
		super();
		this.id = album.getId();
		this.name = album.getName();
		this.description = album.getDescription();
		this.songs = album.getSongsId(getSongs());
		this.createdDate = album.getCreatedDate();
	}

}
