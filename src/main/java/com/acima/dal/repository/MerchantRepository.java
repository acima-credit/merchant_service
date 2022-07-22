package com.acima.dal.repository;

import com.acima.dal.model.MerchantEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MerchantRepository extends CrudRepository<MerchantEntity, UUID> {
    MerchantEntity findMerchantById(UUID id);
}
