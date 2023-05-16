package com.nnk.springboot.controller;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
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
@WebMvcTest(UserController.class)
@WithMockUser
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;


    @Test
    public void home() throws Exception {
        //WHEN
        mvc.perform(get("/user/list").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }
    @Test
    public void addUser() throws Exception {
        //WHEN
        mvc.perform(get("/user/add").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"));
    }
    @Test
    public void validate() throws Exception {
        //WHEN
        mvc.perform(post("/user/validate").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("fullname=fullname&username=username&password=Password!123&role=USER")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list")); }
    @Test
    public void showUpdateForm() throws Exception {
        //GIVEN
        User user = new User("username", "fullname", "password", "USER");
        //WHEN
        when(userService.getUserById(1)).thenReturn(Optional.of(user));
        mvc.perform(get("/user/update/1").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }
    @Test
    public void updateUser() throws Exception {
        //WHEN
        mvc.perform(post("/user/update/1").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("username=username&fullname=fullname&password=Password!1&role=USER")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }
    @Test
    public void deleteUser() throws Exception {
        //GIVEN
        User user = new User("username", "fullname", "password", "USER");
        //WHEN
        when(userService.getUserById(1)).thenReturn(Optional.of(user));
        mvc.perform(get("/user/delete/1").with(csrf()))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }
}
