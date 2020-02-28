package com.example.todo.demo.users.reactive;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class HelloWorldController {

    @GetMapping(value ="/helloworld", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity> sayHelloWorld(){
        Flux.just(1, 2, 0)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                .onErrorReturn("Divided by zero :(");

        return Mono.just(ResponseEntity.status(HttpStatus.OK).body("Hello World"));
    }

    @GetMapping(value ="/helloworld2", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<String> sayHelloWorld2(){
        Flux<String> flux = Flux.just("Hello", "World")
                                .doOnNext(System.out::println)
                                .doOnNext(s -> {
                                    try {
                                        Thread.sleep(10000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                })
                .doOnComplete(() -> System.out.println("Terminé"));

        return flux;
    }

}
