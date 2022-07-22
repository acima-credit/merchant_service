package com.acima.dal.repository;

import com.acima.dal.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
    AddressEntity findByAddressId(UUID addressId);
    void deleteAddressByOwner(UUID owner);
}
