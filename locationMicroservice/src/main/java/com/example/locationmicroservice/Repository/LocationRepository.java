package com.example.locationmicroservice.Repository;

import com.example.locationmicroservice.Model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Integer> {
}
