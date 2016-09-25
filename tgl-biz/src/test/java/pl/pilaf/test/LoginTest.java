package pl.pilaf.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import pl.pilaf.inz.controller.LoginController;
import pl.pilaf.inz.controller.SessionBiz;
import pl.pilaf.inz.model.User;
import pl.pilaf.inz.repository.UserRepository;
import pl.pilaf.json.UserLoginJson;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest implements TestConstants {

	private static final String USER_NAME = "user";

	private static final String USER_PASSWORD = "password";

	private static final String WRONG_USER_NAME = "userF";

	private static final String WRONG_USER_PASSWORD = "passwordF";

	@InjectMocks
	private LoginController loginController;

	@Mock
	private UserRepository userRepository;

	@Mock
	private SessionBiz sessionBiz;

	@Before
	public void initMocks() {
		Mockito.when(userRepository.findByLogin(USER_NAME))
				.thenReturn(Arrays.asList(new User[] { new User(USER_NAME, USER_PASSWORD) }));
	}

	@Test
	public void loginTest() {
		ResponseEntity<String> response = loginController.logIn(getUserJson(USER_NAME, USER_PASSWORD));
		assertEquals(true, response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void loginWrongLoginTest() {
		ResponseEntity<String> response = loginController.logIn(getUserJson(WRONG_USER_NAME, USER_PASSWORD));
		assertEquals(false, response.getStatusCode().is2xxSuccessful());
		assertEquals(true, response.getStatusCode().is4xxClientError());
	}

	@Test
	public void loginWrongPasswordTest() {
		ResponseEntity<String> response = loginController.logIn(getUserJson(USER_NAME, WRONG_USER_PASSWORD));
		assertEquals(false, response.getStatusCode().is2xxSuccessful());
		assertEquals(true, response.getStatusCode().is4xxClientError());
	}

	private UserLoginJson getUserJson(String login, String password) {
		UserLoginJson userJson = new UserLoginJson();
		userJson.setLogin(login);
		userJson.setPassword(password);
		return userJson;
	}

}
