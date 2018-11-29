package DesignPatternsDemo.ChainOfResponsibility;

public class ConcreteHandler1 extends Handler {

    public ConcreteHandler1(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE1){
            System.out.println(request.getName() + " is handled by handle1");
            return;
        }
        if (nextHandler != null) nextHandler.handleRequest(request);
    }
}
