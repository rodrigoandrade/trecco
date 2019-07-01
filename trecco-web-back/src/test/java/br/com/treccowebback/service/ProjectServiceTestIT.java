package br.com.treccowebback.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.treccowebback.model.Member;
import br.com.treccowebback.model.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTestIT {

	Project project;
	List<Member> members;
	
	@Autowired
	private ProjectService service;

	@Before
	public void initialize() {
		Member mary = Member.builder().name("Mary").active(true).build();
		Member paul = Member.builder().name("Paul").active(false).build();
		members = Arrays.asList(mary, paul);
		
		project = new Project(null, "Test_Project", true, LocalDate.now(), members);
	}
	
	public void save() {
		service.save(project);
	}
	
}
