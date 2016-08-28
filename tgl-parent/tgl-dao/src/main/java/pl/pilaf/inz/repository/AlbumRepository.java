package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.Album;
import pl.pilaf.inz.model.Band;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Long>{
	
	
    
    List<Album> findByBand(Band band);
    
    List<Album> findByName(String bandName);
    
}
