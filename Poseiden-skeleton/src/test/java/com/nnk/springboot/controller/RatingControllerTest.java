package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
@WithMockUser
public class RatingControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RatingService ratingService;

    @Test
    public void home() throws Exception {
        //WHEN
        mvc.perform(get("/rating/list").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    public void addRatingForm() throws Exception {
        //WHEN
        mvc.perform(get("/rating/add").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void validate() throws Exception {
        //WHEN
        mvc.perform(post("/rating/validate").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("moodysRating=test&sandPRating=test&fitchRating=test&orderNumber=1")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void showUpdateForm() throws Exception {
        //GIVEN
        Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 1);
        //WHEN
        when(ratingService.getRatingById(1)).thenReturn(Optional.of(rating));
        mvc.perform(get("/rating/update/1").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void updateRatingTest() throws Exception {
        //GIVEN
        Rating rating = new Rating("test", "test", "test", 1);
        //WHEN
        mvc.perform(post("/rating/update/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rating.toString())
                        .accept(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/update/{id}"));
    }

    @Test
    public void deleteRating() throws Exception {
        //GIVEN
        Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 1);
        //WHEN
        when(ratingService.getRatingById(1)).thenReturn(Optional.of(rating));
        mvc.perform(get("/rating/delete/1").with(csrf()))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }
}
