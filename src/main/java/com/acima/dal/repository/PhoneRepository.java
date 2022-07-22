package com.acima.dal.repository;

import com.acima.dal.model.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends CrudRepository<Phone, UUID> {
    List<Phone> findAllByOwner(UUID merchantId);
    void deletePhonesByOwner(UUID owner);
}
