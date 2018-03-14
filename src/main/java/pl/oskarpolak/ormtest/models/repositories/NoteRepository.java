package pl.oskarpolak.ormtest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ormtest.models.NoteModel;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<NoteModel, Integer> {
    List<NoteModel> findAllByUserIdOrderByIdDesc(int userId);
}
