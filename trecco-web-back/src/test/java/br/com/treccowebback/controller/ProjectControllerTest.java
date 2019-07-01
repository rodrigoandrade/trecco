package br.com.treccowebback.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.treccowebback.model.Member;
import br.com.treccowebback.model.Project;
import br.com.treccowebback.service.MemberService;
import br.com.treccowebback.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {
	
	private Project project;
	private List<Member> members;

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private MemberService memberService;
	
	@LocalServerPort
	private int port;
	
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void initialize() {
		members = new ArrayList<Member>();
		
		members.add(new Member(null, "Paul", true));
		members.add(new Member(null, "Mary", true));
		members.add(new Member(null, "Tom", true));
		
		members.forEach(member -> {
			try {
				memberService.save(member);
			} catch (Exception e) {
				throw new RuntimeException();  
			}
		});
		
		
		project = new Project(null,"RestTrecco", true, LocalDate.now(), members);
		
		projectService.save(project);
	}
	
	@After
	public void finalize() throws Exception {
		members.forEach(member -> {
			try {
				memberService.delete(member);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		});
		
		projectService.delete(project);
	}
	
	@Test
	public void testFindProject() {
		HttpEntity<Project> entity = new HttpEntity<Project>(project, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(criaURL("/projects?name=" + project.getName()), HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testUpdateProject( ) {
		HttpEntity<Project> entity = new HttpEntity<Project>(project, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(criaURL("/projects?name=" + project.getName() + "&active=false"), HttpMethod.PUT, entity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	private String criaURL(String uri) {
		return String.format("http://localhost:%d%s", port, uri);
	}
}
