package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{

}
