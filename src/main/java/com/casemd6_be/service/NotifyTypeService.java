package com.casemd6_be.service;

import com.casemd6_be.repository.INotifyTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyTypeService {
    @Autowired
    INotifyTypeRepo iNotifyTypeRepo;
}
