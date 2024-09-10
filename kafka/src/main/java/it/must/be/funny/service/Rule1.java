package it.must.be.funny.service;

public class Rule1  extends HandlerMessage{
    public Rule1(int priority, String ruleName) {
        super(priority, ruleName);
    }

    @Override
    public void handleMessage(Object request, Object response) {
        //process logic rule 1 here
    }
}
