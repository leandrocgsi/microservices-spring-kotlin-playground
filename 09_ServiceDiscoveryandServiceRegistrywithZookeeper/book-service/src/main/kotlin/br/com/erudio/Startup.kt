package br.com.erudio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
class Startup

fun main(args: Array<String>) {
	runApplication<Startup>(*args)
}
