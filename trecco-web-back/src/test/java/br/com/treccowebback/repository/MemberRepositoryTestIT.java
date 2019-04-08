package br.com.treccowebback.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.treccowebback.model.Member;
import br.com.treccowebback.repository.impl.MemberRepositoryImpl;

/**
 * @author rodrigo.andrade
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTestIT {

	@Autowired
	private MemberRepositoryImpl memberRepository;

	private Member joao;
	private Member joana;

	@Before
	public void initialize() {
		joao = new Member("Joao", true);
		joana = new Member("Joana", true);

		memberRepository.save(joao);
		memberRepository.save(joana);
	}

	@After
	public void finalize() {
		memberRepository.delete(joao);
		memberRepository.delete(joana);
	}

	@Test
	public void testMembersSave() {
		List<Member> members = memberRepository.findByName("Joa");
		assertThat(members).hasSize(2).extracting("name", "active")
				.contains(tuple("Joao", true), 
						  tuple("Joana", true));
	}
	
	@Test
	public void testFindByName() {
		joao = memberRepository.findByName(joao.getName()).get(0);
		joana = memberRepository.findByName(joana.getName()).get(0);
		assertThat(joao.getName()).as("Checking %s' name", joao.getName()).isEqualTo("Joao");
		assertThat(joana.getName()).as("Checking %s' name", joana.getName()).isEqualTo("Joana");
	}

}
