package BasicKnowledgeDemo.DynamicProxy;

import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造方法
     * @param target 目标对象
     */
    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("------------------after-------------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//        // 实例化目标对象
//        UserService userService = new UserServiceImpl();
//
//        // 实例化InvocationHandler
//        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
//
//        // 根据目标对象生成代理对象
//        UserService proxy = (UserService) invocationHandler.getProxy();
//
//        // 调用代理对象的方法
//        proxy.add();

        //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserService  userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),  //加载接口的类加载器
                new Class[]{UserService.class},      //一组接口
                new MyInvocationHandler(new UserService() {
                    @Override
                    public void add() {
                        System.out.println("-------------------add--------------------");
                    }
                })); //自定义的InvocationHandler
        userService.add();
    }
}

