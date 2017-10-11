package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "countrycity")
public class CountryCity {

  @Id
  @Column(name = "countrycity_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

}
