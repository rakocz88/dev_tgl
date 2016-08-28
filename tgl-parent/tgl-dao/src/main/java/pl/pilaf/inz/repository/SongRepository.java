package pl.pilaf.inz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.Band;
import pl.pilaf.inz.model.Song;

@Repository
public interface SongRepository extends CrudRepository<Song,Long>{
    
    List<Song> findByBand(Band band);
    
    List<Song> findByName(String name);
}
