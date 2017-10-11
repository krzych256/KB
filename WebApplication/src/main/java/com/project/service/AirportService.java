package com.project.service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.project.entity.Airport;
import com.project.repository.AirportRepository;
import com.project.xml.ListOfAirport;

@Service
public class AirportService {

  @Autowired
  private AirportRepository airportRepository;
  
  public ListOfAirport parseXmlToAirport(String xmlSource) {

    JAXBContext jaxbContext;
    Unmarshaller jaxbUnmarshaller;
    ListOfAirport listOfAirport = null;

    try {
      jaxbContext = JAXBContext.newInstance(ListOfAirport.class);
      jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      listOfAirport = (ListOfAirport) jaxbUnmarshaller
          .unmarshal(new InputSource(new ByteArrayInputStream(xmlSource.getBytes("utf-8"))));
      return listOfAirport;
    } catch (JAXBException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return listOfAirport;

  }
  
  public List<Airport> getAirportsFromDb(){
    return airportRepository.findAll();
  }
  
}
