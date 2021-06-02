package com.github.xingshuangs.ros.demo.rosdemo.handle;

import com.github.xingshuangs.ros.demo.rosdemo.messages.turtlesim.TurtleSimPose;
import edu.wpi.rail.jrosbridge.Topic;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.messages.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ShuangPC
 */
@Slf4j
@Component
public class TurtlePoseHandle implements TopicCallback {

    private final Topic turtlePoseTopic;

    @Autowired
    public TurtlePoseHandle(Topic turtlePoseTopic) {
        this.turtlePoseTopic = turtlePoseTopic;
        this.turtlePoseTopic.subscribe(this);
    }

    @Override
    public void handleMessage(Message message) {
        TurtleSimPose turtleSimPose = TurtleSimPose.fromMessage(message);
        log.debug("x={},y={},linearVelocity={},angularVelocity={},theta={}",
                turtleSimPose.getX(), turtleSimPose.getY(), turtleSimPose.getLinearVelocity(),
                turtleSimPose.getAngularVelocity(), turtleSimPose.getTheta());
    }
}
