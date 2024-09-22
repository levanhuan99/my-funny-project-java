package it.must.be.funny.test.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class IDP {
    private final String callId;
    private final String caller;
    private final String callee;
    private final long timestamp;

    public IDP(String caller, String callee) {
        this.callId = UUID.randomUUID().toString();
        this.caller = caller;
        this.callee = callee;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "IDP{" +
                "callId='" + callId + '\'' +
                ", caller='" + caller + '\'' +
                ", callee='" + callee + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String formatIDP(){
        return String.join("|",this.callId, this.caller, this.callee,String.valueOf(this.timestamp));
    }
    public String getCallId() {
        return callId;
    }

}
