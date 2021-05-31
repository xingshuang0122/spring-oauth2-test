package com.github.xingshuangs.ros.demo.rosdemo;

import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.Topic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RosDemoApplicationTests {
    private Ros ros;
    private Topic t1, t2, t3, t4;

    @Before
    void setUp(){

    }

    @Test
    void contextLoads() {
    }

    @Test
    void rosTest() {
        this.ros = new Ros();
        this.t1 = new Topic(ros,"turtle1/cmd_vel","geometry_msgs/Twist");

    }

}
