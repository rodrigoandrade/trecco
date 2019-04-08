package br.com.treccowebback.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.treccowebback.model.Member;

/**
 * @author rodrigo.andrade
 *
 */

public interface MemberRepository extends MongoRepository<Member, String> {
	
	@Query("{member: { $regex: ?0 } })")
	public List<Member> findByName(String name);

}
