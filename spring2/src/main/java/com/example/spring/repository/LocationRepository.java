package com.example.spring.repository;


import com.example.spring.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Page<Location> findByCountry(String country, Pageable pageable);

    Location findByCity(String city);

}

