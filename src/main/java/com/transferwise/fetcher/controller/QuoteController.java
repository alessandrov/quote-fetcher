package com.transferwise.fetcher.controller;

import com.transferwise.fetcher.entity.User;
import com.transferwise.fetcher.exception.UserNotFoundException;
import com.transferwise.fetcher.model.QuoteDTO;
import com.transferwise.fetcher.model.QuoteResponse;
import com.transferwise.fetcher.service.UserService;
import com.transferwise.fetcher.integration.transferwise.QuoteOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @Autowired
    private UserService userService;

    /**
     * Get the quote from TransferWise and create a PaymentLog record
     *
     * @param quoteDTO
     */
    @Transactional(rollbackOn = Exception.class)
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Mono<QuoteResponse>> createQuote(@Valid @RequestBody QuoteDTO quoteDTO) throws UserNotFoundException {

        logger.info("Payment Log creation started");

        User user = userService.find(quoteDTO.getUserId());

        if (user != null) {
            // COPY AND REMOVE a singleton could have been ok  be better since we are using a non-blocking api
            Mono<QuoteResponse> response = new QuoteOperation().getQuote(user.getPayoutCurrency(), quoteDTO.getPaymentAmount());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            throw new UserNotFoundException("The requested user does not exist");
        }
    }

}
