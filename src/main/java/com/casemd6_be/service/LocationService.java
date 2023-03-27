package com.casemd6_be.service;

import com.casemd6_be.model.Location;
import com.casemd6_be.repository.ILocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    ILocationRepo iLocationRepo;

    public List<Location> getAllLocation(){
        return (List<Location>) iLocationRepo.findAll();
    }
}
