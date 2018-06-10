package tsystemstask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tsystemstask.domain.TSystemTask;

@Repository
public interface TaskRepository extends CrudRepository<TSystemTask, Integer> {

}
