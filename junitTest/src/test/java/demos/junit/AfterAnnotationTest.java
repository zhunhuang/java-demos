package demos.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class AfterAnnotationTest {

    public List<Integer> list;

    @Before
    public void setUp(){
        System.out.println("初始化list");
        list = new ArrayList<>();
    }

    @After
    public void clean(){
        System.out.println("清理list");
        list = new ArrayList<>();
    }

    @Test
    public void test1(){
        list.add(100);
        Assert.assertEquals(100,list.get(0).intValue());
    }

    @Test
    public void test2(){
        list.add(101);
        Assert.assertEquals(101,list.get(0).intValue());
    }
}
