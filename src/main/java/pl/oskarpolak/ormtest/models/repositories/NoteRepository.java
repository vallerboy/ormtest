package pl.oskarpolak.ormtest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ormtest.models.NoteModel;

@Repository
public interface NoteRepository extends CrudRepository<NoteModel, Integer> {
}
