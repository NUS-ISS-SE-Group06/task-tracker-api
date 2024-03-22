package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.impl.UserRegistrationServiceImpl;
import com.nus.iss.tasktracker.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.nullValue;


@WebMvcTest(UserRegistrationController.class)
class UserRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationServiceImpl userRegistrationService;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    void testSignUp_Success() throws  Exception{

        String requestBody= """
                {
                       "name":"Firstname1 Lastname1",
                       "email":"User1@test.test",
                       "username" : "user1",
                       "password": "password1",
                       "createdBy": "admin"
                }
                """;

        UserDTO userDTO =new UserDTO();
        userDTO.setUserId(1);
        userDTO.setName("Firstname1 Lastname1");
        userDTO.setEmail("User1@test.test");
        userDTO.setUsername("user1");
        userDTO.setPassword("password1");
        userDTO.setCreatedBy("admin");
        userDTO.setPasswordChangeMandatory("FALSE");


        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    userDTO.setPassword(null);
                    userDTO.setOldPassword(null);
                    userDTO.setNewPassword(null);
                    userDTO.setAuthToken(null);
                    userDTO.setCreatedBy(null);
                    return userDTO;
                });



        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body.userId").value(1))
                .andExpect(jsonPath("$.body.groupId").value(0))
                .andExpect(jsonPath("$.body.email").value("User1@test.test"))
                .andExpect(jsonPath("$.body.name").value("Firstname1 Lastname1"))
                .andExpect(jsonPath("$.body.username").value("user1"))
                .andExpect(jsonPath("$.body.password").value(nullValue()))
                .andExpect(jsonPath("$.body.userRole").value(nullValue()))
                .andExpect(jsonPath("$.body.passwordChangeMandatory").value("FALSE"))
                .andExpect(jsonPath("$.body.oldPassword").value(nullValue()))
                .andExpect(jsonPath("$.body.newPassword").value(nullValue()))
                .andExpect(jsonPath("$.body.authToken").value(nullValue()))
                .andExpect(jsonPath("$.body.groupName").value(nullValue()))
                .andExpect(jsonPath("$.body.createdBy").value(nullValue()))

                .andExpect(jsonPath("$.message").value("SignUp successfully."))
                .andExpect(jsonPath("$.error").value(""));

    }


    @Test
    void testSignUp_Failed() throws  Exception{

        String requestBody= """
                {
                       "name":"Firstname1 Lastname1",
                       "email":"User1@test.test",
                       "username" : "user1",
                       "password": "password1",
                       "createdBy": "admin"
                }
                """;

        UserDTO userDTO =null;

        //Username not available!"
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Username not available!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Username not available!"));


        //Name - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Name - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Name - Please input value!"));


        //Email - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Email - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Email - Please input value!"));

        //Password - Please input value!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password - Please input value!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password - Please input value!"));



        //Password must meet the min. length of 8!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password must meet the min. length of 8!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password must meet the min. length of 8!"));


        //Password must contain alphanumeric characters!
        when(userRegistrationService.signUp(any(UserDTO.class)))
                .thenAnswer( x -> {
                    throw new RuntimeException("Password must contain alphanumeric characters!");
                });

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.body").value(nullValue()))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.error").value("Password must contain alphanumeric characters!"));


    }



}
