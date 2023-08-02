package com.sap.cc.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GreetingController {

	private GreetingService service;
	// private Logger logger = LoggerFactory.getLogger(getClass());

	public GreetingController(GreetingService service) {
		this.service = service;
	}

	@GetMapping("/hello")
	public String greeting(@RequestParam(value = "name", defaultValue = "Worldd") String name) {
		try {
			MDC.put("path", "/hello");
			return getGreeting("Hello", name);
		} finally {
			MDC.clear();
		}
	}

	@GetMapping("/howdy")
	public String deprecatedGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.error("Deprecated endpoint used.");
		return getGreeting("Howdy", name);
	}

	private String getGreeting(String greeting, String name) {
		try {
			return service.createGreetingMessage(greeting, name);
		} catch (IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
		} finally {
		}
	}

}
