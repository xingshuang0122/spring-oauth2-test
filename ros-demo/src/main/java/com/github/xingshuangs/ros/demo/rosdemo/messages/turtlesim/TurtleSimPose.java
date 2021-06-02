package com.github.xingshuangs.ros.demo.rosdemo.messages.turtlesim;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.wpi.rail.jrosbridge.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.JsonObject;

/**
 * @author ShuangPC
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurtleSimPose extends Message {

    /**
     * The name of the x field for the message.
     */
    public static final String FIELD_X = "x";

    /**
     * The name of the y field for the message.
     */
    public static final String FIELD_Y = "y";

    /**
     * The name of the theta field for the message.
     */
    public static final String FIELD_THETA = "theta";

    /**
     * The name of the linear_velocity field for the message.
     */
    public static final String FIELD_LINEAR_VELOCITY = "linear_velocity";

    /**
     * The name of the angular_velocity field for the message.
     */
    public static final String FIELD_ANGULAR_VELOCITY = "angular_velocity";

    /**
     * The message type.
     */
    public static final String TYPE = "turtlesim/Pose";

    private double x;

    private double y;

    @JsonProperty("linear_velocity")
    private double linearVelocity;

    @JsonProperty("angular_velocity")
    private double angularVelocity;

    private double theta;

    /**
     * Create a clone of this TurtleSimPose.
     */
    @Override
    public TurtleSimPose clone() {
        return new TurtleSimPose(this.x, this.y, this.linearVelocity, this.angularVelocity, this.theta);
    }

    /**
     * Create a new TurtleSimPose based on the given JSON string. Any missing values
     * will be set to their defaults.
     *
     * @param jsonString The JSON string to parse.
     * @return A TurtleSimPose message based on the given JSON string.
     */
    public static TurtleSimPose fromJsonString(String jsonString) {
        // convert to a message
        return TurtleSimPose.fromMessage(new Message(jsonString));
    }

    /**
     * Create a new TurtleSimPose based on the given Message. Any missing values will
     * be set to their defaults.
     *
     * @param m The Message to parse.
     * @return A TurtleSimPose message based on the given Message.
     */
    public static TurtleSimPose fromMessage(Message m) {
        // get it from the JSON object
        return TurtleSimPose.fromJsonObject(m.toJsonObject());
    }

    /**
     * Create a new TurtleSimPose based on the given JSON object. Any missing values
     * will be set to their defaults.
     *
     * @param jsonObject The JSON object to parse.
     * @return A TurtleSimPose message based on the given JSON object.
     */
    public static TurtleSimPose fromJsonObject(JsonObject jsonObject) {
        // check the fields
        double x = jsonObject.containsKey(TurtleSimPose.FIELD_X) ? jsonObject
                .getJsonNumber(TurtleSimPose.FIELD_X).doubleValue() : 0.0;
        double y = jsonObject.containsKey(TurtleSimPose.FIELD_Y) ? jsonObject
                .getJsonNumber(TurtleSimPose.FIELD_Y).doubleValue() : 0.0;
        double linearVelocity = jsonObject.containsKey(TurtleSimPose.FIELD_LINEAR_VELOCITY) ?
                jsonObject.getJsonNumber(TurtleSimPose.FIELD_LINEAR_VELOCITY).doubleValue() : 0.0;
        double angularVelocity = jsonObject.containsKey(TurtleSimPose.FIELD_ANGULAR_VELOCITY) ?
                jsonObject.getJsonNumber(TurtleSimPose.FIELD_ANGULAR_VELOCITY).doubleValue() : 0.0;
        double theta = jsonObject.containsKey(TurtleSimPose.FIELD_THETA) ? jsonObject
                .getJsonNumber(TurtleSimPose.FIELD_THETA).doubleValue() : 0.0;
        return new TurtleSimPose(x, y, linearVelocity, angularVelocity, theta);
    }
}
