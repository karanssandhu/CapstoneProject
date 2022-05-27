package ca.sheridancollege.sandh408.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.sandh408.database.DatabaseAccess;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class TestHomeController {


@Autowired	
private MockMvc mockMvc;

@Autowired
private DatabaseAccess da;
	
	//this test works only when you run the application as a spring boot project
	@Test
	public void testLoadingIndex() throws Exception {
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	// a test that checks get mapping for events
	@Test
	public void givenEvents_whenGetEvents()
	  throws Exception {
		mockMvc.perform(get("/capstones")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].title", is(da.findAll().get(0).getTitle())));
	}
}
