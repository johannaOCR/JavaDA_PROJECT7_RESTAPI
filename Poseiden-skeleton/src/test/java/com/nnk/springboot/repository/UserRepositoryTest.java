package com.nnk.springboot.repository;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userTest() {
        //GIVEN
        User user = new User("username", "Password!1", "fullname", "role");

        // Save
        User userSaved = userRepository.save(user);
        Assert.assertNotNull(userSaved.getId());
        Assert.assertEquals(userSaved.getUsername(), "username");
        Assert.assertEquals(userSaved.getFullname(), "fullname");
        Assert.assertEquals(userSaved.getPassword(), "Password!1");
        Assert.assertEquals(userSaved.getRole(), "role");

        // Update
        userSaved.setFullname("fullnameUpdated");
        User userUpdated = userRepository.save(userSaved);
        Assert.assertEquals(userSaved.getFullname(), "fullnameUpdated");

        // Find
        List<User> listResult = userRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = userUpdated.getId();
        userRepository.delete(userUpdated);
        Optional<User> userDeleted = userRepository.findById(id);
        Assert.assertFalse(userDeleted.isPresent());
    }
}
