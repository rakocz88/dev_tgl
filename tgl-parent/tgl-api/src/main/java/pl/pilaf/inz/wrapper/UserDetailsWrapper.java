package pl.pilaf.inz.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;

public class UserDetailsWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String firstName;

	private String lastName;

	private Date birtDate;

	private String birthPlace;

	private String login;

	private String password;

	private String description;

	private List<BandWrapper> bands;

	public UserDetailsWrapper(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.birtDate = user.getBirtDate();
		this.birthPlace = user.getBirthPlace();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.description = user.getDescription();
		// Function<Band, BandWrapper> bandFunction = (Band band) -> new
		// BandWrapper(band);
		this.bands = user.getBands().stream().map(bandFunction).collect(Collectors.toList());
	}

	public Function<Band, BandWrapper> bandFunction = (Band band) -> new BandWrapper(band);

	public UserDetailsWrapper(String firstName, String lastName, Date birtDate, String birthPlace, String login,
			String password, String description, List<BandWrapper> bands) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birtDate = birtDate;
		this.birthPlace = birthPlace;
		this.login = login;
		this.password = password;
		this.description = description;
		this.bands = bands;
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

	public List<BandWrapper> getBands() {
		return bands;
	}

	public void setBands(List<BandWrapper> bands) {
		this.bands = bands;
	}

}
