package br.com.treccowebback.repository.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.treccowebback.model.Project;

public class ProjectRepositoryImpl {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Project findById(ObjectId id) {
		return mongoTemplate.findById(id, Project.class);
	}

	public List<Project> findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		return mongoTemplate.find(query, Project.class);
	}

	public Project save(Project project) {
		return mongoTemplate.insert(project);
	}
	
	public void delete(Project project) {
		mongoTemplate.remove(project, Project.class.getSimpleName().toLowerCase());
	}
	
	public void updateActive(String name, boolean active) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		try {
			Project project = mongoTemplate.findOne(query, Project.class);
			project.setActive(active);

			mongoTemplate.save(project);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
