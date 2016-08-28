package pl.pilaf.inz.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pl.pilaf.inz.model.User;

@Named
@SessionScoped
public class SessionBiz implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean isLogedIn;
    private User user;

    public boolean isLogedIn() {
	return isLogedIn;
    }

    public void setLogedIn(boolean isLogedIn) {
	this.isLogedIn = isLogedIn;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
