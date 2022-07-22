package com.acima.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "address")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends EntityBase {
    @Column(name = "street_1")
    @JsonProperty(value = "street_1")
    private String street1;

    @Column(name = "street_2")
    @JsonProperty(value = "street_2")
    private String street2;

    @Column(name = "apt_number")
    @JsonProperty(value = "apt_number")
    private String apartmentNumber;

    @Column(name = "city")
    @JsonProperty(value = "city")
    private String City;

    @Column(name = "zipcode")
    @JsonProperty(value = "zipcode")
    private String ZipCode;

    @Column(name = "state_code")
    @JsonProperty(value = "state_code")
    private String State;

    public String getStreet1() { return street1; }
    public void setStreet1(String street1) { this.street1 = street1; }

    public String getStreet2() { return street2; }
    public void setStreet2(String street2) { this.street2 = street2; }

    public String getApartmentNumber() { return apartmentNumber; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }

    public String getCity() { return City; }
    public void setCity(String city) { City = city; }

    public String getZipCode() { return ZipCode; }
    public void setZipCode(String zipCode) { ZipCode = zipCode; }

    public String getState() { return State; }
    public void setState(String state) { State = state; }
}
