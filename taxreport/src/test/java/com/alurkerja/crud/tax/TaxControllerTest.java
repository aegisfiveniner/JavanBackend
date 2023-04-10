package com.alurkerja.crud.tax;

import com.alurkerja.DemoApplication;
import com.alurkerja.core.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(
        locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxService taxService;

    // @Autowired
    // private AuthService authService;

    private String token = "";

    @BeforeEach
    void setToken() {

        token = "";
    }

    Tax savedata() {
        Tax tax = new Tax();
        // Tambahkan data tax
        return taxService.create(tax);
    }

    Tax data() {
        Tax tax = new Tax();
        // Tambahkan data tax
        return tax;
    }

    @Test
    void create() throws Exception {
        Tax tax = this.data();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/crud/tax")
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .content(JsonUtil.serialize(tax))
        ).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void update() throws Exception {
        Tax tax = this.savedata();
        tax.setName("Ryder");
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/crud/tax/"+tax.getId())
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .content(JsonUtil.serialize(tax))
        ).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateShouldErrorWhenIdIsNotSend() throws Exception {
        Tax tax = this.data();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/crud/tax/"+null)
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .content(JsonUtil.serialize(tax))
        ).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void listAll() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/crud/tax")
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void delete() throws Exception {
        Tax tax = this.savedata();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/crud/tax/"+tax.getId())
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    void deleteShouldErrorWhenIidIsnull() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/crud/tax/"+null)
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteShouldErrorWhenIdIsNotExsist() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/crud/tax/"+UUID.randomUUID())
                        .header("Authorization", token)
                        .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void uploadFile() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        this.mockMvc.perform(
                multipart("/crud/tax/upload")
                        .file(file)
                        .header("Authorization", token))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}