package BasicKnowledgeDemo;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyThead(1));
        Thread thread2 = new Thread(new MyThead(2));
        System.out.println("main thread is begun");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main thread is finished");
    }
}

class MyThead implements Runnable{
    private int num;

    public MyThead(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + num);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + num + "执行完毕");
    }
}
