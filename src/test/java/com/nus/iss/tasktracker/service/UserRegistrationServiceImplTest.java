package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.impl.UserRegistrationServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceImplTest {
    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    @Test
    void testSignUp_Success(){

        UserInfo userEntity=new UserInfo();
        userEntity.setUserid(1);
        userEntity.setName("Tester Name");
        userEntity.setPassword("password1");


        UserDTO returnDTO=new UserDTO();
        returnDTO.setUserId(1);
        returnDTO.setUsername("user1");
        returnDTO.setEmail("user1@test.test");



        UserDTO requestDTO=new UserDTO();
        requestDTO.setName("Tester Name");
        requestDTO.setEmail("user1@test.test");
        requestDTO.setUsername("user1");
        requestDTO.setPassword("password1");
        requestDTO.setCreatedBy("admin");



        when(userInfoRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.userEntityToUserDTO(userEntity)).thenReturn(returnDTO);


        UserDTO output = userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));

        verify(userInfoRepository).save(userEntity);
        verify(userMapper).userEntityToUserDTO(userEntity);


        assertEquals("user1",output.getUsername());
        assertEquals("user1@test.test",output.getEmail());


    }







}


