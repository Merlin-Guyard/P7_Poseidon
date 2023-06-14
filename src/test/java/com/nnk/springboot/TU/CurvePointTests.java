package com.nnk.springboot.TU;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;
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
public class CurvePointTests {

    @Autowired
    private CurveService curveService;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @BeforeEach
    public void clear() {
        curvePointRepository.deleteAll();
    }

    @Test
    public void getAll() {

        // Act
        CurvePoint curvePoint = new CurvePoint(11, 111, 1111);
        curvePointRepository.save(curvePoint);

        // Perform
        List<CurvePoint> curvePoints = curveService.getAll();

        //Assert
        assertNotNull(curvePoints.get(0));
        assertEquals(curvePoints.get(0).getCurveId(),11);
    }

    @Test
    public void getById() {

        // Act
        CurvePoint curvePoint = new CurvePoint(11, 111, 1111);
        curvePointRepository.save(curvePoint);
        List<CurvePoint> curvePointList = curveService.getAll();

        // Perform
        CurvePoint curvePoint2Get = curvePointRepository.getById(curvePointList.get(0).getId());

        //Assert
        assertEquals(curvePoint2Get.getCurveId(),11);
    }

    @Test
    public void update() {

        // Act
        CurvePoint curvePoint = new CurvePoint(11, 111, 1111);
        CurvePoint curvePointUpdated = new CurvePoint(22, 111, 1111);
        curvePointRepository.save(curvePoint);

        // Perform
        curveService.updateById(curvePoint.getId(), curvePointUpdated);

        //Assert
        CurvePoint curvePoint1 = curveService.getById(curvePointUpdated.getId());
        assertEquals(curvePoint1.getCurveId(),22);
        assertEquals(curvePoint1.getTerm(),111);
    }

    @Test
    public void delete() {

        // Act
        CurvePoint curvePoint = new CurvePoint(11, 111, 1111);
        curvePointRepository.save(curvePoint);

        // Perform
        curveService.deleteById(curvePoint.getId());

        //Assert
        List<CurvePoint> curvePoints = curveService.getAll();
        assertThat(curvePoints.isEmpty()).isTrue();
    }
}
