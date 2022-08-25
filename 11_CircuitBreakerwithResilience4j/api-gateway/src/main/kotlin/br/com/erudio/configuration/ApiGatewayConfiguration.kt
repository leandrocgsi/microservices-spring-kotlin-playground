package br.com.erudio.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfiguration {
    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { p: PredicateSpec ->
                // E aqui nós podemos definer uma function definindo as
                // configurações da rota
                p.path("/get")
                    .filters { f: GatewayFilterSpec ->
                        f
                            // Você pode usar isso pra passar parâmetros de
                            // autenticação redirecionamento etc
                            .addRequestHeader("Hello", "World")
                            .addRequestParameter("Hello", "World")
                    }
                    //Antes do redirecionamento ele adiciona o nosso header
                    .uri("http://httpbin.org:80")
            }
            // E aqui nós passamos no nome do serviço registrado no Apache Zookeeper
            // quando a request chega ao API gateway, pelo path podemos acessar o Apache Zookeeper
            // server encontrar as location e fazer o load balancing

            .route { p: PredicateSpec ->
                p.path("/cambio-service/**")
                    .uri("lb://cambio-service")
            }
            .route { p: PredicateSpec ->
                p.path("/book-service/**")
                    .uri("lb://book-service")
            }
            .build()
    }
}