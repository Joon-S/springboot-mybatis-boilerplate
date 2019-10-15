package com.joons.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DivisionControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)).build();
    }

    @Test
    public void getDivision() throws Exception {
        this.mockMvc.perform(get("/api/divisions/53").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print())
                .andDo(document("/divisions/get", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("고유 키"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("현장 이름"),
                                fieldWithPath("company_id").type(JsonFieldType.NUMBER).description("회사 고유 키")
                        )));
    }

    @Test
    public void postDivision() throws Exception {
        Map<String, Object> crud = new HashMap<>();
        crud.put("name", "NewYork");
        crud.put("company_id", 18);

        this.mockMvc.perform(post("/api/divisions").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(crud)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andDo(document("/divisions/post", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("현장 이름"),
                                fieldWithPath("company_id").type(JsonFieldType.NUMBER).description("회사 고유 키")
                        )));
    }

    @Test
    public void updateDivision() throws Exception {
        Map<String, Object> crud = new HashMap<>();
        crud.put("name", "NewYork2");

        this.mockMvc.perform(put("/api/divisions/53").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(this.objectMapper.writeValueAsString(crud)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("/divisions/update", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("현장 이름")
                        )));
    }

    @Test
    public void deleteDivision() throws Exception {
        this.mockMvc.perform(delete("/api/divisions/53").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andDo(document("/divisions/delete", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
    }
}
