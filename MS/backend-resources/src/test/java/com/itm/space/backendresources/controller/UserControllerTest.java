package com.itm.space.backendresources.controller;

import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends BaseIntegrationTest {

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_whenSuccessful_thenReturnIsOk() throws Exception {

        UserRequest request = new UserRequest(
                "username26",
                "java2@yandex.ru",
                "userMegaPassword",
                "Kotlin",
                "Javavovich"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(request);
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_whenInvalidEmail_thenReturnBadRequest() throws Exception {

        UserRequest request = new UserRequest(
                "username26",
                "invalid-java",
                "userMegaPassword",
                "Kotlin",
                "Javavovich"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_whenInvalidPassword_thenReturnBadRequest() throws Exception {

        UserRequest request = new UserRequest(
                "username26",
                "javaIsNotInvalid@yandex.ru",
                "1",
                "Kotlin",
                "Javavovich"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isBadRequest());

    }

    @Test
    void createUser_whenNotAuth_thenReturnUnauthorized() throws Exception {
        UserRequest request = new UserRequest(
                "username26",
                "javaIsNotInvalid@yandex.ru",
                "13453",
                "Kotlin",
                "Javavovich"
        );
        doNothing().when(userService).createUser(request);
        mockMvc.perform(requestWithContent(post("/api/users"), request))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void getUser_whenSuccessful_thenReturnIsOk() throws Exception {
        UUID testUserId = UUID.randomUUID();
        UserResponse response = new UserResponse(
                "Java",
                "Kotlin",
                "java@gmail.com",
                List.of("MODERATOR"),
                List.of("Moderators")
        );

        when(userService.getUserById(testUserId)).thenReturn(response);
        mockMvc.perform(get("/api/users/" + testUserId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Java"))
                .andExpect(jsonPath("$.lastName").value("Kotlin"))
                .andExpect(jsonPath("$.email").value("java@gmail.com"))
                .andExpect(jsonPath("$.groups").isArray());
        verify(userService, times(1)).getUserById(testUserId);
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void getUser_whenInvalidId_thenReturnBadRequest() throws Exception {
        UUID testUserId = UUID.randomUUID();

        when(userService.getUserById(testUserId)).thenThrow(new BackendResourcesException("User not found",
                HttpStatus.BAD_REQUEST));
        mockMvc.perform(get("/api/users/" + testUserId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User not found"));
    }

    @Test
    void getUser_whenNotAuth_thenReturnUnauthorized() throws Exception {
        UUID testUserId = UUID.randomUUID();
        mockMvc.perform(get("/api/users/" + testUserId))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR", username = "user")
    void helloEndpoint_whenSuccessful_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/api/users/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("user"));
    }

    @Test
    void helloEndpoint_whenNotAuth_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/hello"))
                .andExpect(status().isUnauthorized());
    }

}
