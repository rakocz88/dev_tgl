package pl.pilaf.inz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pl.pilaf.enums.UserType;
import pl.pilaf.inz.wrapper.UserWrapper;

@Entity
@Table(name = "appUser")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(max = 100)
	@NotNull
	private String firstName;

	@Size(max = 100)
	@NotNull
	private String lastName;

	private Date birtDate;

	@Size(max = 255)
	private String birthPlace;
	private UserType userType;

	@Size(max = 50)
	@NotNull
	private String login;

	@Size(max = 50)
	@NotNull
	private String password;

	@Size(max = 255)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_BAND", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "BAND_ID", referencedColumnName = "id") })
	private List<Band> bands;

	public User() {
		super();
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User(long id, String firstName, String lastName, Date birtDate, String birthPlace, UserType userType,
			String login, String password, String description, List<Band> bands) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birtDate = birtDate;
		this.birthPlace = birthPlace;
		this.userType = userType;
		this.login = login;
		this.password = password;
		this.description = description;
		this.bands = bands;
	}

	public User(UserWrapper userWrapper) {
		this.id = userWrapper.getId();
		this.firstName = userWrapper.getFirstName();
		this.lastName = userWrapper.getLastName();
		this.birtDate = userWrapper.getBirtDate();
		this.birthPlace = userWrapper.getBirthPlace();
		this.login = userWrapper.getLogin();
		this.password = userWrapper.getPassword();
		this.description = userWrapper.getDescription();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirtDate() {
		return birtDate;
	}

	public void setBirtDate(Date birtDate) {
		this.birtDate = birtDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birtDate == null) ? 0 : birtDate.hashCode());
		result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		User other = (User) obj;
		if (birtDate == null) {
			if (other.birtDate != null)
				return false;
		} else if (!birtDate.equals(other.birtDate))
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

}
