package it.must.be.funny.service;

public abstract class HandlerMessage {
    private int priority;
    private String ruleName;
    private HandlerMessage nextHandler;

    public HandlerMessage() {
    }

    public HandlerMessage(int priority, String ruleName) {
        this.priority = priority;
        this.ruleName = ruleName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public HandlerMessage getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(HandlerMessage nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleMessage(Object request, Object response);

    public void chainMessage(Object request, Object response){
        handleMessage(request, response);
        //điều kiện để kết thúc lặp todo
        if (response == null){
            nextHandler.chainMessage(request, response);
        }
    }
}
