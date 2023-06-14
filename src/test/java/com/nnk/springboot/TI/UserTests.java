package com.nnk.springboot.TI;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddUser() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .with(csrf())
                .param("username", "usernameTest1")
                .param("password", "passwordTest1")
                .param("fullname", "fullnameTest1")
                .param("role", "USERTest1"));

        Optional<User> user = userRepository.findByUsername("usernameTest1");

        //assert
        assertThat(user).isPresent();
        assertThat(user.get().getUsername()).isEqualTo("usernameTest1");
        assertThat(user.get().getFullname()).isEqualTo("fullnameTest1");
        assertThat(user.get().getRole()).isEqualTo("USERTest1");
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetUser() throws Exception {

        //arrange & act, perform a request and create a user
        User user = new User("usernameTest2", "passwordTest2", "fullnameTest2", "USERTest2");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<User> users = (List<User>) result.getModelAndView().getModel().get("users");

                    //assert
                    assertThat(users.get(0).getUsername()).isEqualTo("usernameTest2");
                    assertThat(users.get(0).getFullname()).isEqualTo("fullnameTest2");
                    assertThat(users.get(0).getRole()).isEqualTo("USERTest2");
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateUser() throws Exception {

        //arrange & act, perform a request and create a user
        User user = new User("usernameTest3", "passwordTest3", "fullnameTest3", "USERTest3");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/" + user.getId())
                .with(csrf())
                .param("username", "usernameTest4")
                .param("password", "passwordTest4")
                .param("fullname", "fullnameTest4")
                .param("role", "USERTest4"));

        Optional<User> user2Assert = userRepository.findByUsername("usernameTest4");

        //assert
        assertThat(user2Assert).isPresent();
        assertThat(user2Assert.get().getUsername()).isEqualTo("usernameTest4");
        assertThat(user2Assert.get().getFullname()).isEqualTo("fullnameTest4");
        assertThat(user2Assert.get().getRole()).isEqualTo("USERTest4");
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelUser() throws Exception {

        //arrange & act, perform a request and create a user
        User user = new User("usernameTest5", "passwordTest5", "fullnameTest5", "USERTest5");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/delete/1")
                .with(csrf())
                .param("id", String.valueOf(user.getId())));

        //assert
        assertThat(userRepository.findById(user.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/user/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a user
        User user = new User("usernameTest6", "passwordTest6", "fullnameTest6", "USERTest6");
        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/update/" + user.getId())
                        .with(csrf())
                        .param("id", String.valueOf(user.getId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/update"));
    }
}
