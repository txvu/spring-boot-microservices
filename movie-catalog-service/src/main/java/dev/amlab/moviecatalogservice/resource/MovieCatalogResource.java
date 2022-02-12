package dev.amlab.moviecatalogservice.resource;

import dev.amlab.moviecatalogservice.models.CatalogItem;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static java.util.Collection.*;
import static java.util.Collections.singletonList;

@RestController
public class MovieCatalogResource
{
	public List<CatalogItem> getCatalog(String userId) {
		return Collections.singletonList(
				new CatalogItem("Bright", "test", 4)
		);
	}
}
