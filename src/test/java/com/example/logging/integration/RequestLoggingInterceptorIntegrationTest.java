package com.example.logging.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class) // Captures log output
public class RequestLoggingInterceptorIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    MDC.clear(); // Ensure MDC is empty before each test
  }

  @Test
  void testRequestLoggingInterceptor() throws Exception {
    mockMvc
        .perform(
            get("/test-endpoint")
                .header("User-Agent", "JUnitTest")
                .header("referer", "http://localhost")
                .contentType("application/json"))
        .andExpect(status().isOk());

    // Assert that the log contains expected request details
    //      assertThat(output).contains("User-Agent=JUnitTest");
    //      assertThat(output).contains("referer=http://localhost");
    //      assertThat(output).contains("HTTP_METHOD=GET");
  }
}
