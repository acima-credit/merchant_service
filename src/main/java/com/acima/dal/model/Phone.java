package com.acima.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "phone")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private UUID id;

    @Column(name = "phone_number")
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "type")
    private PhoneNumberType type;

    @Column(name = "primary_phone")
    @JsonProperty(value = "primary_phone")
    private Boolean primaryPhone;

    @Column(name = "owner")
    @JsonProperty(value = "owner")
    private UUID owner;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public PhoneNumberType getType() { return type; }
    public void setType(PhoneNumberType type) { this.type = type; }

    public Boolean isPrimaryPhone() { return primaryPhone; }
    public void setPrimaryPhone(Boolean primaryPhone) { this.primaryPhone = primaryPhone; }

    public UUID getOwner() { return owner; }
    public void setOwner(UUID owner) { this.owner = owner; }
}
