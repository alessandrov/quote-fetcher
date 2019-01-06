package com.transferwise.fetcher.service.impl;

import com.transferwise.fetcher.entity.PaymentLog;
import com.transferwise.fetcher.service.PaymentLogService;
import com.transferwise.fetcher.repository.PaymentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PaymentLogServiceImpl implements PaymentLogService {

    @Autowired
    protected PaymentLogRepository repository;

    @Override
    public PaymentLog find(Long id) {
        PaymentLog result = null;

        Optional<PaymentLog> paymentLog = repository.findById(id);

        if (paymentLog.isPresent()) {
            result = paymentLog.get();
        }

        return result;
    }

    @Override
    public List<PaymentLog> findAll() {
        return (List<PaymentLog>) repository.findAll();
    }

    @Override
    public PaymentLog save(PaymentLog entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PaymentLog update(PaymentLog entity) {
        return repository.save(entity);
    }

}
