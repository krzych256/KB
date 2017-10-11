package com.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Country;
import com.project.service.AirportService;
import com.project.service.CountryService;
import com.project.webservicex.Airport;
import com.project.xml.ListOfAirport;

@RestController
@RequestMapping("/api")
public class AirportRestController {

  @Autowired
  private CountryService countryService;

  @Autowired
  private AirportService airportService;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/allCountries")
  public List<Country> getCountries() {
    return countryService.getAllCountries();
  }

  @RequestMapping("/airports")
  public List<com.project.entity.Airport> getAirport() {
    return airportService.getAirportsFromDb();
  }

  @RequestMapping(value = "/airports/country/{country}", method = RequestMethod.GET)
  public ListOfAirport getListOfAirport(@PathVariable("country") String country) {
    Airport airport = new Airport();
    String xmlSource = airport.getAirportSoap().getAirportInformationByCountry(country);
    logger.info("GET AIRPORTS FROM WEBSERVICE BY COUNTRY");
    return airportService.parseXmlToAirport(xmlSource);
  }

  @RequestMapping(value = "/airports/code/{airportCode}", method = RequestMethod.GET)
  public ListOfAirport getListOfAirportByAirportCode(
      @PathVariable("airportCode") String airportCode) {
    Airport airport = new Airport();
    String xmlSource = airport.getAirportSoap().getAirportInformationByAirportCode(airportCode);
    logger.info("GET AIRPORTS FROM WEBSERVICE BY AIRPORT CODE");
    return airportService.parseXmlToAirport(xmlSource);
  }

  @RequestMapping(value = "/airports/name/{cityOrAirportName}", method = RequestMethod.GET)
  public ListOfAirport getListOfAirportByCityOrAirportName(
      @PathVariable("cityOrAirportName") String cityOrAirportName) {
    Airport airport = new Airport();
    String xmlSource = airport.getAirportSoap()
        .getAirportInformationByCityOrAirportName(cityOrAirportName);
    logger.info("GET AIRPORTS FROM WEBSERVICE BY AIRPORT NAME");
    return airportService.parseXmlToAirport(xmlSource);
  }

  @RequestMapping(value = "/airports/iso/{isoCountryCode}", method = RequestMethod.GET)
  public ListOfAirport getListOfAirportByISOCountryCode(
      @PathVariable("isoCountryCode") String isoCountryCode) {
    Airport airport = new Airport();
    String xmlSource = airport.getAirportSoap()
        .getAirportInformationByISOCountryCode(isoCountryCode);
    logger.info("GET AIRPORTS FROM WEBSERVICE BY COUNTRY ISO");
    return airportService.parseXmlToAirport(xmlSource);
  }
}
