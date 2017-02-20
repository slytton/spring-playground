package com.example;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
///
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
    public void TestPostClimbersWithJsonString() throws Exception {
        String json = "{\"firstName\":\"Chris\"," +
                "\"lastName\":\"Sharma\"," +
                "\"startedClimbingOn\":\"11-03-96\"," +
                "\"age\":35," +
                "\"competitions\":[" +
                    "{\"id\": 1,\"name\":\"ABS Nationals\",\"result\":1}" +
                "]}";

        MockHttpServletRequestBuilder request = post("/climbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Name: Chris Sharma\n" +
                        "age: 35\n" +
                        "competitions: \n" +
                        "\tid: 1, name: ABS Nationals, result: 1"));

    }

    @Test
    public void TestPostClimbersWithGson() throws Exception {
        JsonObject climber = new JsonObject();
        JsonObject competition = new JsonObject();
        JsonArray competitions = new JsonArray();

        climber.addProperty("firstName", "Seth");
        climber.addProperty("lastName", "Lytton");
        climber.addProperty("startedClimbingOn", "02-10-2001");
        climber.addProperty("age", 31);

        competition.addProperty("id", 1);
        competition.addProperty("name", "Psychedelia");
        competition.addProperty("result", 6);

        competitions.add(competition);

        climber.add("competitions", competitions);

        String json = new GsonBuilder().create().toJson(climber);
        MockHttpServletRequestBuilder request = post("/climbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Name: Seth Lytton\n" +
                        "age: 31\n" +
                        "competitions: \n" +
                        "\tid: 1, name: Psychedelia, result: 6"));

    }

    @Test
    public void TestPostClimbersWithFileFixtures() throws Exception {

        String json = getJSON("/climber.json");

        MockHttpServletRequestBuilder request = post("/climbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Name: Daniel Woods\n" +
                        "age: 27\n" +
                        "competitions: \n" +
                        "\tid: 1, name: Vail World Cup - 2014, result: 1"));
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


    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}