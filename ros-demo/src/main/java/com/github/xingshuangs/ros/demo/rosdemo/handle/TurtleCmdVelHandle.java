package com.github.xingshuangs.ros.demo.rosdemo.handle;

import edu.wpi.rail.jrosbridge.Topic;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.geometry.Twist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ShuangPC
 */
@Slf4j
@Component
public class TurtleCmdVelHandle implements TopicCallback {

    private final Topic turtleCmdVelTopic;

    @Autowired
    public TurtleCmdVelHandle(Topic turtleCmdVelTopic) {
        this.turtleCmdVelTopic = turtleCmdVelTopic;
        this.turtleCmdVelTopic.subscribe(this);
    }

    @Override
    public void handleMessage(Message message) {
        Twist twist = Twist.fromMessage(message);
        log.debug("linear: x={},y={},z={}| angler: x={},y={},z={}",
                twist.getLinear().getX(), twist.getLinear().getY(), twist.getLinear().getZ(),
                twist.getAngular().getX(), twist.getAngular().getY(), twist.getAngular().getZ());
    }
}
