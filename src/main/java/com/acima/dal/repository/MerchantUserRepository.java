package com.acima.dal.repository;

import com.acima.dal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MerchantUserRepository extends CrudRepository<User, UUID> {
    User findByMerchantUserId(UUID merchantUserId);
}
