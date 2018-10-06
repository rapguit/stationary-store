package com.store.stationary.controller;

import com.store.stationary.IntegrationTest;
import com.store.stationary.model.Product;
import com.store.stationary.repository.ProductRepository;
import com.store.stationary.util.TestUtil;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StationaryStoreControllerTest extends IntegrationTest {

    @Autowired private ProductRepository repo;

    @After
    public void tearDown() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void should_retrieve_a_product() throws Exception {
        Product product = getObjectMapper().readValue(TestUtil.load("product_sample"), Product.class);
        Product saved = repo.save(product);

        getMockMvc().perform(
                get("/api/stationary-store/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andDo(document("stationary-store-find-one",
                        pathParameters(
                                parameterWithName("id").description("The product isbn to retrieve.")
                        ),
                        responseFields(
                                fieldWithPath(".id").description("The product id."),
                                fieldWithPath(".barCode").description("The product bar code."),
                                fieldWithPath(".name").description("The product name."),
                                fieldWithPath(".description").description("The product description."),
                                fieldWithPath(".category").description("The product category."),
                                fieldWithPath(".quantity").description("The product quantity.")
                        )
                ));
    }

    @Test
    public void should_retrieve_products() throws Exception {
        Product product = getObjectMapper().readValue(TestUtil.load("product_sample"), Product.class);
        repo.save(product);

        getMockMvc().perform(
                get("/api/stationary-store"))
                .andExpect(status().isOk())
                .andDo(document("stationary-store-find-all",
                        responseFields(
                                fieldWithPath("[]").description("The products collection."),
                                fieldWithPath("[].id").description("The product id."),
                                fieldWithPath("[].barCode").description("The product bar code."),
                                fieldWithPath("[].name").description("The product name."),
                                fieldWithPath("[].description").description("The product description."),
                                fieldWithPath("[].category").description("The product category."),
                                fieldWithPath("[].quantity").description("The product quantity.")
                        )
                ));
    }

    @Test
    public void should_save_a_product() throws Exception {
        getMockMvc().perform(
                post("/api/stationary-store")
                        .contentType(APPLICATION_JSON)
                        .content(TestUtil.load("product_sample")))
                .andExpect(status().isCreated())
                .andDo(document("product-save",
                        requestFields(
                                fieldWithPath(".barCode").description("The product bar code."),
                                fieldWithPath(".name").description("The product name."),
                                fieldWithPath(".description").description("The product description."),
                                fieldWithPath(".category").description("The product category."),
                                fieldWithPath(".quantity").description("The product quantity.")
                        ),
                        responseFields(
                                fieldWithPath(".id").description("The product id."),
                                fieldWithPath(".barCode").description("The product bar code."),
                                fieldWithPath(".name").description("The product name."),
                                fieldWithPath(".description").description("The product description."),
                                fieldWithPath(".category").description("The product category."),
                                fieldWithPath(".quantity").description("The product quantity.")
                        )
                ));
    }

    @Test
    public void should_update_a_product() throws Exception {
        Product product = getObjectMapper().readValue(TestUtil.load("product_sample"), Product.class);
        product.setName("");
        repo.save(product);

        getMockMvc().perform(
                put("/api/stationary-store/{id}", product.getId())
                        .contentType(APPLICATION_JSON)
                        .content(TestUtil.load("product_sample")))
                .andExpect(status().isOk())
                .andDo(document("product-update",
                        pathParameters(
                                parameterWithName("id").description("The product id to update.")
                        ),
                        requestFields(
                                fieldWithPath(".barCode").description("The product bar code."),
                                fieldWithPath(".name").description("The product name."),
                                fieldWithPath(".description").description("The product description."),
                                fieldWithPath(".category").description("The product category."),
                                fieldWithPath(".quantity").description("The product quantity.")
                        ),
                        responseFields(
                                fieldWithPath(".id").description("The product id."),
                                fieldWithPath(".barCode").description("The product bar code."),
                                fieldWithPath(".name").description("The product name."),
                                fieldWithPath(".description").description("The product description."),
                                fieldWithPath(".category").description("The product category."),
                                fieldWithPath(".quantity").description("The product quantity.")
                        ))
                );

        assertThat(repo.findAll().get(0).getName(), equalTo("Java 8 in Action"));
    }

    @Test
    public void should_delete_a_product() throws Exception {
        Product product = getObjectMapper().readValue(TestUtil.load("product_sample"), Product.class);
        repo.save(product);

        getMockMvc().perform(delete("/api/stationary-store/{id}", product.getId()))
                .andExpect(status().isNoContent())
                .andDo(document("product-delete",
                        pathParameters(
                                parameterWithName("id").description("The product isbn to update.")
                        ))
                );

        assertThat(repo.findAll(), empty());
    }
}