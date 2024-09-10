package it.must.be.funny.service;

import java.util.*;

public class ChainRule {
    private static ChainRule chainRule;
    private List<HandlerMessage> handlerMessages;
    private HandlerMessage firstHandlerMessage;

    private ChainRule(){
        this.handlerMessages = new ArrayList<>();
        initChainRule();
        depoy();
    }

    //init all rule for process message here
    public void initChainRule(){
        Rule1 rule1 = new Rule1(1,"rule1");
        Rule2 rule2 = new Rule2(2,"rule2");
        handlerMessages.add(rule1);
        handlerMessages.add(rule2);
    }

    public void depoy(){
        sortByPriorityDesc();
        if (handlerMessages.size() > 0){
            for (int i = 0; i < handlerMessages.size(); i++) {
                if ((i + 1) < handlerMessages.size()){
                    handlerMessages.get(i).setNextHandler(handlerMessages.get(i+1));
                }
            }
            this.firstHandlerMessage = handlerMessages.get(0);
        }
    }
    public void sortByPriorityDesc(){
        Collections.sort(handlerMessages, new Comparator<HandlerMessage>() {
            @Override
            public int compare(HandlerMessage o1, HandlerMessage o2) {
                if (o1.getPriority() < o2.getPriority()){
                    return -1;
                }
                return 0;
            }
        });
    }

    //singleton design pattern
    public static ChainRule getInstance(){
        if (chainRule == null){
            chainRule = new ChainRule();
        }
        return chainRule;
    }

}
