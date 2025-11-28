package com.example.spring.repository;

import com.example.spring.entity.Forecast;
import com.example.spring.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Repository
@RepositoryRestResource
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
    Page<Forecast> findByDateGreaterThanEqualAndLocation_Id(
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Integer locationId,
            Pageable pageable
    );
}
