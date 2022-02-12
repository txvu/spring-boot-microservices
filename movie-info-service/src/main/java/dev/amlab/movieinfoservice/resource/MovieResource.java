package dev.amlab.movieinfoservice.resource;

import dev.amlab.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource
{
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		System.out.println("Return a Movie object");
		return new Movie(movieId, "> Movie name from Movie Service");
	}
}
