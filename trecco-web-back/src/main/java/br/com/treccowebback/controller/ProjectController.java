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

import br.com.treccowebback.model.Project;
import br.com.treccowebback.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author rodrigo.andrade
 *
 */

@Api(value="trecco")
@RestController
@RequestMapping("/project")
public class ProjectController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProjectService service;

	@ApiOperation(value = "Project List", response = List.class)
	@GetMapping
	public List<Project> getProject(@RequestParam(value = "name", required = true) String name) throws Exception {
		
		log.info("Find Projects:..");
		return service.listProjecstBy(name);
	}
	
	@ApiOperation(value = "Record Project", response = List.class)
	@PostMapping
	public void save(@RequestBody Project project) {
		service.save(project);
		log.info("Record Project:.." + project);
	}
	
}
