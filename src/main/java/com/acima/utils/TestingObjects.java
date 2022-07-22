package com.acima.utils;

import com.acima.dal.collection.PrimaryIndustry;
import com.acima.dal.model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestingObjects {
    public static MerchantEntity getMerchant(){
        MerchantEntity merchant = new MerchantEntity();
        merchant.setId(UUID.randomUUID());
        merchant.setLegalBusinessName("Legal Business Name");
        merchant.setDba("DBA whatever that is");
        merchant.setAddress(getAddress());
        merchant.setPhones(getPhones());
        merchant.setPrimaryAddressId(merchant.getAddress().getId());
        merchant.setPrimaryPhoneId(getPrimaryPhone(merchant.getPhones()).getId());
        merchant.setYearEstablished(1836);
        merchant.setLatitude(36.129308597550356f);
        merchant.setLongitude(-115.17679478623552f);
        merchant.setCreatedAt(Date.from(LocalDateTime.now().minus(3, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setEmail("kissit@microsoft.com");
        merchant.setDeactivatedAt(Date.from(LocalDateTime.now().plus(5, ChronoUnit.YEARS).atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setWebsiteUrl("www.kissit.com");
        merchant.setPublicId("Public Id");
        merchant.setFinanceOptions("option1, option2");
        merchant.setSource("The Source");
        merchant.setDeletedAt(Date.from(LocalDateTime.now().plus(5, ChronoUnit.YEARS).atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setFundingEmail("kissitmoney@microsoft.com");
        merchant.setAgreementSignedAt(Date.from(LocalDateTime.now().minusYears(1).minusMonths(3).minusHours(6).atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setAgreementSignedThrough("A Jamaican intermediary");
        merchant.setAgreementSignedName("Adult McLovin");
        merchant.setAgreementSignedTitle("Lord Master of the Royal Privy");
        merchant.setSigningMethod("Prehensile Tail");
        merchant.setBuyingGroup(BuyingGroup.THE_BORG);
        merchant.setPrimaryIndustry(PrimaryIndustry.ASSIMILATION);
        merchant.setPrimaryRep(getMerchantUser());
        merchant.setPrimaryRepId(merchant.getPrimaryRep().getId());
        merchant.setApplicationShortLink("www.wtf-app.com");
        merchant.setAreaSalesManager(getMerchantUser());
        merchant.setAreaSalesManagerId(merchant.getAreaSalesManager().getId());
        merchant.setMerchantSuccessRepId(UUID.randomUUID());
        merchant.setDeniedAt(Date.from(LocalDateTime.now().plus(5, ChronoUnit.YEARS).atZone(ZoneId.systemDefault()).toInstant()));
        merchant.setDistrictManager(getMerchantUser());
        merchant.setDistrictManagerId(merchant.getDistrictManager().getId());
        merchant.setTaxIdNumber("Here is another Tax Id number");
        merchant.setAcimaMerchantGuid(UUID.randomUUID().toString());
        return merchant;
    }

    public static User getMerchantUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        return user;
    }

    public static AddressEntity getAddress(){
        AddressEntity address = new AddressEntity();
        address.setId(UUID.randomUUID());
        address.setStreet1("10 Buxton Ave.");
        address.setCity("Asheville");
        address.setState("NC");
        address.setZipCode("28801");
        return address;
    }

    public static List<Phone> getPhones(){
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        phone.setId(UUID.randomUUID());
        phone.setPhoneNumber("828-252-8089");
        phone.setPrimaryPhone(true);
        phone.setType(PhoneNumberType.OFFICE);
        phones.add(phone);

        phone = new Phone();
        phone.setId(UUID.randomUUID());
        phone.setPhoneNumber("549-798-1234");
        phone.setPrimaryPhone(false);
        phone.setType(PhoneNumberType.OFFICE);
        phones.add(phone);
        return phones;
    }

    public static Phone getPrimaryPhone(List<Phone> phones){
        return phones.stream().filter(phone -> phone.isPrimaryPhone().equals(true)).findFirst().orElse(null);
    }

    public static Phone getSecondaryPhone(List<Phone> phones){
        return phones.stream().filter(phone -> phone.isPrimaryPhone().equals(false)).findFirst().orElse(null);
    }
}
