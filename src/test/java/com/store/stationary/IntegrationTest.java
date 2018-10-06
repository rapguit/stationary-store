package com.store.stationary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StationaryStoreApplication.class)
@WebAppConfiguration
public abstract class IntegrationTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired WebApplicationContext context;
    @Autowired @Getter ObjectMapper objectMapper;

    @Getter MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();

    }
}
