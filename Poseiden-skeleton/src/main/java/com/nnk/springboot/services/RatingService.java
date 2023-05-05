package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private static final Logger logger = LogManager.getLogger("RatingService");

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRating(){
        return ratingRepository.findAll();
    }

    public void addRating(Rating rating){
        logger.info("Adding rating");
        ratingRepository.save(rating);
    }

    public void updateRating(Rating rating){
        logger.info("Updating rating");
        ratingRepository.save(rating);
    }

    public void deleteRating(Integer id){
        logger.info("Deleting rating");
        ratingRepository.deleteById(id);
    }

    public Optional<Rating> getRatingById(Integer id) {
        return ratingRepository.findById(id);
    }
}
