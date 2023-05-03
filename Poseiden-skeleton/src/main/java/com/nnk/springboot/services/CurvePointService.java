package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;
    private static final Logger logger = LogManager.getLogger("CurvePointService");

    public List<CurvePoint> getAllCurvePoint(){
        return curvePointRepository.findAll();
    }

    public void addCurvePoint(CurvePoint curvePoint){
        logger.info("Adding curvePoint");
        curvePointRepository.save(curvePoint);
    }

    public void updateCurvePoint(CurvePoint curvePoint){
        logger.info("Updating curvePoint");
        curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePoint(Integer id){
        logger.info("Deleting curvePoint");
        curvePointRepository.deleteById(id);
    }

    public Optional<CurvePoint> getCurvePointById(Integer id) {
        return curvePointRepository.findById(id);
    }
}
