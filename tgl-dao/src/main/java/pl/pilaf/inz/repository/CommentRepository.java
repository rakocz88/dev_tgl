package pl.pilaf.inz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.pilaf.inz.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long>{
    

    
}
