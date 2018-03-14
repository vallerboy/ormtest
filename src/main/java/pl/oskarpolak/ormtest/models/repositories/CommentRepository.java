package pl.oskarpolak.ormtest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ormtest.models.CommentModel;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentModel, Integer> {
    List<CommentModel> findByPostIdOrderByIdDesc(int id);
}
