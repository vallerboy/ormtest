package pl.oskarpolak.ormtest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ormtest.models.PostModel;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostModel, Integer> {
    List<PostModel> findAllByUserIdOrderByIdDesc(int userId);
}
