package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    private static final Logger logger = LogManager.getLogger("RuleNameService");
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> getAllRuleName() {
        return ruleNameRepository.findAll();
    }

    public void addRuleName(RuleName ruleName) {
        logger.info("Adding ruleName");
        ruleNameRepository.save(ruleName);
    }

    public void updateRuleName(RuleName ruleName) {
        logger.info("Updating ruleName");
        ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
        logger.info("Deleting ruleName");
        ruleNameRepository.deleteById(id);
    }

    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }
}
