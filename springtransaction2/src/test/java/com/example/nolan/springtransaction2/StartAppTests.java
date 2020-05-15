package com.example.nolan.springtransaction2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApp.class)
public class StartAppTests {

    @Test
    public void hello() {
        System.out.println("hello world");
    }

}
