package pl.pilaf.inz.controller;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.UserRepository;
import pl.pilaf.inz.wrapper.UserWrapper;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

	private UserRepository userRepository;
	
	private BandRepository bandRepository;
	
	
	@Autowired
	public RegisterController(UserRepository userRepository, BandRepository bandRepository) {
		super();
		this.userRepository = userRepository;
		this.bandRepository = bandRepository;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void register(@RequestBody UserWrapper userWrapper) {
		User user = new User(userWrapper);
		user = userRepository.save(user);
		if (user.getBands().isEmpty()) {
			return;
		}
		final User userForLambda = user;
		Consumer<Band> addToBandConsumer = (Band band) -> {
			band.getMembers().add(userForLambda);
			bandRepository.save(band);
		};
		user.getBands().stream().forEach(addToBandConsumer);

	}

	@RequestMapping(value = "/userBand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerBand(@RequestBody Band band) {
		bandRepository.save(band);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAllByName(@RequestParam String name) {
		return userRepository.findByFirstName(name);
	}

}
