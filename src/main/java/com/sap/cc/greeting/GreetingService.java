package com.sap.cc.greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GreetingService {

	// private Logger logger = LoggerFactory.getLogger(getClass());

	public String createGreetingMessage(String greeting, String name) {
		if (name.matches(".*[0-9].*")) {
			throw new IllegalArgumentException("Name must not contain numbers!");
		}
		log.debug("created greeting name: {}", name);
		return greeting + " " + name;
	}
}