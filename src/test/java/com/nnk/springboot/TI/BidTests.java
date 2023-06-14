package com.nnk.springboot.TI;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

    @BeforeEach
    public void clear() {
        bidListRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddBidList() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .with(csrf())
                .param("account", "accountTest1")
                .param("type", "typeTest1")
                .param("bidQuantity", "11"));

        Optional<BidList> bidList = bidListRepository.findByAccount("accountTest1");

        //assert
        assertThat(bidList).isPresent();
        assertThat(bidList.get().getAccount()).isEqualTo("accountTest1");
        assertThat(bidList.get().getType()).isEqualTo("typeTest1");
        assertThat(bidList.get().getBidQuantity()).isEqualTo(11);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetBidList() throws Exception {

        //arrange & act, perform a request and create a bid
        BidList bidList = new BidList("accountTest2", "typeTest2", 22);
        bidListRepository.save(bidList);

        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidLists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<BidList> bidLists = (List<BidList>) result.getModelAndView().getModel().get("bidLists");

                    //assert
                    assertThat(bidLists.get(0).getAccount()).isEqualTo("accountTest2");
                    assertThat(bidLists.get(0).getType()).isEqualTo("typeTest2");
                    assertThat(bidLists.get(0).getBidQuantity()).isEqualTo(22);
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateBidList() throws Exception {

        //arrange & act, perform a request and create a new bid
        BidList bidList = new BidList("accountTest3", "typeTest3", 33);
        bidListRepository.save(bidList);

        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/" + bidList.getBidListId())
                .with(csrf())
                .param("account", "accountTest4")
                .param("type", "typeTest4")
                .param("bidQuantity", "44"));

        Optional<BidList> bidList2Assert = bidListRepository.findByAccount("accountTest4");

        //assert
        assertThat(bidList2Assert).isPresent();
        assertThat(bidList2Assert.get().getAccount()).isEqualTo("accountTest4");
        assertThat(bidList2Assert.get().getType()).isEqualTo("typeTest4");
        assertThat(bidList2Assert.get().getBidQuantity()).isEqualTo(44);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelBidList() throws Exception {

        //arrange & act, perform a request and create a new bid
        BidList bidList = new BidList("accountTest5", "typeTest5", 55);
        bidListRepository.save(bidList);

        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete/1")
                .with(csrf())
                .param("id", String.valueOf(bidList.getBidListId())));

        //assert
        assertThat(bidListRepository.findById(bidList.getBidListId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a new bid
        BidList bidList = new BidList("accountTest5", "typeTest5", 55);
        bidListRepository.save(bidList);

        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update/" + bidList.getBidListId())
                        .with(csrf())
                        .param("id", String.valueOf(bidList.getBidListId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"));
    }

}
