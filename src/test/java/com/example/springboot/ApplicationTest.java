package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrlTemplate;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL)).andExpect(content().string(not(containsString("testing"))));
    }

    @Test
    void testCaseSensitivity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=TESTING")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL)).andExpect(content().string(not(containsString("testing"))));
    }
}
