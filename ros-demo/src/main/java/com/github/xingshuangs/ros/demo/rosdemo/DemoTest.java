package com.github.xingshuangs.ros.demo.rosdemo;

import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.Topic;
import edu.wpi.rail.jrosbridge.messages.geometry.Twist;
import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;

import java.util.Scanner;

/**
 * @author ShuangPC
 */

public class DemoTest {

    public static void main(String[] args) {
        Ros ros = new Ros("192.168.239.128",9090);
        if(!ros.connect()) {
            System.out.println("连接服务器失败");
            return;
        }
        System.out.println("连接服务器成功");
        Topic cmdVel = new Topic(ros, "turtle1/cmd_vel", "geometry_msgs/Twist");
        Topic pose = new Topic(ros, "/turtle1/pose", "turtlesim/Pose");

//        pose.subscribe(message -> {
//            System.out.println(message);
//        });
        Scanner sc = new Scanner(System.in);
        System.out.println("输入q为退出，A,S,D,W为上下左右");
        String input = "";

        while (!"q".equals(input)) {
            switch (input){
                case "A":
                    cmdVel.publish(new Twist(new Vector3(0,0,0),new Vector3(0,0,1)));
                    break;
                case "S":
                    cmdVel.publish(new Twist(new Vector3(-1,0,0),new Vector3(0,0,0)));
                    break;
                case "D":
                    cmdVel.publish(new Twist(new Vector3(0,0,0),new Vector3(0,0,-1)));
                    break;
                case "W":
                    cmdVel.publish(new Twist(new Vector3(1,0,0),new Vector3(0,0,0)));
                    break;
            }
            input = sc.next().toUpperCase();
        }
    }
}
