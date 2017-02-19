package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gschool on 2/18/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ClimbersController.class)
public class ClimbersControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void TestGetClimbers() throws Exception {
        String expectedResponse = "[{\"firstName\":\"Chris\",\"lastName\":\"Sharma\",\"startedClimbingOn\":\"11-03-96\",\"age\":35},{\"firstName\":\"Dave\",\"lastName\":\"Graham\",\"startedClimbingOn\":\"04-11-93\",\"age\":35},{\"firstName\":\"Tommy\",\"lastName\":\"Caldwell\",\"startedClimbingOn\":\"04-04-85\",\"age\":38}]";
        this.mvc.perform(get("/climbers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void TestDeleteClimbers() throws Exception {
        String name = "chris_sharma";
        this.mvc.perform(delete("/climbers/" + name).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleting " + name));

        this.mvc.perform(delete("/climbers/" + name + "/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleting " + name));

    }

    @Test
    public void TestPatchClimbers() throws Exception {
        String name = "chris_sharma";
        this.mvc.perform(patch("/climbers/" + name).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Patching " + name));

        this.mvc.perform(patch("/climbers/" + name + "/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Patching " + name));
    }
}