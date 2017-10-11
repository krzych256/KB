package com.project.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "airportCode", "cityOrAirportName", "country",
    "countryAbbrviation", "countryCode", "gmtOffset", "runwayLengthFeet", "runwayElevationFeet",
    "latitudeDegree", "latitudeMinute", "latitudeSecond", "latitudeNpeerS", "longitudeDegree",
    "longitudeMinute", "longitudeSeconds", "longitudeEperW" })
public class Airport {

  @XmlElement(name = "AirportCode")
  private String airportCode;
  @XmlElement(name = "CityOrAirportName")
  private String cityOrAirportName;
  @XmlElement(name = "Country")
  private String country;
  @XmlElement(name = "CountryAbbrviation")
  private String countryAbbrviation;
  @XmlElement(name = "CountryCode")
  private String countryCode;
  @XmlElement(name = "GMTOffset")
  private String gmtOffset;
  @XmlElement(name = "RunwayLengthFeet")
  private String runwayLengthFeet;
  @XmlElement(name = "RunwayElevationFeet")
  private String runwayElevationFeet;
  @XmlElement(name = "LatitudeDegree")
  private String latitudeDegree;
  @XmlElement(name = "LatitudeMinute")
  private String latitudeMinute;
  @XmlElement(name = "LatitudeSecond")
  private String latitudeSecond;
  @XmlElement(name = "LatitudeNpeerS")
  private String latitudeNpeerS;
  @XmlElement(name = "LongitudeDegree")
  private String longitudeDegree;
  @XmlElement(name = "LongitudeMinute")
  private String longitudeMinute;
  @XmlElement(name = "LongitudeSeconds")
  private String longitudeSeconds;
  @XmlElement(name = "LongitudeEperW")
  private String longitudeEperW;

  public String getAirportCode() {
    return airportCode;
  }

  public void setAirportCode(String airportCode) {
    this.airportCode = airportCode;
  }

  public String getCityOrAirportName() {
    return cityOrAirportName;
  }

  public void setCityOrAirportName(String cityOrAirportName) {
    this.cityOrAirportName = cityOrAirportName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryAbbrviation() {
    return countryAbbrviation;
  }

  public void setCountryAbbrviation(String countryAbbrviation) {
    this.countryAbbrviation = countryAbbrviation;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getGmtOffset() {
    return gmtOffset;
  }

  public void setGmtOffset(String gmtOffset) {
    this.gmtOffset = gmtOffset;
  }

  public String getRunwayLengthFeet() {
    return runwayLengthFeet;
  }

  public void setRunwayLengthFeet(String runwayLengthFeet) {
    this.runwayLengthFeet = runwayLengthFeet;
  }

  public String getRunwayElevationFeet() {
    return runwayElevationFeet;
  }

  public void setRunwayElevationFeet(String runwayElevationFeet) {
    this.runwayElevationFeet = runwayElevationFeet;
  }

  public String getLatitudeDegree() {
    return latitudeDegree;
  }

  public void setLatitudeDegree(String latitudeDegree) {
    this.latitudeDegree = latitudeDegree;
  }

  public String getLatitudeMinute() {
    return latitudeMinute;
  }

  public void setLatitudeMinute(String latitudeMinute) {
    this.latitudeMinute = latitudeMinute;
  }

  public String getLatitudeSecond() {
    return latitudeSecond;
  }

  public void setLatitudeSecond(String latitudeSecond) {
    this.latitudeSecond = latitudeSecond;
  }

  public String getLatitudeNpeerS() {
    return latitudeNpeerS;
  }

  public void setLatitudeNpeerS(String latitudeNpeerS) {
    this.latitudeNpeerS = latitudeNpeerS;
  }

  public String getLongitudeDegree() {
    return longitudeDegree;
  }

  public void setLongitudeDegree(String longitudeDegree) {
    this.longitudeDegree = longitudeDegree;
  }

  public String getLongitudeMinute() {
    return longitudeMinute;
  }

  public void setLongitudeMinute(String longitudeMinute) {
    this.longitudeMinute = longitudeMinute;
  }

  public String getLongitudeSeconds() {
    return longitudeSeconds;
  }

  public void setLongitudeSeconds(String longitudeSeconds) {
    this.longitudeSeconds = longitudeSeconds;
  }

  public String getLongitudeEperW() {
    return longitudeEperW;
  }

  public void setLongitudeEperW(String longitudeEperW) {
    this.longitudeEperW = longitudeEperW;
  }

}

// airportCode = kod lotniska
// cityOrAirportName = nazwa lotniska
// country = kraj
// countryAbbrviation = skrot panstwa np. PL
// countryCode = kod kraju
// gmtOffset = odniesienie chyba do czasu
// runwayLengthFeet = dlugosc pasa startowego
// runwayElevationFeet = wysokosc nad pozziomem morza
// latitudeDegree = szerokosc geograficzna (stopni)
// latitudeMinute = szerokosc geograficzna (minuty)
// latitudeSecond = szerokosc geograficzna (sekundy)
// latitudeNpeerS = szerokosc geograficzna ktora polkula
// longitudeDegree = dlugosc geograficzna (stopni)
// longitudeMinute = dlugosc geograficzna (minuty)
// longitudeSeconds = dlugosc geograficzna (sekundy)
// longitudeEperW = dlugosc geograficzna ktora polkula
