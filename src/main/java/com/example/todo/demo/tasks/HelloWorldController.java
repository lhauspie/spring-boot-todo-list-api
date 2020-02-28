package com.example.todo.demo.tasks;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class HelloWorldController {

	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> hello() {
		return Flux.range(1,10)
				.map(i -> "Hello World " + i + " !\n");
	}

	@GetMapping(value = "/bye", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> bye() {
		return Flux.just("Good Bye");
	}
}
