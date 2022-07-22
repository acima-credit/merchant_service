package com.acima.rest.resource;

import com.acima.dal.model.MerchantEntity;
import com.acima.dal.repository.AddressRepository;
import com.acima.dal.repository.MerchantRepository;
import com.acima.dal.repository.MerchantUserRepository;
import com.acima.dal.repository.PhoneRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.UUID;

@Path("/merchant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MerchantResource {
    private final MerchantRepository merchantRepository;
    private final MerchantUserRepository merchantUserRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    public MerchantResource(
            MerchantRepository merchantRepository,
            MerchantUserRepository merchantUserRepository,
            AddressRepository addressRepository,
            PhoneRepository phoneRepository) {
        this.merchantRepository = merchantRepository;
        this.merchantUserRepository = merchantUserRepository;
        this.addressRepository = addressRepository;
        this.phoneRepository = phoneRepository;
    }

    @POST
    public Response create(MerchantEntity merchant) {
        SaveMerchant(merchant);
        return Response.accepted(MerchantBuilder(merchant.getId())).build();
    }

    @GET
    @Path("/{merchant_id}")
    public Response read(@PathParam("merchant_id") UUID id) {
        return Response.ok(MerchantBuilder(id)).build();
    }

    @PUT
    public Response update(MerchantEntity merchant) {
        SaveMerchant(merchant);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{merchant_id}/delete_users/{delete_users}")
    public Response delete(@PathParam("merchant_id") UUID id, @PathParam("delete_users") boolean deleteUsers) {
        DeleteMerchant(id,deleteUsers);
        return Response.ok().build();
    }

    private MerchantEntity MerchantBuilder(UUID merchantId) {
        Optional<MerchantEntity> merchant = merchantRepository.findById(merchantId);
        MerchantEntity retVal = null;
        if(merchant.isPresent()) {
            retVal = merchant.get();
        } else {
            throw new RuntimeException(String.format("No merchant found for %s", merchantId.toString()));
        }
        retVal.setAreaSalesManager(merchantUserRepository.findByMerchantUserId(retVal.getAreaSalesManagerId()));
        retVal.setDistrictManager(merchantUserRepository.findByMerchantUserId(retVal.getDistrictManagerId()));
        retVal.setPrimaryRep(merchantUserRepository.findByMerchantUserId(retVal.getPrimaryRepId()));
        retVal.setAddress(addressRepository.findByAddressId(retVal.getPrimaryAddressId()));
        retVal.setPhones(phoneRepository.findAllByOwner(retVal.getId()));
        return retVal;
    }
    private void SaveMerchant(MerchantEntity merchant){
        addressRepository.save(merchant.getAddress());
        phoneRepository.saveAll(merchant.getPhones());
        merchantUserRepository.saveAll(merchant.getMerchantUsers());
        merchantRepository.save(merchant);
    }
    private void DeleteMerchant(UUID merchantId, boolean deleteUsers) {
        MerchantEntity merchant = merchantRepository.findMerchantById(merchantId);
        addressRepository.deleteAddressByOwner(merchant.getId());
        phoneRepository.deletePhonesByOwner(merchant.getId());
        if(deleteUsers) {
            merchantUserRepository.deleteAll(merchant.getMerchantUsers());
        }
        merchantRepository.delete(merchant);
    }
}
