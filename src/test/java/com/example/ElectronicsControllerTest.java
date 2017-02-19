package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gschool on 2/19/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ElectronicsController.class)
public class ElectronicsControllerTest {
    @Autowired
    MockMvc mvc;

    final String samsungPath = "/electronics?type=tv&brand=samsung&model-number=123";
    final String vizioPath = "/electronics?type=tv&brand=vizio&model-number=345";

    @Test
    public void TestGetParams() throws Exception {
        mvc.perform(get(samsungPath).accept(MediaType.ALL))
        .andExpect(status().isOk())
        .andExpect(content().string("You searched for the following tv:\n" +
                "brand: samsung\n" +
                "model-number: 123"));


        mvc.perform(get(vizioPath))
                .andExpect(status().isOk())
                .andExpect(content().string("You searched for the following tv:\n" +
                        "brand: vizio\n" +
                        "model-number: 345"));
    }

    @Test
    public void TestPostParams() throws Exception {
        mvc.perform(post(samsungPath))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully added a gizmo with the following details:\n" +
                        "type: tv\n" +
                        "brand: samsung\n" +
                        "model-number: 123"));

        mvc.perform(post(vizioPath))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully added a gizmo with the following details:\n" +
                        "type: tv\n" +
                        "brand: vizio\n" +
                        "model-number: 345"));
    }

    @Test
    public void TestPatchParams() throws Exception {
        mvc.perform(patch("/electronics?type=tv&brand=samsung&modelNumber=123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated a gizmo with the following details:\n" +
                        "type: tv\n" +
                        "brand: samsung\n" +
                        "model number: 123"));

        mvc.perform(patch("/electronics?type=tv&brand=vizio&modelNumber=345"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated a gizmo with the following details:\n" +
                        "type: tv\n" +
                        "brand: vizio\n" +
                        "model number: 345"));
    }

}