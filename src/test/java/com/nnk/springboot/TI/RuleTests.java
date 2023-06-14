package com.nnk.springboot.TI;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RuleTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @BeforeEach
    public void clear(){
        ruleNameRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddRuleName() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .with(csrf())
                .param("name", "nameTest1")
                .param("description", "descriptionTest1")
                .param("template", "templateTest1"));

        Optional<RuleName> ruleName = ruleNameRepository.findByName("nameTest1");

        //assert
        assertThat(ruleName).isPresent();
        assertThat(ruleName.get().getName()).isEqualTo("nameTest1");
        assertThat(ruleName.get().getDescription()).isEqualTo("descriptionTest1");
        assertThat(ruleName.get().getTemplate()).isEqualTo("templateTest1");
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetRuleName() throws Exception {

        //arrange & act, perform a request and create a rule name
        RuleName ruleName = new RuleName("nameTest2", "descriptionTest2", "templateTest2");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleNames"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<RuleName> ruleNames = (List<RuleName>) result.getModelAndView().getModel().get("ruleNames");

                    //assert
                    assertThat(ruleNames.get(0).getName()).isEqualTo("nameTest2");
                    assertThat(ruleNames.get(0).getDescription()).isEqualTo("descriptionTest2");
                    assertThat(ruleNames.get(0).getTemplate()).isEqualTo("templateTest2");
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateRuleName() throws Exception {

        //arrange & act, perform a request and create a rule name
        RuleName ruleName = new RuleName("nameTest3", "descriptionTest3", "templateTest3");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/" + ruleName.getId())
                .with(csrf())
                .param("name", "nameTest4")
                .param("description", "descriptionTest4")
                .param("template", "templateTest4"));

        Optional<RuleName> ruleName2Assert = ruleNameRepository.findByName("nameTest4");

        //assert
        assertThat(ruleName2Assert).isPresent();
        assertThat(ruleName2Assert.get().getName()).isEqualTo("nameTest4");
        assertThat(ruleName2Assert.get().getDescription()).isEqualTo("descriptionTest4");
        assertThat(ruleName2Assert.get().getTemplate()).isEqualTo("templateTest4");
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelRuleName() throws Exception {

        //arrange & act, perform a request and create a rule name
        RuleName ruleName = new RuleName("nameTest5", "descriptionTest5", "templateTest5");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/1")
                .with(csrf())
                .param("id", String.valueOf(ruleName.getId())));

        //assert
        assertThat(ruleNameRepository.findById(ruleName.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a rule name
        RuleName ruleName = new RuleName("nameTest6", "descriptionTest6", "templateTest6");
        ruleNameRepository.save(ruleName);

        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update/" + ruleName.getId())
                        .with(csrf())
                        .param("id", String.valueOf(ruleName.getId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"));
    }
}
