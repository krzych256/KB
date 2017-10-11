package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Country;
import com.project.repository.CountryRepository;

@Service
public class CountryService {

  @Autowired
  private CountryRepository countryRepository;
  
  public List<Country> getAllCountries() {
    return countryRepository.findAll();
  }
}
