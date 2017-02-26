package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gschool on 2/25/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AlbumsController.class)
public class AlbumsControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumRepository repository;

    @Test
    public void listTest() throws Exception {
        Long id = new Random().nextLong();
        Album album = new Album();
        album.setName("Kill 'em All");
        album.setBandName("Metallica");
        album.setId(id);

        when(repository.findAll()).thenReturn(Collections.singletonList(album));

        MockHttpServletRequestBuilder request = get("/albums")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("Kill 'em All")))
                .andExpect(jsonPath("$[0].bandName", equalTo(album.getBandName())))
                .andExpect(jsonPath("$[0].id", equalTo(id)));

    }

    @Test
    public void createTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Kill 'em All\"," +
                        "\"bandName\": \"Metallica\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Kill 'em All")));

        verify(repository).save(any(Album.class));
    }

}