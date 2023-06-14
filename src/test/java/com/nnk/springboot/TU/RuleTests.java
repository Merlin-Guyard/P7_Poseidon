package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class RuleTests {

    @Autowired
    private RuleNameService ruleNameService;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @BeforeEach
    public void clear() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void getAll() {

        // Act
        RuleName ruleName = new RuleName("Name Test", "Description Test", "Template Test");
        ruleNameRepository.save(ruleName);

        // Perform
        List<RuleName> ruleNames = ruleNameService.getAll();

        //Assert
        assertNotNull(ruleNames.get(0));
        assertEquals(ruleNames.get(0).getName(),"Name Test");
    }

    @Test
    public void getById() {

        // Act
        RuleName ruleName = new RuleName("Name Test", "Description Test", "Template Test");
        ruleNameRepository.save(ruleName);

        // Perform
        RuleName ruleName1 = ruleNameService.getById(ruleName.getId());

        //Assert
        assertEquals(ruleName1.getName(),"Name Test");
    }

    @Test
    public void update() {

        // Act
        RuleName ruleName = new RuleName("Name Test", "Description Test", "Template Test");
        RuleName ruleNameUpdated = new RuleName("Name Test2", "Description Test", "Template Test");
        ruleNameRepository.save(ruleName);

        // Perform
        ruleNameService.updateById(ruleName.getId(), ruleNameUpdated);

        //Assert
        RuleName ruleName1 = ruleNameService.getById(4);
        assertEquals(ruleName1.getName(),"Name Test2");
        assertEquals(ruleName1.getDescription(),"Description Test");
    }

    @Test
    public void delete() {

        // Act
        RuleName ruleName = new RuleName("Name Test", "Description Test", "Template Test");
        ruleNameRepository.save(ruleName);

        // Perform
        ruleNameService.deleteById(ruleName.getId());

        //Assert
        List<RuleName> ruleNames = ruleNameService.getAll();
        assertThat(ruleNames.isEmpty()).isTrue();
    }
}
