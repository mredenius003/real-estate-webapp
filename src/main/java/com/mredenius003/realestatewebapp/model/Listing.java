package com.mredenius003.realestatewebapp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "listing")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "mls")
    @NotNull
    private long mls;

    @Column(name = "street")
    @NotEmpty(message = "*Please provide a street.")
    private String street;

    @Column(name = "street2")
    private String street2;

    @Column(name = "city")
    @NotEmpty(message = "*Please provide a city.")
    private String city;

    @Column(name = "state")
    @NotEmpty(message = "*Please provide a state.")
    private String state;

    @Column(name = "zipcode")
    @NotEmpty(message = "*Please provide a zipcode.")
    private String zipcode;

    @Column(name = "neighborhood")
    private String neighborhood;
    
    @Column(name = "dateListed")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDate dateListed;

    @Column(name = "numBedrooms")
    @NotEmpty(message = "*Please provide the number of bedrooms.")
    private Integer numBedrooms;
    
    @Column(name = "image")
    private List<String> imagePaths;

    @Column(name = "numBathrooms")
    @NotEmpty(message = "*Please provide the number of bathrooms.")
    private Integer numBathrooms;

    @Column(name = "garageSize")
    private Integer garageSize;

    @Column(name = "squareFootage")
    @NotEmpty(message = "*Please provide the square footage.")
    private Integer squareFootage;

    @Column(name = "lotSize")
    @NotEmpty(message = "*Please provide the lot size in acres.")
    private Double lotSize;

    @Column(name = "description")
    @NotEmpty(message = "*Please provide a description")
    private String description;
}
