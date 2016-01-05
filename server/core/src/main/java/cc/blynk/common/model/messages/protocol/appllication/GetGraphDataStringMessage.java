package cc.blynk.common.model.messages.protocol.appllication;

import cc.blynk.common.model.messages.StringMessage;

import static cc.blynk.common.enums.Command.*;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 2/1/2015.
 */
public class GetGraphDataStringMessage extends StringMessage {

    public GetGraphDataStringMessage(int messageId, String body) {
        super(messageId, GET_GRAPH_DATA, body.length(), body);
    }

    @Override
    public String toString() {
        return "GetGraphDataStringMessage{" + super.toString() + "}";
    }
}