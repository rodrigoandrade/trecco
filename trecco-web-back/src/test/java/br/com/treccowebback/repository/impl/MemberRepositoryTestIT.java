package br.com.treccowebback.repository.impl;

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
	public void initialize() throws Exception {
		joao = Member.builder().name("Joao").active(true).build();
		joana = Member.builder().name("Joana").active(true).build();

		memberRepository.save(joao);
		memberRepository.save(joana);
	}

	@After
	public void finalize() throws Exception {
		memberRepository.delete(joao);
		memberRepository.delete(joana);
	}

	@Test
	public void testMembersSave() throws Exception {
		List<Member> members = memberRepository.findByName("Joa");
		assertThat(members).hasSize(2).extracting("name", "active")
				.contains(tuple("Joao", true), 
						  tuple("Joana", true));
	}
	
	@Test
	public void testFindByName() throws Exception {
		joao = memberRepository.findByName(joao.getName()).get(0);
		joana = memberRepository.findByName(joana.getName()).get(0);
		assertThat(joao.getName()).as("Checking %s' name", joao.getName()).isEqualTo("Joao");
		assertThat(joana.getName()).as("Checking %s' name", joana.getName()).isEqualTo("Joana");
	}
	
	@Test
	public void testDelete() throws Exception {
		Member member = memberRepository.findByName(joao.getName()).get(0);
		
		memberRepository.delete(member);
		
		List<Member> listMemberEmpty = memberRepository.findByName(member.getName());
		assertThat(listMemberEmpty.isEmpty()).as("Checking Removing of Member", listMemberEmpty).isEqualTo(true);
	}

	@Test
	public void testSave() throws Exception {
		Member teobaldo = Member.builder().name("Teobaldo").active(false).build();
		memberRepository.save(teobaldo);
		
		Member member = memberRepository.findByName(teobaldo.getName()).get(0);
		assertThat(member.getName()).as("Checking %s' name", member.getName()).isEqualTo("Teobaldo");
		assertThat(member.getActive()).as("Checking %s' active", member.getActive()).isEqualTo(false);
		
		memberRepository.delete(member);
	}
	
	@Test
	public void testUpdateActive() throws Exception {
		memberRepository.updateActive(joana.getName(), false);
		
		Member member = memberRepository.findByName(joana.getName()).get(0);
		assertThat(member.getName()).as("Checking %s' name", member.getName()).isEqualTo("Joana");
		assertThat(member.getActive()).as("Checking %s' active", member.getActive()).isEqualTo(false);
	}

}
