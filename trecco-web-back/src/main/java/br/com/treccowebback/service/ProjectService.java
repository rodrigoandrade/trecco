package br.com.treccowebback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treccowebback.model.Project;
import br.com.treccowebback.repository.impl.ProjectRepositoryImpl;

/**
 * @author rodrigo.andrade
 *
 */

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepositoryImpl repositoryImpl;
	
	public List<Project> listProjecstBy(String name) {
		return repositoryImpl.findByName(name);
	}

	public Project save(Project project) {
		return repositoryImpl.save(project);
	}
	
	public void delete(Project project) throws Exception {
		repositoryImpl.delete(project);
	}
	
	public void updateActive(String name, boolean active) throws Exception {
		repositoryImpl.updateActive(name, active);
	}

}
