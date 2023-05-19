package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
@WebMvcTest(RuleNameController.class)
@WithMockUser
public class RuleNameControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RuleNameService ruleNameService;

    @Test
    public void home() throws Exception {
        //WHEN
        mvc.perform(get("/ruleName/list").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"));
    }

    @Test
    public void addRuleNameForm() throws Exception {
        //WHEN
        mvc.perform(get("/ruleName/add").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void validate() throws Exception {
        //WHEN
        mvc.perform(post("/ruleName/validate").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("name=name&description=description&json=json&template=template&sqlStr=sqlStr&sqlPart=sqlPart")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void showUpdateForm() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
        //WHEN
        when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(ruleName));
        mvc.perform(get("/ruleName/update/1").with(csrf()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void updateRuleName() throws Exception {
        //GIVEN
        RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
        //WHEN
        when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(ruleName));
        mvc.perform(post("/ruleName/update/1").with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("name=name&description=description&json=json&template=template&sqlStr=sqlStr&sqlPart=sqlPart")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void deleteRuleName() throws Exception {
        //WHEN
        mvc.perform(get("/ruleName/delete/1").with(csrf()))
                //THEN
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

}
