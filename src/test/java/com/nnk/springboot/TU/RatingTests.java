package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class RatingTests {

    @Autowired
    private RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    @BeforeEach
    public void clear() {
        ratingRepository.deleteAll();
    }

    @Test
    public void getAll() {

        // Act
        Rating rating = new Rating("Moody Test", "Fitch Test", 11);
        ratingRepository.save(rating);

        // Perform
        List<Rating> ratings = ratingService.getAll();

        //Assert
        assertNotNull(ratings.get(0));
        assertEquals(ratings.get(0).getMoodysRating(),"Moody Test");
    }

    @Test
    public void getById() {

        // Act
        Rating rating = new Rating("Moody Test", "Fitch Test", 11);
        ratingRepository.save(rating);

        // Perform
        Rating rating1 = ratingService.getById(rating.getId());

        //Assert
        assertEquals(rating1.getMoodysRating(),"Moody Test");
    }

    @Test
    public void update() {

        // Act
        Rating rating = new Rating("Moody Test", "Fitch Test", 11);
        Rating ratingUpdated = new Rating("Moody Test2", "Fitch Test", 11);
        ratingRepository.save(rating);

        // Perform
        ratingService.updateById(rating.getId(), ratingUpdated);

        //Assert
        Rating rating1 = ratingService.getById(ratingUpdated.getId());
        assertEquals(rating1.getMoodysRating(),"Moody Test2");
        assertEquals(rating1.getOrderNumber(),11);
    }

    @Test
    public void delete() {

        // Act
        Rating rating = new Rating("Moody Test", "Fitch Test", 11);
        ratingRepository.save(rating);

        // Perform
        ratingService.deleteById(rating.getId());

        //Assert
        List<Rating> ratings = ratingService.getAll();
        assertThat(ratings.isEmpty()).isTrue();
    }
}
