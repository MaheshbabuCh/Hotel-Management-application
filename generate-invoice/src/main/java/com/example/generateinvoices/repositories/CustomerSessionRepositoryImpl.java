package com.example.generateinvoices.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.generateinvoices.models.CustomerSession;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository {

    Map<Long, CustomerSession> customerSessionMap = new HashMap<>();

    @Override
    public CustomerSession save(CustomerSession customerSession) {
        customerSessionMap.put(customerSession.getUser().getId(), customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return Optional.ofNullable(customerSessionMap.get(userId));
    }
    
}
