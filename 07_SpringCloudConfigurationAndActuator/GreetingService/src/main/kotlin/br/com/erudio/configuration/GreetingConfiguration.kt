package br.com.erudio.configuration


import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("greeting-service")
class GreetingConfiguration {
    var greeting: String? = null
    var defaultValue: String? = null
}