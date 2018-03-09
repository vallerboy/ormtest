package pl.oskarpolak.ormtest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ormtest.models.UserModel;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    List<UserModel> findByAgeGreaterThanAndLoginLike(int a, String login);
}
