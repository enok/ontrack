package com.accounting.personal.ontrack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OntrackApplicationTest {

    @Test
    public void main() {
        OntrackApplication.main(new String[]{""});
    }
}