package br.com.treccowebback.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
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
import br.com.treccowebback.model.Project;
import br.com.treccowebback.repository.impl.MemberRepositoryImpl;
import br.com.treccowebback.repository.impl.ProjectRepositoryImpl;

/**
 * @author rodrigo.andrade
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTestIT {
	
	private Project project;
	private List<Member> members;
	
	@Autowired
	private ProjectRepositoryImpl projectRepository;
	
	@Autowired
	private MemberRepositoryImpl memberRepository;
	
	@Before
	public void initialize() {
		members = new ArrayList<Member>();
		
		members.add(new Member(null, "Paul", true));
		members.add(new Member(null, "Mary", true));
		members.add(new Member(null, "Tom", true));
		
		members.forEach(member -> {
			try {
				memberRepository.save(member);
			} catch (Exception e) {
				throw new RuntimeException();  
			}
		});
		
		
		project = new Project(null,"RestTrecco", true, LocalDate.now(), members);
		
		projectRepository.save(project);
	}
	
	@After
	public void finalize() {
		members.forEach(member -> {
			try {
				memberRepository.delete(member);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});
		
		projectRepository.delete(project);
	}
	
	@Test
	public void testProjectSave() {
		Project project = projectRepository.findById(this.project.getId());
		
		assertEquals("RestTrecco", project.getName());
		assertEquals(true, project.getActive());
		
		List<Member> members = project.getMembers();
		assertThat(members).hasSize(3).extracting("name", "active")
				.contains(tuple("Paul", true),
						  tuple("Mary", true),
						  tuple("Tom", true));
	}
	
	@Test
	public void testDelete() {
		Project project = new Project(null,"TESTE", true, LocalDate.now(), members);
		project = projectRepository.save(project);
		
		projectRepository.delete(project);
		
		assertNull(projectRepository.findById(project.getId()));
	}
	
	@Test
	public void testUpdateActive() throws Exception {
		projectRepository.updateActive(project.getName(), false);
		
		Project projectRest = projectRepository.findByName(project.getName()).get(0);
		assertThat(projectRest.getName()).as("Checking %s' name", projectRest.getName()).isEqualTo("Joana");
		assertThat(projectRest.getActive()).as("Checking %s' active", projectRest.getActive()).isEqualTo(false);
	}

}
