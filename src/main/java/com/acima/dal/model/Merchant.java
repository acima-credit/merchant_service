package com.acima.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "merchant")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Merchant extends EntityBase {
    @Column(name = "legal_business_name")
    @JsonProperty(value = "legal_business_name")
    @NotNull
    private String legalBusinessName;

    @Column(name = "display_name")
    @JsonProperty(value = "display_name")
    private String displayName;

    @Transient
    @JsonProperty(value = "primary_address")
    private Address primaryAddress;

    @Column(name = "primary_address_id")
    @JsonIgnore
    private UUID primaryAddressId;

    @Transient
    @JsonProperty(value = "primary_phone")
    private Phone primaryPhone;

    @Column(name = "primary_phone_id")
    @JsonIgnore
    private UUID primaryPhoneId;

    @Column(name = "year_established")
    @JsonProperty(value = "year_established")
    private int yearEstablished;

    @Transient
    @JsonProperty(value = "email")
    private Email email;

    @Column(name = "email_id")
    @JsonIgnore
    private UUID emailId;

    @Column(name = "website_url")
    @JsonProperty(value = "website_url")
    private String websiteUrl;

    @Transient
    @JsonProperty(value = "funding_email")
    private Email fundingEmail;

    @Column(name = "funding_email_id")
    @JsonIgnore
    private UUID fundingEmailId;

    @JsonProperty(value = "industries")
    @Transient
    private List<Industry> industries;

    @Transient
    @JsonProperty(value = "buying_group")
    private BuyingGroup buyingGroup;

    @Column(name = "buying_group_id")
    @JsonIgnore
    private UUID buyingGroupId;

    @Transient
    @JsonProperty(value = "primary_contact")
    private User primaryContact;

    @Column(name = "buying_group_id")
    @JsonIgnore
    private UUID primaryRepId;

    @Transient
    @JsonProperty(value = "source")
    private Source source;

    @Column(name = "source_id")
    @JsonIgnore
    private UUID sourceId;

    @Column(name = "application_short_link")
    @JsonProperty(value = "application_short_link")
    private String applicationShortLink;

    @Column(name = "tax_id_number")
    @JsonProperty(value = "tax_id_number")
    private String taxIdNumber; // This needs to be encrypted and decrypted at the application level.

    public String getLegalBusinessName() { return legalBusinessName; }
    public void setLegalBusinessName(String legalBusinessName) { this.legalBusinessName = legalBusinessName; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public Address getPrimaryAddress() { return primaryAddress; }
    public void setPrimaryAddress(Address primaryAddress) { this.primaryAddress = primaryAddress; }

    public UUID getPrimaryAddressId() { return primaryAddressId; }
    public void setPrimaryAddressId(UUID primaryAddressId) { this.primaryAddressId = primaryAddressId; }

    public Phone getPrimaryPhone() { return primaryPhone; }
    public void setPrimaryPhone(Phone primaryPhone) { this.primaryPhone = primaryPhone; }

    public UUID getPrimaryPhoneId() { return primaryPhoneId; }
    public void setPrimaryPhoneId(UUID primaryPhoneId) { this.primaryPhoneId = primaryPhoneId; }

    public int getYearEstablished() { return yearEstablished; }
    public void setYearEstablished(int yearEstablished) { this.yearEstablished = yearEstablished; }

    public Email getEmail() { return email; }
    public void setEmail(Email email) { this.email = email; }

    public UUID getEmailId() { return emailId; }
    public void setEmailId(UUID emailId) { this.emailId = emailId; }

    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }

    public Email getFundingEmail() {
        return fundingEmail;
    }

    public void setFundingEmail(Email fundingEmail) {
        this.fundingEmail = fundingEmail;
    }

    public UUID getFundingEmailId() {
        return fundingEmailId;
    }

    public void setFundingEmailId(UUID fundingEmailId) {
        this.fundingEmailId = fundingEmailId;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public BuyingGroup getBuyingGroup() {
        return buyingGroup;
    }

    public void setBuyingGroup(BuyingGroup buyingGroup) {
        this.buyingGroup = buyingGroup;
    }

    public UUID getBuyingGroupId() {
        return buyingGroupId;
    }

    public void setBuyingGroupId(UUID buyingGroupId) {
        this.buyingGroupId = buyingGroupId;
    }

    public User getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(User primaryContact) {
        this.primaryContact = primaryContact;
    }

    public UUID getPrimaryRepId() {
        return primaryRepId;
    }

    public void setPrimaryRepId(UUID primaryRepId) {
        this.primaryRepId = primaryRepId;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public UUID getSourceId() {
        return sourceId;
    }

    public void setSourceId(UUID sourceId) {
        this.sourceId = sourceId;
    }

    public String getApplicationShortLink() {
        return applicationShortLink;
    }

    public void setApplicationShortLink(String applicationShortLink) {
        this.applicationShortLink = applicationShortLink;
    }

    public String getTaxIdNumber() {
        return taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }
}
