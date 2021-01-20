package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesAndFilterByYear(@RequestParam(name="year", required = false) Integer year,
                                                                    @RequestParam(name="distilleryName", required = false) String distilleryName,
                                                                    @RequestParam(name="age", required = false) Integer age,
                                                                    @RequestParam(name="distilleryRegion", required = false) String distilleryRegion) {
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if(distilleryName != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
        }
        if(distilleryRegion != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(distilleryRegion), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }




}
