package it.must.be.funny.service;

public class Rule2 extends HandlerMessage{
    public Rule2(int priority, String ruleName) {
        super(priority, ruleName);
    }

    @Override
    public void handleMessage(Object request, Object response) {
        //process logic rule 2 here
    }
}
