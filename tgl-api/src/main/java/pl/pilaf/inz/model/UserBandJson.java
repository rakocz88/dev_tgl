package pl.pilaf.inz.model;

import java.io.Serializable;

public class UserBandJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    private Band band;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Band getBand() {
	return band;
    }

    public void setBand(Band band) {
	this.band = band;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((band == null) ? 0 : band.hashCode());
	result = prime * result + ((user == null) ? 0 : user.hashCode());
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
	UserBandJson other = (UserBandJson) obj;
	if (band == null) {
	    if (other.band != null)
		return false;
	} else if (!band.equals(other.band))
	    return false;
	if (user == null) {
	    if (other.user != null)
		return false;
	} else if (!user.equals(other.user))
	    return false;
	return true;
    }

}
