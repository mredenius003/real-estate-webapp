package com.mredenius003.realestatewebapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mredenius003.realestatewebapp.model.User;
import com.mredenius003.realestatewebapp.repository.RoleRepository;
import com.mredenius003.realestatewebapp.repository.UserRepository;
import com.mredenius003.realestatewebapp.utils.MockDataGenerator;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
        user = MockDataGenerator.generateUser();

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        final User result = userServiceUnderTest.findUserByEmail(user.getEmail());
        assertEquals(user, result);
    }

    @Test
    public void testSaveUser() {
        User result = userServiceUnderTest.saveUser(user);
        assertEquals(user, result);
    }
}