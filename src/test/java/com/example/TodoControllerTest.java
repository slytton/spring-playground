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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gschool on 2/19/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    MockMvc mvc;
    final String todosUrl = "/todos";

    @Test
    public void TestGetTodos() throws Exception {
        mvc.perform(get(todosUrl).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Here are all of your todos:\n" +
                        "1: Buy pants\n" +
                        "2: Put pants on\n" +
                        "3: Look really\n" +
                        "\t1: really\n" +
                        "\t2: good"));
    }

    @Test
    public void TestGetTodo() throws Exception {
        mvc.perform(get(todosUrl + "/2").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("You requested todo number 2:\n" +
                        "2: Put pants on"));
    }

    @Test
    public void TestGetSubTodo() throws Exception {
        mvc.perform(get(todosUrl + "/3/subtodos/2").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("You requested todo number 3 and subtodo 2:\n" +
                        "2: good"));

        mvc.perform(get(todosUrl + "/3/subtodos/1").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("You requested todo number 3 and subtodo 1:\n" +
                        "1: really"));
    }

    @Test
    public void TestPatchSubTodo() throws Exception {
        mvc.perform(patch(todosUrl + "/3/subtodos/2").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated todo #3 subtodo #2"));

        mvc.perform(patch(todosUrl + "/2/subtodos/1").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated todo #2 subtodo #1"));
    }
}