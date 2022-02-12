package dev.amlab.moviecatalogservice.resource;

import dev.amlab.moviecatalogservice.models.CatalogItem;
import dev.amlab.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(
			new Rating("1234", 4),
			new Rating("5678", 3)
		);

		// Get all rated movie
		// For each movie ID, call movie info service and get details
		// Put them all together
		return Collections.singletonList(
				new CatalogItem("Ironman", "test", 4)
		);
	}
}
