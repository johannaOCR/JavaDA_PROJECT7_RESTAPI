package com.nnk.springboot.service;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUser() {
        //GIVEN
        User user = new User("username", "password", "fullname", "role");
        //WHEN
        userService.addUser(user);
        //THEN
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        //GIVEN
        User user = new User("username", "password", "fullname", "role");
        userService.addUser(user);
        user.setFullname("fullname2");
        //WHEN
        userService.updateUser(user);
        //THEN
        verify(userRepository, times(2)).save(user);
    }

    @Test
    public void testGetUserByUserName() {
        //GIVEN
        User user = new User("username", "password", "fullname", "role");
        //WHEN
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User result = userService.getUserByUsername(user.getUsername());
        //THEN
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        assertThat(result.getUsername()).isEqualTo("username");
        assertThat(result.getPassword()).isEqualTo("password");
        assertThat(result.getFullname()).isEqualTo("fullname");
        assertThat(result.getRole()).isEqualTo("role");
    }

    @Test
    public void testLoadUserByUsername() {
        //GIVEN
        User user = new User("username", "password", "fullname", "role");
        //WHEN
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        UserDetails result = userService.loadUserByUsername(user.getUsername());
        //THEN
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        assertThat(result.getUsername()).isEqualTo("username");
        assertThat(result.getPassword()).isEqualTo("password");
    }

    @Test
    public void testDeleteUser() {
        //GIVEN
        User user = new User("username", "password", "fullname", "role");
        user.setId(1);
        Integer id = user.getId();
        //WHEN
        userService.deleteUser(id);
        //THEN
        verify(userRepository, times(1)).deleteById(id);
    }
}
