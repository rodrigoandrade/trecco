package br.com.treccowebback.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.treccowebback.model.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTestIT {

	private Member mary;
	private Member paul;
	
	@Autowired
	private MemberService service;
	
	@Before
	public void initialize() throws Exception {
		
		mary = Member.builder().name("Mary").active(true).build();
		paul = Member.builder().name("Paul").active(false).build();
		
		service.save(mary);
		service.save(paul);
	}

	@After
	public void finalize() throws Exception {
		service.delete(mary);
		service.delete(paul);
	}
	
	@Test
	public void testFindMemberByName() throws Exception {
		List<Member> members = new ArrayList<Member>();
		
		List<Member> memberMary =  service.findByName("Mary");
		List<Member> memberPaul =  service.findByName("Paul");

		members.addAll(memberMary);
		members.addAll(memberPaul);
		
		assertThat(members)
			    	.hasSize(2)
			    	.extracting("name", "active")
			    	.contains(tuple("Mary", true),
			    			  tuple("Mary", true));
	}
	
	@Test
	public void testSave() throws Exception {
		List<Member> members = service.findByName("Mary");
		Member member = members.get(0);
		assertNotNull(member);
		assertThat(member.getName()).as("Checking %s' name", member.getName()).isEqualTo("Mary");
	}
	
	@Test
	public void testRemove() throws Exception {
		Member tom = new Member(null, "Tom", false);
		service.save(tom);
		
		List<Member> members = service.findByName("Tom");
		Member member = members.get(0);
		assertNotNull(member);
		assertThat(member.getName()).as("Checking %s' name", member.getName()).isEqualTo("Tom");
		
		service.delete(member);
		
		List<Member> membersNull = service.findByName("Tom");
		assertThat(membersNull).as("Checking %s' name", membersNull).isEmpty();
	}
	
	@Test
	public void testUpdateMemberWithActiveFalse() throws Exception {
		
		assertTrue(mary.getActive());
		
		service.updateActive(mary.getName(), false);
		
		Member maryUpdate = service.findByName(mary.getName()).get(0);
		
		assertFalse(maryUpdate.getActive());
	}
	
}    
