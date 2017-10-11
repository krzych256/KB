package com.project.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "city")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "city_id")
  private int id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "city")
  private List<CountryCity> countryCity;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CountryCity> getCountryCity() {
    return countryCity;
  }

  public void setCountryCity(List<CountryCity> countryCity) {
    this.countryCity = countryCity;
  }

}
