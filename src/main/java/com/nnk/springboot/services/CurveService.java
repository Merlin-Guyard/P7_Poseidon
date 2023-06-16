package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class CurveService {

    @Autowired
    CurvePointRepository curvePointRepository;

    //get all Curve Points
    public List<CurvePoint> getAll(){
        return curvePointRepository.findAll();
    }

    //get Curve Points by id
    public CurvePoint getById(int id){
        Optional<CurvePoint> optionalCurvePoint= curvePointRepository.findById(id);
        if (optionalCurvePoint.isEmpty()){
            Logger.error("No CurvePoint found by id",id);
            throw new RuntimeException();
        }
        return optionalCurvePoint.get();
    }

    //save a new Curve Point
    public void saveCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    //update a Curve Point
    public void updateById(Integer id, CurvePoint curvePoint) {
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
    }

    //delete a Curve Point
    public void deleteById(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
