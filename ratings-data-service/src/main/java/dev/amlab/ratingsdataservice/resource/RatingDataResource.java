package dev.amlab.ratingsdataservice.resource;

import dev.amlab.ratingsdataservice.models.Rating;
import dev.amlab.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource
{
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		return new Rating(movieId, 4);
	}

	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{

		List<Rating> ratings = Arrays.asList(
				new Rating("1234-from-rating-service", 4),
				new Rating("5678-from-rating-service", 3)
		);

		System.out.println("> Return a UserRating object");

		return new UserRating(ratings);
	}
}
