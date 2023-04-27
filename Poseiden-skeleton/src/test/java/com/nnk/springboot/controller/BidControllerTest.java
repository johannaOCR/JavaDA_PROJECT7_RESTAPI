package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)
@WithMockUser
public class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;


    @Test
    public void addBidFormTest() throws Exception {
        //WHEN
        mockMvc.perform(get("/bidList/add"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void validateTest() throws Exception {
        //WHEN
        mockMvc.perform(post("/bidList/validate").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("account=account&type=type&bidQuantity=10.0")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }
    @Test
    public void homeTest() throws Exception {
        //WHEN
        mockMvc.perform(get("/bidList/list"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidList"));
    }
    @Test
    public void showUpdateFormTest() throws Exception {
        //GIVEN
        BidList bidList = new BidList("account", "type", 10d);
        //WHEN
        when(bidListService.getBidListById(1)).thenReturn(bidList);
        mockMvc.perform(get("/bidList/update/1").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bid"));
    }

    @Test
    public void updateBidTest() throws Exception {
        //GIVEN
        BidList bidList = new BidList("account", "type", 10d);
        //WHEN
        mockMvc.perform(post("/bidList/update/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bidList.toString())
                        .accept(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/update/{id}"));
    }

}
