package com.ivl.movierating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivl.movierating.model.Rating;
import com.ivl.movierating.repositories.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public List<Rating> getAllRatings() {

		return (List<Rating>) ratingRepository.findAll();
	}

	@Override
	public List<Rating> findByName(String name) {

		return ratingRepository.findByMovieName(name);
	}

	@Override
	public Rating getById(Long id) {

		return ratingRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteRating(Long id) {
		ratingRepository.deleteById(id);
	}

	@Override
	public boolean addRating(Rating rating) {

		return ratingRepository.save(rating)!=null;
	}

	@Override
	public boolean updateRating(Rating rating) {

		return ratingRepository.save(rating)!=null;
	}

}
