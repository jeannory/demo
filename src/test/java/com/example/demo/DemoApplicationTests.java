package com.example.demo;

import com.example.demo.exceptions.CustomFileException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @InjectMocks
    private DemoApplication demoApplication;

    @Mock
    private SpringApplication springApplication;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void test_main_when_all_parameters_valid(){
    }

    @Test
    public void test_main_when_throw_CustomFileException_stop_the_application(){
    }

    @Test
    public void test_main_when_throw_CustomConfigParameterException_stop_the_application(){
    }

}
