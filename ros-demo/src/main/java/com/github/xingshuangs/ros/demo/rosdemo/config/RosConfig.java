package com.github.xingshuangs.ros.demo.rosdemo.config;

import edu.wpi.rail.jrosbridge.JRosbridge;
import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ShuangPC
 */
@Slf4j
@Configuration
public class RosConfig {

    @Bean
    public Ros rosBean() {
        String ip = "192.168.239.128";
        int port = 9090;
        Ros ros = new Ros(ip, port);
        if (ros.connect()) {
            log.info("RosBridge连接成功，IP：[{}]，端口号：[{}]", ip, port);
        } else {
            log.error("RosBridge连接成功，IP：[{}]，端口号：[{}]", ip, port);
            throw new RuntimeException(String.format("RosBridge连接失败，IP：[%s]，端口号：[%d]", ip, port));
        }
        return ros;
    }

    @Bean
    public Topic turtleCmdVelTopic(Ros ros) {
        return new Topic(ros, "turtle1/cmd_vel", "geometry_msgs/Twist", JRosbridge.CompressionType.none, 100);
    }

    @Bean
    public Topic turtlePoseTopic(Ros ros) {
        return new Topic(ros, "/turtle1/pose", "turtlesim/Pose", JRosbridge.CompressionType.none,1000);
    }
}
