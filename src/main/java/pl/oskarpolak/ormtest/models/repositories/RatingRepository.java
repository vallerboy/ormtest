package pl.oskarpolak.ormtest.models.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.oskarpolak.ormtest.models.RatingModel;

@Repository
public interface RatingRepository extends CrudRepository<RatingModel, Integer> {
    boolean existsByUserIdAndPostId(int userId, int postId);
}
