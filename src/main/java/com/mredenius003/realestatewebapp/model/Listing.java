package com.mredenius003.realestatewebapp.model;

import java.time.LocalDateTime;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.mredenius003.realestatewebapp.validator.api.EnsureNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "listing")
public class Listing {

    private static final String LONG_ENTRY_ERROR_MESSAGE = "*This input is too long";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Size(min = 1, max = 10)
    @EnsureNumber(message = "*Please use only numbers with a max lengh of 10.")
    @Column(name = "mls")
    private String mls;

    @Column(name = "street")
    @Size(max = 199, message = LONG_ENTRY_ERROR_MESSAGE)
    @NotEmpty(message = "*Please provide a street.")
    private String street;

    @Size(max = 199, message = LONG_ENTRY_ERROR_MESSAGE)
    @Column(name = "street2")
    private String street2;

    @Size(max = 99, message = LONG_ENTRY_ERROR_MESSAGE)
    @Column(name = "city")
    @NotEmpty(message = "*Please provide a city.")
    private String city;

    @Size(max = 13, message = LONG_ENTRY_ERROR_MESSAGE)
    @Column(name = "state")
    @NotEmpty(message = "*Please provide a state.")
    private String state;

    @Size(min = 5, max = 5)
    @EnsureNumber(message = "*Please enter a valid 5 digit zipcode.")
    @Column(name = "zipcode")
    @NotEmpty(message = "*Please provide a zipcode.")
    private String zipcode;

    @Size(max = 199, message = LONG_ENTRY_ERROR_MESSAGE)
    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "dateListed")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateListed;

    @Max(value = 99, message = "*No way you have that many bedrooms.")
    @Min(value = 0, message = "*Please provide a number of bedrooms greater >= 0")
    @Column(name = "numBedrooms")
    @NotNull(message = "*Please provide the number of bedrooms.")
    private Integer numBedrooms;

    @Column(name = "imagePaths")
    private HashSet<String> imagePaths;

    @Max(value = 99, message = "*No way you have that many bathrooms.")
    @Min(value = 0, message = "*Please provide a number of bathrooms >= 0")
    @Column(name = "numBathrooms")
    @NotNull(message = "*Please provide the number of bathrooms.")
    private Integer numBathrooms;

    @Max(value = 99999999, message = "*No way your garage is that big.")
    @Min(value = 0, message = "*Please provide a garage size >= 0")
    @Column(name = "garageSize")
    private Integer garageSize;

    @Max(value = 99999999, message = "*No way your house is that big.")
    @Min(value = 0, message = "*Please provide a home size >= 0")
    @Column(name = "homeSize")
    @NotNull(message = "*Please provide the size of your home.")
    private Integer homeSize;

    @Max(value = 99999999, message = "*No way your lot is that big.")
    @Min(value = 0, message = "*Please provide a lot size >= 0")
    @Column(name = "lotSize")
    @NotNull(message = "*Please provide the lot size in acres.")
    private Double lotSize;

    @Size(max = 1500, message = "*Can you provide a less wordy description?")
    @Column(name = "description")
    @NotEmpty(message = "*Please provide a description")
    private String description;

    @Max(value = 99999999, message = "*In this economy?? Maybe it should be a little cheaper.")
    @Min(value = 1, message = "*Please provide a price for your home.")
    @Column(name = "price")
    @NotNull(message = "*Please provide a price for your home")
    private Integer price;
}
