package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RatingServiceTest {
    @InjectMocks
    private RatingService ratingService;
    @Mock
    RatingRepository ratingRepository;

    @Test
    public void testAddRating() {
        // GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating",
                "Fitch Rating", 10);
        // WHEN
        ratingService.addRating(rating);
        // THEN
        verify(ratingRepository,times(1)).save(rating);
    }
    @Test
    public void testGetRatingById() {
        // GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
        Integer id = rating.getId();
        // WHEN
        when(ratingRepository.findById(id)).thenReturn(java.util.Optional.of(rating));
        Rating result = ratingService.getRatingById(id).get();
        // THEN
        verify(ratingRepository,times(1)).findById(id);
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getMoodysRating()).isEqualTo("Moodys Rating");
        assertThat(result.getSandPRating()).isEqualTo("Sand PRating");
        assertThat(result.getFitchRating()).isEqualTo("Fitch Rating");
        assertThat(result.getOrderNumber()).isEqualTo(10);
    }
    @Test
    public void testGetAllRating() {
        // WHEN
        ratingService.getAllRating();
        // THEN
        verify(ratingRepository,times(1)).findAll();
    }
    @Test
    public void testUpdateRating() {
        // GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
        ratingService.addRating(rating);
        rating.setMoodysRating("Moodys Rating 2");
        // WHEN
        ratingService.updateRating(rating);
        // THEN
        verify(ratingRepository,times(2)).save(rating);
    }
    @Test
    public void testDeleteRating() {
        // GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating.setId(1);
        Integer id = rating.getId();
        // WHEN
        ratingService.deleteRating(id);
        // THEN
        verify(ratingRepository,times(1)).deleteById(id);
    }

}
