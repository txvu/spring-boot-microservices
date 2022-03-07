package dev.amlab.moviecatalogservice.resource;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dev.amlab.moviecatalogservice.models.CatalogItem;
import dev.amlab.moviecatalogservice.models.Movie;
import dev.amlab.moviecatalogservice.models.Rating;
import dev.amlab.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
	@Autowired
	private RestTemplate restTemplate;

//	Another method to make API call
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{


		UserRating userRating = getUserRating(userId);

		System.out.println("> Return a Catalog object");

		// Get all rated movie
		// For each movie ID, call movie info service and get details
		// Put them all together
		return userRating.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

//			Movie movie = webClientBuilder.build()
//					.get()
//					.uri("http://localhost:8200/movies/" + rating.getMovieId())
//					.retrieve()
//					.bodyToMono(Movie.class)
//					.block();

			return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList());


//		return Collections.singletonList(
//				new CatalogItem("Ironman", "test", 4)
//		);
	}

	// put fallback annotation here
	private UserRating getUserRating(String userId)
	{
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
	}

	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId)
	{
		return Arrays.asList(new CatalogItem("No movie", "No description", 0));
	}
}
