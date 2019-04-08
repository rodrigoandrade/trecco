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
	
	public List<Member> findByName(String name) {
		return memberRepositoryImpl.findByName(name);
	}

	public Member save(Member member) {
		return memberRepositoryImpl.save(member);
	}
}
