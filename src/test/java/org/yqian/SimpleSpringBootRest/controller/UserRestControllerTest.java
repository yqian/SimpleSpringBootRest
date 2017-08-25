package org.yqian.SimpleSpringBootRest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.yqian.SimpleSpringBootRest.controller.UserRestController;
import org.yqian.SimpleSpringBootRest.model.User;
import org.yqian.SimpleSpringBootRest.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRestController.class)
public class UserRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService mockService;

	private final ObjectMapper mapper = new ObjectMapper();

	private final List<User> list = new ArrayList<User>();

	@Before
	public void setupUserList() {
		User u = new User();
		u.setId(1);
		u.setName("Joe Schmo");
		u.setRole("Big Boss");
		list.add(u);
		u = new User();
		u.setId(10);
		u.setName("Jane Doe");
		u.setRole("Small Boss");
		list.add(u);
		u = new User();
		u.setId(100);
		u.setName("Mary Major");
		u.setRole("Tiny Boss");
		list.add(u);
	}

	@Test
	public void testListUser() throws Exception {
		when(mockService.listUser()).thenReturn(list);
		MvcResult result = mockMvc.perform(get("/user/list")).andReturn();
		TypeFactory factory = mapper.getTypeFactory();
		List<User> uList = mapper.readValue(result.getResponse().getContentAsString(), factory.constructCollectionType(List.class, User.class));
		// [{"id":1,"name":"Joe Schmo","role":"Big Boss"},//
		//  {"id":10,"name":"Jane Doe","role":"Small Boss"},//
		//  {"id":100,"name":"Mary Major","role":"Tiny Boss"}]
		int expected = 3;
		assertThat(uList.size()).isEqualTo(expected);
	}

	@Test
	public void testListUserNoContent() throws Exception {
		when(mockService.listUser()).thenReturn(new ArrayList<User>());
		RequestBuilder builder = MockMvcRequestBuilders.get("/user/list").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(204);
	}

	@Test
	public void testGetUser() throws Exception {
		when(mockService.getUser(1)).thenReturn(list.get(0));
		MvcResult result = mockMvc.perform(get("/user/1")).andReturn();
		User u = mapper.readValue(result.getResponse().getContentAsString(), User.class);
		String expected = "Joe Schmo";
		assertThat(u.getName()).isEqualTo(expected);
	}

	@Test
	public void testGetUserNoFound() throws Exception {
		when(mockService.getUser(1000)).thenReturn(null);
		RequestBuilder builder = MockMvcRequestBuilders.get("/user/1000").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(404);
	}

	@Test
	public void testUpdateUser() throws Exception {
		User u = new User();
		u.setId(1);
		u.setName("Joe Schmo");
		u.setRole("U R Fired");
		when(mockService.getUser(1)).thenReturn(u);
		String userAsJsonString = mapper.writeValueAsString(u);
		RequestBuilder builder = MockMvcRequestBuilders.put("/user/1", u).contentType(MediaType.APPLICATION_JSON).content(userAsJsonString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	public void testUpdateUserNotFound() throws Exception {
		User u = new User();
		u.setId(1);
		u.setName("Joe Schmo");
		u.setRole("U R Fired");
		when(mockService.getUser(1000)).thenReturn(null);
		String userAsJsonString = mapper.writeValueAsString(u);
		RequestBuilder builder = MockMvcRequestBuilders.put("/user/1000", u).contentType(MediaType.APPLICATION_JSON).content(userAsJsonString);
		MvcResult result = mockMvc.perform(builder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(404);
	}

	@Test
	public void testDeleteUser() throws Exception {
		when(mockService.getUser(1)).thenReturn(list.get(0));
		MvcResult result = mockMvc.perform(delete("/user/1", 1)).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	public void testDeleteUserNotFound() throws Exception {
		when(mockService.getUser(1000)).thenReturn(null);
		MvcResult result = mockMvc.perform(delete("/user/1000", 1000)).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(404);
	}

	@Test
	public void testDeleteAllUser() throws Exception {
		MvcResult result = mockMvc.perform(delete("/user/clear")).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(204);
	}
}
