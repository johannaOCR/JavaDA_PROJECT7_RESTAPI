package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
@WebMvcTest(TradeController.class)
@WithMockUser
public class TradeControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TradeService tradeService;

    @Test
    public void home() throws Exception {
        //WHEN
        mvc.perform(get("/trade/list").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"));
    }
    @Test
    public void addTradeForm() throws Exception {
        //WHEN
        mvc.perform(get("/trade/add").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"))
                .andExpect(model().attributeExists("trade"));
    }
    @Test
    public void validate() throws Exception {
        //WHEN
        mvc.perform(post("/trade/validate").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("account=account&type=type")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }
    @Test
    public void showUpdateForm() throws Exception {
        //GIVEN
        Trade trade = new Trade("account", "type");
        //WHEN
        when(tradeService.getTradeById(1)).thenReturn(Optional.of(trade));
        mvc.perform(get("/trade/update/1").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"));
    }
    @Test
    public void updateTrade() throws Exception {
        //GIVEN
        Trade trade = new Trade("account", "type");
        //WHEN
        when(tradeService.getTradeById(1)).thenReturn(Optional.of(trade));
        mvc.perform(post("/trade/update/1").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("account=account&type=type")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }
    @Test
    public void deleteTrade() throws Exception {
        //GIVEN
        Trade trade = new Trade("account", "type");
        //WHEN
        when(tradeService.getTradeById(1)).thenReturn(Optional.of(trade));
        mvc.perform(get("/trade/delete/1").with(csrf()))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }
}
