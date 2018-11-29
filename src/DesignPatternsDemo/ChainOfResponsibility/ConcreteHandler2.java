package DesignPatternsDemo.ChainOfResponsibility;

public class ConcreteHandler2 extends Handler {

    public ConcreteHandler2(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2){
            System.out.println(request.getName() + " is handled by handle2");
            return;
        }
        if (nextHandler != null) nextHandler.handleRequest(request);
    }
}
