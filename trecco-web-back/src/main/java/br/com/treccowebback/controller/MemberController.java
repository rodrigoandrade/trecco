package br.com.treccowebback.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treccowebback.model.Member;
import br.com.treccowebback.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author rodrigo.andrade
 *
 */

@Api(value="trecco")
@RestController
@RequestMapping("/member")
public class MemberController {
	
private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService service;

	@ApiOperation(value = "Member List", response = List.class)
	@GetMapping
	public List<Member> getMember(@RequestParam(value = "name", required = true) String name) throws Exception {
		
		log.info("Find Members with name:.. " + name);
		return service.findByName(name);
	}
	
	@ApiOperation(value = "Record Member", response = List.class)
	@PostMapping
	public void save(@RequestBody Member name) {
		service.save(name);
		log.info("Record Member:.." + name);
	}

}
