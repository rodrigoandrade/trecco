package br.com.treccowebback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treccowebback.model.Member;
import br.com.treccowebback.repository.impl.MemberRepositoryImpl;

/**
 * @author rodrigo.andrade
 *
 */

@Service
public class MemberService {
	
	@Autowired
	private MemberRepositoryImpl memberRepositoryImpl;
	
	public List<Member> findByName(String name) throws Exception {
		return memberRepositoryImpl.findByName(name);
	}

	public Member save(Member member) throws Exception {
		return memberRepositoryImpl.save(member);
	}
	
	public void delete(Member member) throws Exception {
		memberRepositoryImpl.delete(member);
	}
	
	public void updateActive(String name, boolean active) throws Exception {
		memberRepositoryImpl.updateActive(name, active);
	}
}
