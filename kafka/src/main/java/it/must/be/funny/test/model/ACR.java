package it.must.be.funny.test.model;

import java.time.LocalDateTime;

public class ACR {
    private final String callId;
    private final double charge;
    private final long timestamp;

    public ACR(String callId, double charge) {
        this.callId = callId;
        this.charge = charge;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ACR{" +
                "callId='" + callId + '\'' +
                ", charge=" + charge +
                ", timestamp=" + timestamp +
                '}';
    }
}
