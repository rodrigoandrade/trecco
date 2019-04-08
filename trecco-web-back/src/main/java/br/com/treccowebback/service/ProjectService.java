package br.com.treccowebback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treccowebback.model.Project;
import br.com.treccowebback.repository.ProjectRepository;
import br.com.treccowebback.repository.impl.MemberRepositoryImpl;

/**
 * @author rodrigo.andrade
 *
 */

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repository;
	
	@Autowired
	private MemberRepositoryImpl memberRepositoryImpl;

	public List<Project> listProjecstBy(String name) {
		return repository.findByName(name);
	}

	public Project save(Project project) {
		return repository.save(project);
	}

}
