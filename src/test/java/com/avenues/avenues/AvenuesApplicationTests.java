package com.avenues.avenues;

import com.avenues.avenues.work.file.service.FileStorageService;
import com.avenues.avenues.work.user.dto.*;
import com.avenues.avenues.work.user.repository.IUserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AvenuesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IUserRepo userRepo;
	private FileStorageService fileStorageService;

	@Test
	public void successRegRequest() throws Exception {

		UserRegRequest request = new UserRegRequest("uri", "username", "email");

		String result = mockMvc.perform(post("/user/reg")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		UserRegResponse regResponse = objectMapper.readValue(result, UserRegResponse.class);

		assertNotNull(userRepo.findById(regResponse.getId()));
	}

	@Test
	public void successProfileRequest() throws Exception {

		UserRegRequest regRequest = new UserRegRequest("uri", "username", "email");

		String regResult = mockMvc.perform(post("/user/reg")
						.content(objectMapper.writeValueAsString(regRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		UserRegResponse regResponse = objectMapper.readValue(regResult, UserRegResponse.class);

		String result = mockMvc.perform(post("/user/profile")
						.content(objectMapper.writeValueAsString(new UserProfileRequest(regResponse.getId())))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		UserProfileResponse profileResponse = objectMapper.readValue(result, UserProfileResponse.class);


		assertEquals(regRequest.getEmail(), profileResponse.getEmail());
		assertEquals(regRequest.getUsername(), profileResponse.getUsername());
		assertEquals(regRequest.getUri(), profileResponse.getUri());
	}

	@Test
	public void successChangeOnline() throws Exception {

		UserRegRequest regRequest = new UserRegRequest("uri", "username", "email");

		String regResult = mockMvc.perform(post("/user/reg")
						.content(objectMapper.writeValueAsString(regRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		UserRegResponse regResponse = objectMapper.readValue(regResult, UserRegResponse.class);

		StatusChangeRequest statusChangeRequest =
				new StatusChangeRequest(regResponse.getId(), "Offline");

		String onlineResult = mockMvc.perform(post("/user/online")
						.content(objectMapper.writeValueAsString(statusChangeRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();

		StatusChangeResponse status = objectMapper.readValue(onlineResult, StatusChangeResponse.class);

		assertEquals(statusChangeRequest.getStatus(), status.getCurrentStatus());
	}
}
