package com.ivl.movierating.resorces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ivl.movierating.exception.RecordNotFoundException;
import com.ivl.movierating.model.Rating;
import com.ivl.movierating.service.RatingService;

@RestController
@RequestMapping("/movieratings")

public class RatingResource {

	@Autowired
	private RatingService ratingService;// Service which will do all data retrieval/manipulation work

	// ---------------------Retrieve All Movie ratings---------

	@RequestMapping()
	public ResponseEntity<List<Rating>> listAllRatings() {

		List<Rating> allMovieRating = ratingService.getAllRatings();
		System.out.println("Movie list :" + allMovieRating);
		if (allMovieRating.isEmpty()) {
			return new ResponseEntity<List<Rating>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Rating>>(allMovieRating, HttpStatus.OK);
	}

	// -----------------Retrieve Single movie Rating------------
	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRating(@PathVariable("movieId") long id) {
		// System.out.println("Fetching User with id {}" + id);
		Rating movieRating = ratingService.getById(id);
		if (movieRating == null) {
			System.out.println("User with id {} not found." + id);
			throw new RecordNotFoundException("User with id " + id + " not found");
		}
		return new ResponseEntity<Rating>(movieRating, HttpStatus.OK);
	}

	// -------------------Create/add a Movie Rating ---------------

	@RequestMapping(value = "/rating", method = RequestMethod.POST)
	public ResponseEntity<?> addMovieRating(@RequestBody Rating rating) throws Exception {
		// logger.info("Creating rating : {}", rating);
		Rating movieRating = ratingService.getById(rating.getId());
		if (movieRating != null) {
			System.out.println("Unable to create. A rating with name {} already exist " + rating);
			throw new Exception("Unable to create. A rating with name " + rating.getId() + " already exist.");
		}
		ratingService.addRating(rating);

		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(ucBuilder.path("/api/rating/{id}").buildAndExpand(rating.
		 * getId()).toUri());
		 */return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}

	// ------------------- Update a Rating--------------------------------

	@RequestMapping(value = "/rating/{movieId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("movieId") long id, @RequestBody Rating rating) {
		System.out.println("Updating rating with id {}" + id);

		Rating ratingToupdate = ratingService.getById(id);

		if (ratingToupdate == null) {
			System.out.println("Unable to update. User with id {} not found." + id);
			throw new RecordNotFoundException(
					"Unable to update movie rating. Movie rating with id " + id + " not found.");
		}

		ratingToupdate.setMovieName(rating.getMovieName());
		ratingToupdate.setRating(rating.getRating());

		ratingService.updateRating(ratingToupdate);
		return new ResponseEntity<Rating>(ratingToupdate, HttpStatus.OK);
	}

	// ------------------- Delete a Movie Rating -------------------------

	@RequestMapping(value = "/rating/{movieId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMovieRating(@PathVariable("movieId") long id) {
		System.out.println("Fetching & Deleting User with id {} :" + id);
		Rating ratingToDelete = ratingService.getById(id);
		if (ratingToDelete == null) {
			System.out.println("Unable to update. User with id {} not found." + id);
			throw new RecordNotFoundException(
					"Unable to delete movie rating. Movie rating with id " + id + " not found.");
		}

		ratingService.deleteRating(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}