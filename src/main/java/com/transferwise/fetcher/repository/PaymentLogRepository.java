package com.transferwise.fetcher.repository;

import com.transferwise.fetcher.entity.PaymentLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogRepository extends CrudRepository<PaymentLog, Long> {

}
