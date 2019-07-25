package com.sb2.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.VariableDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb2.bo.Employee;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@Slf4j
public class MainController {

	@Autowired
	private ProcessService processService;

	@Autowired
	private RuntimeDataService runtimeDataService;
	
	@Autowired
	private ProducerTemplate producerTemplate;

	@GetMapping(path = "/hello", produces = "text/plain")
	public ResponseEntity<String> hello(@RequestParam String name) {
		log.info("Inside hello");
		Map<String, Object> processParams = new HashMap<>();
		processParams.put("Name", name);
		String val = "";
		long processInstanceId = processService.startProcess("com.sb2:spring-boot-2-jbpm-workflow:0.0.1-SNAPSHOT",
				"com.sample.printname", processParams);

		for (VariableDesc var : runtimeDataService.getVariablesCurrentState(processInstanceId)) {
			log.info("Variable ID: {}; Old Value: {}; New Value {}", var.getVariableId(), var.getOldValue(),
					var.getNewValue());
			val = var.getNewValue();
		}
		return new ResponseEntity<>("Hello " + val, HttpStatus.OK);
	}
	
	@GetMapping(path = "/employee/{id}", produces = "text/plain")
	public ResponseEntity<String> getEmployee(@PathVariable("id") String id) {
		log.info("Inside get_employee");
		return new ResponseEntity<>(invokeGetEmployeeByIdApi(id), HttpStatus.OK);
	}

	private String invokeGetEmployeeByIdApi(String id) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("id", id);
		return (String) producerTemplate.sendBodyAndHeaders("direct:getEmployeeById", ExchangePattern.InOut, null, headers);
	}

}
