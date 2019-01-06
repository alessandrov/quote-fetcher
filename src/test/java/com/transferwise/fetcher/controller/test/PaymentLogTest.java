package com.transferwise.fetcher.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transferwise.fetcher.model.QuoteDTO;
import com.transferwise.fetcher.model.QuoteResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentLogTest {

    private static final Logger logger = LoggerFactory.getLogger(PaymentLogTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<QuoteDTO> jsonTester;

    private QuoteDTO quoteDTO;

    private Properties configProperties;

    @Before
    public void setup() {
        Resource resource = new ClassPathResource("/config.properties");

        try {
            configProperties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error("Property file not found");
        }

        jsonTester.initFields(this, objectMapper);

        quoteDTO = new QuoteDTO(1L, "12.35");
    }

    @Test
    public void quoteRetrievalAndPaymentLogSuccessfulTest() throws Exception {
        String quoteDTOJson = jsonTester.write(quoteDTO).getJson();

        mockMvc.perform(post("/quote/")
                .content(quoteDTOJson)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andDo(mvcResult -> {
                    QuoteResponse response = (QuoteResponse) mvcResult.getAsyncResult(3000);

                    assertThat(response.getType()).isEqualTo("REGULAR");

                    assertThat(response.getBusiness()).isEqualTo(configProperties.getProperty("business_profile_id"));
                })
                .andExpect(status().isCreated());
    }

}
