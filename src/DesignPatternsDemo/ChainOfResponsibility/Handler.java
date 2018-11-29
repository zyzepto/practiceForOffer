package DesignPatternsDemo.ChainOfResponsibility;

public abstract class Handler {

    protected Handler nextHandler;

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void handleRequest(Request request);
}
