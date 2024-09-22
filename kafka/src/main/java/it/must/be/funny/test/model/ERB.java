package it.must.be.funny.test.model;

import java.time.LocalDateTime;

public class ERB {
    private final String callId;
    private final String event;
    private final long timestamp;

    public ERB(String callId, String event) {
        this.callId = callId;
        this.event = event;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ERB{" +
                "callId='" + callId + '\'' +
                ", event='" + event + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
    public String formatERB(){
        return String.join("|",this.callId, this.event, String.valueOf(this.timestamp));
    }

    public String getCallId() {
        return callId;
    }
}
