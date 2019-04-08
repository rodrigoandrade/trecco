package br.com.treccowebback.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.treccowebback.model.Project;

/**
 * @author rodrigo.andrade
 *
 */

public interface ProjectRepository extends MongoRepository<Project, String> {
	
	@Query("{project: { $regex: ?0 } })")
	public List<Project> findByName(String name);

}
