package br.com.treccowebback.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.treccowebback.model.Member;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private MemberController memberController;
	
	@LocalServerPort
	private int port;
	
	HttpHeaders headers = new HttpHeaders();

	private Member mary;
	private Member paul;
	
	@Before
	public void initialize() {
		
		mary = Member.builder().name("Mary").active(true).build();
		paul = Member.builder().name("Paul").active(false).build();
		
		memberController.save(mary);
		memberController.save(paul);
	}

	@After
	public void finalize() {
		memberController.delete(mary);
		memberController.delete(paul);
	}
	
	@Test
	public void testFindMemberByName() {
		HttpEntity<Member> entity = new HttpEntity<Member>(mary, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(criaURL("/members?name=" + mary.getName()), HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testSaveAndDeleteMember() throws Exception {
		Member frank = Member.builder().name("Frank").active(true).build();
		HttpEntity<Member> entitySave = new HttpEntity<Member>(frank, headers);
		
		ResponseEntity<String> responseSave = restTemplate.exchange(
				criaURL("/members"),
				HttpMethod.POST, entitySave, String.class);
		
		frank = memberController.getMember(frank.getName()).get(0);
		HttpEntity<Member> entityDelete = new HttpEntity<Member>(frank, headers);
		
		assertThat(responseSave.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
		
		ResponseEntity<String> responseDelete = restTemplate.exchange(
				criaURL("/members"),
				HttpMethod.DELETE, entityDelete, String.class);
		
		assertThat(responseDelete.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	public void testUpdateMember() {
		
		HttpEntity<Member> entity = new HttpEntity<Member>(mary, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				criaURL("/members?name=" + mary.getName() + "&active=false"),
				HttpMethod.PUT, entity, String.class);

		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
	}
	
	private String criaURL(String uri) {
		return String.format("http://localhost:%d%s", port, uri);
	}
	
}
