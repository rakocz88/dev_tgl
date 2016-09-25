package pl.pilaf.inz.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.Song;
import pl.pilaf.inz.repository.AlbumRepository;
import pl.pilaf.inz.repository.BandRepository;
import pl.pilaf.inz.repository.SongRepository;
import pl.pilaf.inz.wrapper.AlbumWrapper;
import pl.pilaf.inz.wrapper.SongWrapper;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

	private AlbumRepository albumRepository;
	
	private BandRepository bandRepository;
	
	@Autowired
	public AlbumController( BandRepository bandRepository, AlbumRepository albumRepository, SongRepository songRepository){
		this.bandRepository = bandRepository;
		this.albumRepository = albumRepository;
	}
	
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AlbumWrapper create(@RequestBody AlbumWrapper albumWrapper) {
		Band band  = bandRepository.findOne(albumWrapper.getBand());
		Album album = new Album(albumWrapper);
		album.setBand(band);
		band.getAlbums().add(album);
		Album createdAlbum =  albumRepository.save(album);
		bandRepository.save(band);
		return new AlbumWrapper(createdAlbum);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AlbumWrapper findById(@PathVariable("id") Long id) {
		return new AlbumWrapper(albumRepository.findOne(id));
	}
	
	@RequestMapping(value="{id}/songs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SongWrapper> findAllSongs(@PathVariable("id") Long id) {
		Album album = albumRepository.findOne(id);
		Function<Song, SongWrapper> songFunction = (Song song) -> (new SongWrapper(song));
		return album.getSongs().stream().map(songFunction).collect(Collectors.toList());
	}
	
	

}
