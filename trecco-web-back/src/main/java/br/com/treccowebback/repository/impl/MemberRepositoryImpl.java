package br.com.treccowebback.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.treccowebback.model.Member;

public class MemberRepositoryImpl {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<Member> findByName(String name) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name));
		return mongoTemplate.find(query, Member.class);
	}

	public Member save(Member member) {
		return mongoTemplate.insert(member);
	}

	public void delete(Member member) {
		mongoTemplate.remove(member, Member.class.getSimpleName().toLowerCase());
	}

}
