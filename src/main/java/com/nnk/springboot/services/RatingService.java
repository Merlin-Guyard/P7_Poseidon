package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> getAll(){
        return ratingRepository.findAll();
    }

    public Rating getById(int id){
        Optional<Rating> optionalRating= ratingRepository.findById(id);
        if (optionalRating.isEmpty()){
            Logger.info("No rating found by id",id);
            throw new RuntimeException();
        }
        return optionalRating.get();
    }


    public void saveRating(Rating bid) {
        ratingRepository.save(bid);
    }

    public void updateById(Integer id, Rating rating) {
        rating.setId(id);
        ratingRepository.save(rating);
    }

    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
