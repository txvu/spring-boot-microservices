package dev.amlab.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	@Bean // Make this object global and final
	@LoadBalanced // Make this server discover other servers based on Eureka
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	//	Another method to make API call
	//	@Bean
//	public WebClient.Builder builder() {
//		return WebClient.builder();
//	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
