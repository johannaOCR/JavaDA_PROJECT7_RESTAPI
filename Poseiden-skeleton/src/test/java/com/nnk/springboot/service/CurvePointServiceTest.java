package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CurvePointServiceTest {
    @InjectMocks
    private CurvePointService curvePointService;
    @Mock
    CurvePointRepository curvePointRepository;

    @Test
    public void addCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(1, 10d, 10d);
        //WHEN
        curvePointService.addCurvePoint(curvePoint);
        //THEN
        verify(curvePointRepository,times(1)).save(curvePoint);
    }
    @Test
    public void updateCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(1, 10d, 10d);
        curvePointService.addCurvePoint(curvePoint);
        curvePoint.setCurveId(2);
        //WHEN
        curvePointService.updateCurvePoint(curvePoint);
        //THEN
        verify(curvePointRepository,times(2)).save(curvePoint);
    }
    @Test
    public void getByIdCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(1, 10d, 10d);
        curvePoint.setId(1);
        Integer id = curvePoint.getId();
        //WHEN
        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));
        CurvePoint result = curvePointService.getCurvePointById(id).get();
        //THEN
        verify(curvePointRepository,times(1)).findById(id);
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getCurveId()).isEqualTo(1);
        assertThat(result.getTerm()).isEqualTo(10d);
        assertThat(result.getValue()).isEqualTo(10d);
    }
    @Test
    public void getAllCurvePointTest(){
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(1, 10d, 10d);
        curvePoint.setId(1);
        //WHEN
        curvePointService.getAllCurvePoint();
        //THEN
        verify(curvePointRepository,times(1)).findAll();
    }
    @Test
    public void deleteCurvePointTest() {
        //GIVEN
        CurvePoint curvePoint = new CurvePoint(1, 10d, 10d);
        curvePoint.setId(1);
        Integer id = curvePoint.getId();
        //WHEN
        curvePointService.deleteCurvePoint(id);
        //THEN
        verify(curvePointRepository,times(1)).deleteById(id);
    }
}
