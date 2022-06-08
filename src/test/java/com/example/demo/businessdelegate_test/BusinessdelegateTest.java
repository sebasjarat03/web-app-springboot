package com.example.demo.businessdelegate_test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.demo.businessdelegate.BusinessDelegate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BusinessdelegateTest {

    @Mock
    private static RestTemplate restTemplate;

    @InjectMocks
    private static BusinessDelegate bd;
}