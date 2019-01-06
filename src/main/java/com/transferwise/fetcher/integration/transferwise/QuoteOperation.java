package com.transferwise.fetcher.integration.transferwise;

import com.transferwise.fetcher.entity.PaymentLog;
import com.transferwise.fetcher.exception.WebClientCustomClientException;
import com.transferwise.fetcher.exception.WebClientCustomServerException;
import com.transferwise.fetcher.model.QuoteRequest;
import com.transferwise.fetcher.model.QuoteResponse;
import com.transferwise.fetcher.util.PaymentLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuoteOperation {

    private static final Logger logger = LoggerFactory.getLogger(QuoteOperation.class);

    private final WebClient webClient;

    private Properties configProperties;

    public QuoteOperation() {
        Resource resource = new ClassPathResource("/config.properties");

        try {
            configProperties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error("Property file not found");
        }

        String transferwiseQuoteBaseUrl = configProperties.getProperty("transferwise_quote_base_url");

        this.webClient = WebClient.builder().baseUrl(transferwiseQuoteBaseUrl).build();
    }

    private static void logPayment(QuoteResponse quoteResponse) {

        PaymentLog paymentLog = PaymentLogUtil.fromQuoteResponseToEntity(String.valueOf(quoteResponse.getId()), quoteResponse.getSource(), quoteResponse.getTarget(),
                quoteResponse.getSourceAmount(), quoteResponse.getTargetAmount(), quoteResponse.getRate(),
                quoteResponse.getFee());

        // saving payment log
        PaymentLogUtil.persistPaymentLog(paymentLog);
    }

    public Mono<QuoteResponse> getQuote(String payoutCurrency, String paymentAmount) {

        logger.info("Quote retrieval started");

        Long profileId = Long.valueOf(configProperties.getProperty("business_profile_id"));
        String sandboxAPIKey = configProperties.getProperty("sandbox_API_key");
        String sourceCurrency = configProperties.getProperty("source_currency");
        String rateType = configProperties.getProperty("rate_type");

        QuoteRequest quoteRequest = new QuoteRequest(profileId, sourceCurrency, payoutCurrency, rateType,
                paymentAmount, QuoteRequest.Type.REGULAR);

        Mono<QuoteResponse> response = webClient.post()
                .uri("/")
                .syncBody(quoteRequest)
                .header("Authorization", "Bearer " + sandboxAPIKey)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new WebClientCustomClientException("Something is wrong with one or more values you provided",
                                HttpStatus.BAD_REQUEST.value(), "Error", null, null, null))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new WebClientCustomServerException("Something is wrong with one or more values you provided",
                                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", null, null, null))
                )
                .bodyToMono(QuoteResponse.class);

        response.subscribe(QuoteOperation::logPayment);

        return response;
    }
}
