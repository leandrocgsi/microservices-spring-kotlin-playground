package br.com.erudio.controller

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("book-service")
class FooBarController {

    private val logger = LoggerFactory.getLogger(FooBarController::class.java)

    @GetMapping("/v7/foo-bar")
    @Bulkhead(name = "default")
    fun fooBarV7(): String {
        logger.info("Request to foo-bar is received!")
        return "Foo-Bar!!!"
    }

    @GetMapping("/v6/foo-bar")
    @RateLimiter(name = "default")
    fun fooBarV6(): String {
        logger.info("Request to foo-bar is received!")
        return "Foo-Bar!!!"
    }

    @GetMapping("/v5/foo-bar")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    fun fooBarV5(): String? {
        logger.info("Request to foo-bar is received!")
        val response = RestTemplate()
            .getForEntity("http://localhost:8080/foo-bar", String::class.java)
        return response.body
    }

    @GetMapping("/v4/foo-bar")
    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
    fun fooBarV4(): String? {
        logger.info("Request to Foo-Bar is received!")
        val response = RestTemplate()
            .getForEntity("http://localhost:8080/foo-bar", String::class.java)
        // return "Foo-bar!!!";
        return response.body
    }

    @GetMapping("/v3/foo-bar")
    @Retry(name = "default")
    fun fooBarV3(): String? {
        logger.info("Request to Foo-Bar is received!")
        val response = RestTemplate()
            .getForEntity("http://localhost:8080/foo-bar", String::class.java)
        // return "Foo-bar!!!";
        return response.body
    }

    @GetMapping("/v2/foo-bar")
    fun fooBarV2(): String? {
        val response = RestTemplate()
            .getForEntity("http://localhost:8080/foo-bar", String::class.java)
        // return "Foo-bar!!!";
        return response.body
    }

    @GetMapping("/v1/foo-bar")
    fun fooBarV1(): String {
        RestTemplate()
            .getForEntity("http://localhost:8080/foo-bar", String::class.java)
        return "Foo-bar!!!"
    }

    @GetMapping("/v0/foo-bar")
    fun fooBarV0(): String {
        return "Foo-Bar!!!"
    }

    fun fallbackMethod(ex: Exception?): String {
        return "fallbackMethod foo-bar!!!"
    }
}