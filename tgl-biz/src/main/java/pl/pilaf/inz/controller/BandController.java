package pl.pilaf.inz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.User;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.UserRepository;
import pl.pilaf.inz.wrapper.AddUserWrapper;
import pl.pilaf.inz.wrapper.AlbumWrapper;
import pl.pilaf.inz.wrapper.BandWrapper;

@RestController
@RequestMapping(value = "/band")
public class BandController {

	private BandRepository bandRepository;

	private UserRepository userRepository;

	@Inject
	private SessionBiz sessionBiz;

	@Autowired
	public BandController(BandRepository bandRepository, UserRepository userRepository) {
		super();
		this.bandRepository = bandRepository;
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Band> findAll() {
		final List<Band> resultList = new ArrayList<>();
		final Iterable<Band> all = bandRepository.findAll();
		Consumer<Band> consLambda = (Band band) -> resultList.add(band);
		all.forEach(consLambda);
		return resultList;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Band> findAllByName(@RequestParam String name) {
		return bandRepository.findByName(name);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public BandWrapper register(@RequestBody BandWrapper band) {
		Band createdBand = bandRepository.save(new Band(band));
		return new BandWrapper(createdBand);
	}

	@RequestMapping(value = "byUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BandWrapper> findAllForUser() {
		List<Band> bandList = new ArrayList<>();
		Consumer<Band> bandConsumer = (Band band) -> bandList.add(band);
		Predicate<Band> bandPredicate = (Band band) -> band.getMembers().contains(sessionBiz.getUser());
		Function<Band, BandWrapper> bandFunction = (Band band) -> (new BandWrapper(band));
		bandRepository.findAll().forEach(bandConsumer);
		List<BandWrapper> responseList = bandList.stream().filter(bandPredicate).map(bandFunction)
				.collect(Collectors.toList());
		return responseList;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BandWrapper findById(@PathVariable("id") Long id) {
		return new BandWrapper(bandRepository.findOne(id));
	}

	@RequestMapping(value = "{id}/albums", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlbumWrapper> findAllForBand(@PathVariable("id") Long id) {
		Band band = bandRepository.findOne(id);
		Function<Album, AlbumWrapper> albumFunction = (Album album) -> (new AlbumWrapper(album));
		List<AlbumWrapper> responseList = band.getAlbums().stream().map(albumFunction).collect(Collectors.toList());
		return responseList;
	}

	@RequestMapping(value = "/addToBand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void register(@RequestBody AddUserWrapper userBandWrapper) {
		User user = userRepository.findOne(userBandWrapper.getUserId());
		Band band = bandRepository.findOne(userBandWrapper.getGroupId());
		if (band.getMembers().contains(user)) {
			return;
		}
		band.getMembers().add(user);
		bandRepository.save(band);
		user.getBands().add(band);
		userRepository.save(user);
	}

	@RequestMapping(value = "filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BandWrapper> filterBands(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gengre", required = false) String gengre) {
		name = filterUndefined(name);
		gengre = filterUndefined(gengre);
		return bandRepository.filter(name, gengre).stream().map(bandWrapperFunction).collect(Collectors.toList());
	}

	public String filterUndefined(String filterString) {
		return filterString.equals("undefined") ? "" : filterString;
	}

	public static Function<Band, BandWrapper> bandWrapperFunction = (Band band) -> (new BandWrapper(band));

}
