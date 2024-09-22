package it.must.be.funny.test.model;

import java.time.LocalDateTime;

public class RC {
    private final String callId;
    private final String reason;
    private final long timestamp;

    public RC(String callId, String reason) {
        this.callId = callId;
        this.reason = reason;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "RC{" +
                "callId='" + callId + '\'' +
                ", reason='" + reason + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
    public String formatRC(){
        return String.join("|",this.callId, this.reason, String.valueOf(this.timestamp));
    }

    public String getCallId() {
        return callId;
    }
}
