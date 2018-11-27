package AlgorithmDemo;

import java.util.Stack;

public class MinStack extends Stack<Integer> {

    int min;
    boolean isFirst = true;
    public int push(int item) {
        if (isFirst){
            min = item;
            isFirst = false;
        }
        super.push(item - min);
        if (item - min < 0){
            min = item;
        }
        return item;
    }

    public synchronized Integer pop() {
        int temp = super.pop();
        if (temp > 0) {
            return temp + min;
        }else {
            min -= temp;
            return min + temp;
        }
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(3);
        minStack.push(1);
        minStack.push(4);
        int i = 3;
        while (i > 0) {
            System.out.println(minStack.getMin());
            System.out.println(minStack.pop());
            i--;
        }
    }
}
