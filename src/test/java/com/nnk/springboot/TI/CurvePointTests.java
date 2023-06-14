package com.nnk.springboot.TI;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @BeforeEach
    public void clear(){
        curvePointRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testAddCurvePoint() throws Exception {

        //arrange & act, perform a create request
        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate")
                .with(csrf())
                .param("curveId", "11")
                .param("term", "111")
                .param("valueCP", "1111"));

        Optional<CurvePoint> curvePoint = curvePointRepository.findByCurveId("11");

        //assert
        assertThat(curvePoint).isPresent();
        assertThat(curvePoint.get().getCurveId()).isEqualTo(11);
        assertThat(curvePoint.get().getTerm()).isEqualTo(111);
        assertThat(curvePoint.get().getValueCP()).isEqualTo(1111);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testGetCurvePoint() throws Exception {

        //arrange & act, perform a request and create a curvePoint
        CurvePoint curvePoint = new CurvePoint(22, 222, 2222);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoints"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    List<CurvePoint> curvePointList = (List<CurvePoint>) result.getModelAndView().getModel().get("curvePoints");

                    //assert
                    assertThat(curvePointList.get(0).getCurveId()).isEqualTo(22);
                    assertThat(curvePointList.get(0).getTerm()).isEqualTo(222);
                    assertThat(curvePointList.get(0).getValueCP()).isEqualTo(2222);
                });
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testUpdateCurvePoint() throws Exception {

        //arrange & act, perform a request and create a new CurvePoint
        CurvePoint curvePoint = new CurvePoint(33, 333, 3333);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/" + curvePoint.getId())
                .with(csrf())
                .param("curveId", "44")
                .param("term", "444")
                .param("valueCP", "4444"));

        Optional<CurvePoint> curvePoint2Assert = curvePointRepository.findByCurveId("44");

        //assert
        assertThat(curvePoint2Assert).isPresent();
        assertThat(curvePoint2Assert.get().getCurveId()).isEqualTo(44);
        assertThat(curvePoint2Assert.get().getTerm()).isEqualTo(444);
        assertThat(curvePoint2Assert.get().getValueCP()).isEqualTo(4444);
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    public void testDelCurvePoint() throws Exception {

        //arrange & act, perform a request and create a new CurvePoint
        CurvePoint curvePoint = new CurvePoint(55, 555, 5555);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/1")
                .with(csrf())
                .param("id", String.valueOf(curvePoint.getId())));

        //assert
        assertThat(curvePointRepository.findById(curvePoint.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testAddPage() throws Exception {

        //act, perform a request
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add"))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/add"));
    }

    @Test
    @WithMockUser(username = "bobby.dupont@test.com", password = "mdp123")
    void testUpdatePage() throws Exception {

        //arrange & act, perform a request and create a new CurvePoint
        CurvePoint curvePoint = new CurvePoint(66, 666, 6666);
        curvePointRepository.save(curvePoint);

        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1")
                        .with(csrf())
                        .param("id", String.valueOf(curvePoint.getId())))

                //assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"));
    }
}
