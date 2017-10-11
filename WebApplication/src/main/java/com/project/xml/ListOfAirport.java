package com.project.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "airport" })
@XmlRootElement(name = "NewDataSet")
public class ListOfAirport {

  @XmlElement(name = "Table", required = true)
  private List<Airport> airport;

  public List<Airport> getAirport() {
    return airport;
  }

  public void setAirport(List<Airport> airport) {
    this.airport = airport;
  }

}
