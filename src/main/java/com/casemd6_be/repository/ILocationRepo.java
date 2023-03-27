package com.casemd6_be.repository;

import com.casemd6_be.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface ILocationRepo extends CrudRepository<Location, Integer> {
}
