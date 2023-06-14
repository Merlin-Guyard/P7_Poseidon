//package com.nnk.springboot.TU;
//
//import com.nnk.springboot.domain.User;
//import com.nnk.springboot.repositories.UserRepository;
//import com.nnk.springboot.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class UserTests {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void clear() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void getAll() {
//
//        // Act
//        User user = new User("Bobby", "psw", "Dupont","USER");
//        userRepository.save(user);
//
//        // Perform
//        List<User> users = userService.getAll();
//
//        //Assert
//        assertNotNull(users.get(0));
//        assertEquals(users.get(0).getUsername(),"Bobby");
//    }
//
//    @Test
//    public void getById() {
//
//        // Act
//        User user = new User("Bobby", "psw", "Dupont","USER");
//        userRepository.save(user);
//
//        // Perform
//        User user1 = userService.getById(user.getId());
//
//        //Assert
//        assertEquals(user1.getUsername(),"Bobby");
//    }
//
//    @Test
//    public void update() {
//
//        // Act
//        User user = new User("Bobby", "psw", "Dupont","USER");
//        User Updated = new User("Bobby2", "psw", "Dupont","USER");
//        userRepository.save(user);
//
//        // Perform
//        userService.updateById(user.getId(), Updated);
//
//        //Assert
//        User user1 = userService.getById(Updated.getId());
//        assertEquals(user1.getUsername(),"Bobby2");
//        assertEquals(user1.getFullname(),"Dupont");
//    }
//
//    @Test
//    public void delete() {
//
//        // Act
//        User user = new User("Bobby", "psw", "Dupont","USER");
//        userRepository.save(user);
//
//        // Perform
//        userService.deleteById(user.getId());
//
//        //Assert
//        List<User> users = userService.getAll();
//        assertThat(users.isEmpty()).isTrue();
//    }
//}