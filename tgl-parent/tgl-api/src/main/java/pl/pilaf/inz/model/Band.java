package pl.pilaf.inz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pl.pilaf.inz.wrapper.BandWrapper;

@Entity
public class Band implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(max = 100)
	private String name;

	@Size(max = 100)
	private String description;

	private Date createdDate;

	@Size(max = 100)
	private String createdPlace;

	@ManyToMany(mappedBy = "bands", fetch = FetchType.EAGER)
	private List<User> members;

	@Size(max = 100)
	private String gengre;

	@OneToMany()
	private List<Album> albums;

	public Band() {
		super();
	}

	public Band(long id, String name, String description, Date createdDate, String createdPlace, List<User> members,
			String gengre) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.createdPlace = createdPlace;
		this.members = members;
		this.gengre = gengre;
	}

	public Band(BandWrapper bandWrapper) {
		this.name = bandWrapper.getName();
		this.description = bandWrapper.getDescription();
		this.createdDate = bandWrapper.getCreatedDate();
		this.createdPlace = bandWrapper.getCreatedPlace();
		this.gengre = bandWrapper.getGengre();
	}

	public List<Long> getIdList() {
		List<Long> idList = new ArrayList<>();
		Consumer<User> userConsumer = (User user) -> idList.add(user.getId());
		if (this.members != null) {
			this.members.stream().forEach(userConsumer);
		}
		return idList;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedPlace() {
		return createdPlace;
	}

	public void setCreatedPlace(String createdPlace) {
		this.createdPlace = createdPlace;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getGengre() {
		return gengre;
	}

	public void setGengre(String gengre) {
		this.gengre = gengre;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdPlace == null) ? 0 : createdPlace.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((gengre == null) ? 0 : gengre.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Band other = (Band) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdPlace == null) {
			if (other.createdPlace != null)
				return false;
		} else if (!createdPlace.equals(other.createdPlace))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gengre == null) {
			if (other.gengre != null)
				return false;
		} else if (!gengre.equals(other.gengre))
			return false;
		if (id != other.id)
			return false;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
