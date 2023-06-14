package com.nnk.springboot.TI;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeRepository tradeRepository;

    @BeforeEach
    public void clear() {
        tradeRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddTrade() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .with(csrf())
                .param("account", "accountTest1")
                .param("type", "typeTest1")
                .param("buyQuantity", "11"));

        Optional<Trade> trade = tradeRepository.findByAccount("accountTest1");

        //assert
        assertThat(trade).isPresent();
        assertThat(trade.get().getAccount()).isEqualTo("accountTest1");
        assertThat(trade.get().getType()).isEqualTo("typeTest1");
        assertThat(trade.get().getBuyQuantity()).isEqualTo(11);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetTrade() throws Exception {

        //arrange & act, perform a request and create a trade
        Trade trade = new Trade("accountTest2", "typeTest2", 22);
        tradeRepository.save(trade);

        mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("trades"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<Trade> trades = (List<Trade>) result.getModelAndView().getModel().get("trades");

                    //assert
                    assertThat(trades.get(0).getAccount()).isEqualTo("accountTest2");
                    assertThat(trades.get(0).getType()).isEqualTo("typeTest2");
                    assertThat(trades.get(0).getBuyQuantity()).isEqualTo(22);
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateTrade() throws Exception {

        //arrange & act, perform a request and create a new trade
        Trade trade = new Trade("accountTest3", "typeTest3", 33);
        tradeRepository.save(trade);

        mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/" + trade.getTradeId())
                .with(csrf())
                .param("account", "accountTest4")
                .param("type", "typeTest4")
                .param("buyQuantity", "44"));

        Optional<Trade> trade2Assert = tradeRepository.findByAccount("accountTest4");

        //assert
        assertThat(trade2Assert).isPresent();
        assertThat(trade2Assert.get().getAccount()).isEqualTo("accountTest4");
        assertThat(trade2Assert.get().getType()).isEqualTo("typeTest4");
        assertThat(trade2Assert.get().getBuyQuantity()).isEqualTo(44);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelTrade() throws Exception {

        //arrange & act, perform a request and create a trade
        Trade trade = new Trade("accountTest5", "typeTest5", 55);
        tradeRepository.save(trade);

        mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete/1")
                .with(csrf())
                .param("id", String.valueOf(trade.getTradeId())));

        //assert
        assertThat(tradeRepository.findById(trade.getTradeId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a trade
        Trade trade = new Trade("accountTest6", "typeTest6", 66);
        tradeRepository.save(trade);

        mockMvc.perform(MockMvcRequestBuilders.get("/trade/update/"+ trade.getTradeId())
                        .with(csrf())
                        .param("id", String.valueOf(trade.getTradeId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"));
    }
}
