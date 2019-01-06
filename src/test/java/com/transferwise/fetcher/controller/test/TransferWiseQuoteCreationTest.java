package com.transferwise.fetcher.controller.test;

import com.transferwise.fetcher.model.QuoteRequest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransferWiseQuoteCreationTest {

    private static final Logger logger = LoggerFactory.getLogger(TransferWiseQuoteCreationTest.class);

    @Autowired
    private WebTestClient webTestClient;

    private Properties configProperties;

    @Before
    public void setup() {
        Resource resource = new ClassPathResource("/config.properties");

        try {
            configProperties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error("Property file not found");
        }
    }

    @Test
    public void quoteRetrievalSuccessfulTest() {

        Long profileId = Long.valueOf(configProperties.getProperty("business_profile_id"));
        String sandboxAPIKey = configProperties.getProperty("sandbox_API_key");
        String sourceCurrency = configProperties.getProperty("source_currency");
        String rateType = configProperties.getProperty("rate_type");
        String transferwiseQuoteBaseUrl = configProperties.getProperty("transferwise_quote_base_url");

        QuoteRequest quoteRequest = new QuoteRequest(profileId, sourceCurrency, "USD", rateType,
                "12.35", QuoteRequest.Type.REGULAR);

        webTestClient.post().uri(transferwiseQuoteBaseUrl)
                .header("Authorization", "Bearer " + sandboxAPIKey)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(quoteRequest), QuoteRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.targetAmount").isNotEmpty()
                .jsonPath("$.targetAmount").isEqualTo("12.35")
                .jsonPath("$.business").isNotEmpty()
                .jsonPath("$.business").isEqualTo(profileId);
    }

}
