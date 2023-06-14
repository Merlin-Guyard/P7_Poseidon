package com.nnk.springboot.TI;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RatingTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingRepository ratingRepository;

    @BeforeEach
    public void clear(){
        ratingRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddRating() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .with(csrf())
                .param("moodysRating", "moodysRatingTest1")
                .param("fitchRating", "fitchRatingTest1")
                .param("orderNumber", "11"));

        Optional<Rating> rating = ratingRepository.findByMoodysRating("moodysRatingTest1");

        //assert
        assertThat(rating).isPresent();
        assertThat(rating.get().getMoodysRating()).isEqualTo("moodysRatingTest1");
        assertThat(rating.get().getFitchRating()).isEqualTo("fitchRatingTest1");
        assertThat(rating.get().getOrderNumber()).isEqualTo(11);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetRating() throws Exception {

        //arrange & act, perform a request and create a rating
        Rating rating = new Rating("moodysRatingTest2", "fitchRatingTest2", 22);
        ratingRepository.save(rating);

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ratings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<Rating> ratings = (List<Rating>) result.getModelAndView().getModel().get("ratings");

                    //assert
                    assertThat(ratings.get(0).getMoodysRating()).isEqualTo("moodysRatingTest2");
                    assertThat(ratings.get(0).getFitchRating()).isEqualTo("fitchRatingTest2");
                    assertThat(ratings.get(0).getOrderNumber()).isEqualTo(22);
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateRating() throws Exception {

        //arrange & act, perform a request and create a new rating
        Rating rating = new Rating("moodysRatingTest3", "fitchRatingTest3", 33);
        ratingRepository.save(rating);

        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/" + rating.getId())
                .with(csrf())
                .param("moodysRating", "moodysRatingTest4")
                .param("fitchRating", "fitchRatingTest4")
                .param("orderNumber", "44"));

        Optional<Rating> rating2Assert = ratingRepository.findByMoodysRating("moodysRatingTest4");

        //assert
        assertThat(rating2Assert).isPresent();
        assertThat(rating2Assert.get().getMoodysRating()).isEqualTo("moodysRatingTest4");
        assertThat(rating2Assert.get().getFitchRating()).isEqualTo("fitchRatingTest4");
        assertThat(rating2Assert.get().getOrderNumber()).isEqualTo(44);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelRating() throws Exception {

        //arrange & act, perform a request and create a new rating
        Rating rating = new Rating("moodysRatingTest5", "fitchRatingTest5", 55);
        ratingRepository.save(rating);

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete/1")
                .with(csrf())
                .param("id", String.valueOf(rating.getId())));

        //assert
        assertThat(ratingRepository.findById(rating.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a new rating
        Rating rating = new Rating("moodysRatingTest6", "fitchRatingTest6", 66);
        ratingRepository.save(rating);

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/update/1")
                        .with(csrf())
                        .param("id", String.valueOf(rating.getId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/update"));
    }
}
