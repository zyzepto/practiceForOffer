package BasicKnowledgeDemo;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {

    public static void main(String[] args) {

        //优先队列自然排序示例
        Queue<IPCount> integerPriorityQueue = new PriorityQueue<>(7);

        IPCount ipCount1 = new IPCount("a", 2);
        IPCount ipCount2 = new IPCount("b", 1);
        IPCount ipCount3 = new IPCount("c", 7);
        IPCount ipCount4 = new IPCount("d", 3);
        IPCount ipCount5 = new IPCount("e", 3);

        integerPriorityQueue.add(ipCount1);
        integerPriorityQueue.add(ipCount2);
        integerPriorityQueue.add(ipCount3);
        integerPriorityQueue.add(ipCount4);
        integerPriorityQueue.add(ipCount5);

        for (int i = 0; i < 5; i++) {
            System.out.println(integerPriorityQueue.poll().toString());
        }

    }

}

class IPCount implements Comparable<IPCount>{
    private String name;

    private int count;

    public IPCount(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", count = " + count + "]";
    }

    @Override
    public int compareTo(IPCount ipCount) {
        int numbera = this.getCount();
        int numberb = ipCount.getCount();
        if(numberb > numbera)
        {
            return 1;
        }
        else if(numberb<numbera)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
