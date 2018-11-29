package DesignPatternsDemo;

public class SingletonDemo {

    private SingletonDemo(){}

    private static class SingletonHolder{
        private static final SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance(){
        return SingletonHolder.instance;
    }
}
