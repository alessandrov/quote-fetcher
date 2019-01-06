package com.transferwise.fetcher.util;

import com.transferwise.fetcher.entity.PaymentLog;
import com.transferwise.fetcher.service.PaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentLogUtil {

    private static PaymentLogService paymentLogService;

    @Autowired
    public PaymentLogUtil(PaymentLogService paymentLogService) {
        PaymentLogUtil.paymentLogService = paymentLogService;
    }

    public static PaymentLog fromQuoteResponseToEntity(String id, String source, String target, String sourceAmount,
                                                       String targetAmount, String rate, String fee) {

        PaymentLog result = new PaymentLog(Long.valueOf(id), source, target, new BigDecimal(sourceAmount),
                new BigDecimal(targetAmount), new BigDecimal(rate), new BigDecimal(fee));

        return result;
    }

    public static void persistPaymentLog(PaymentLog entity) {
        paymentLogService.save(entity);
    }

}
