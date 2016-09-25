package pl.pilaf.inz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.repository.SongRepository;
import pl.pilaf.inz.wrapper.SongWrapper;

@RestController
@RequestMapping(value = "/song")
public class SongController {

	private SongRepository songRepository;

	@Autowired
	public SongController(SongRepository songRepository) {
		super();
		this.songRepository = songRepository;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SongWrapper findById(@PathVariable("id") Long id) {
		return new SongWrapper(songRepository.findOne(id));
	}

}
