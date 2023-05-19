package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RuleNameServiceTest {
    @Mock
    RuleNameRepository ruleNameRepository;
    @InjectMocks
    private RuleNameService ruleNameService;

    @Test
    public void addRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json",
                "Template", "SQL", "SQL Part");
        //WHEN
        ruleNameService.addRuleName(ruleName);
        //THEN
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void updateRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json",
                "Template", "SQL", "SQL Part");
        //WHEN
        ruleNameService.updateRuleName(ruleName);
        //THEN
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void getAllRuleNameTest() {
        //WHEN
        ruleNameService.getAllRuleName();
        //THEN
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    public void getRuleNameByIdTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleName.setId(1);
        Integer id = ruleName.getId();
        //WHEN
        when(ruleNameRepository.findById(id)).thenReturn(Optional.of(ruleName));
        RuleName result = ruleNameService.getRuleNameById(1).get();
        //THEN
        verify(ruleNameRepository, times(1)).findById(1);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Rule Name");
        assertThat(result.getDescription()).isEqualTo("Description");
        assertThat(result.getJson()).isEqualTo("Json");
        assertThat(result.getTemplate()).isEqualTo("Template");
        assertThat(result.getSqlStr()).isEqualTo("SQL");
        assertThat(result.getSqlPart()).isEqualTo("SQL Part");
    }

    @Test
    public void deleteByIdRuleNameTest() {
        //GIVEN
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleName.setId(1);
        //WHEN
        ruleNameService.deleteRuleName(1);
        //THEN
        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}
