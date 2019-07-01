package br.com.treccowebback.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.treccowebback.model.Member;

/**
 * 
 * @author rodrigo.andrade
 *
 */

public class MemberRepositoryImpl {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Member> findByName(String name) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		
		try {
			return mongoTemplate.find(query, Member.class);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Member save(Member member) throws Exception {
		try {
			return mongoTemplate.insert(member);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void delete(Member member) throws Exception {
		try {
			mongoTemplate.remove(member, Member.class.getSimpleName().toLowerCase());
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void updateActive(String name, boolean active) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		try {
			Member member = mongoTemplate.findOne(query, Member.class);
			member.setActive(active);

			mongoTemplate.save(member);
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
