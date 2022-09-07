package com.tweetapp.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tweetapp.controller.UserController;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserDetailsService userDetailService;

	@MockBean
	private UserService userService;

	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	User mockUser = new User("testid", "testfirstname", "testlastname", "testemail", "testusername", "testpassword",
			"1234");

	String exampleUserJson = "{\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\","
			+ "\"email\":\"testemail\",\"username\":\"testusername\",\"password\":\"testpassword\",\"contactNumber\":\"1234\"}";

	String missingFirstNameUserJson = "{\"firstName\":\"\",\"lastName\":\"testlastname\","
			+ "\"email\":\"testemail\",\"username\":\"testusername\",\"password\":\"testpassword\",\"contactNumber\":\"1234\"}";

	@Test
	public void retrieveDetailsForUser() throws Exception {

		Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/tweets/user/testusername")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"id\":\"testid\",\"firstName\":\"testfirstname\",\"lastName\":\"testlastname\","
				+ "\"email\":\"testemail\",\"username\":\"testusername\",\"password\":\"testpassword\",\"contactNumber\":\"1234\"}";


		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void registerUser() throws Exception {

		Map<String, String> mockResponse = new HashMap<>();
		mockResponse.put("registration_status", "Successful");

		// userService.saveUser to respond back with mockResponse
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockResponse);

		// Send user as body to /api/v1.0/tweets/register
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/tweets/register")
				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void missingFirstNameRegisterUser() throws Exception {

		Map<String, String> mockResponse = new HashMap<>();
		mockResponse.put("registration_status", "Successful");

		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/tweets/register")
				.accept(MediaType.APPLICATION_JSON).content(missingFirstNameUserJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());

	}

}
