package AlgorithmDemo;

import java.util.Iterator;
import java.util.Stack;

public class StackSort {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(2);
//        s.push(8);
        s.push(9);
        s.push(3);
        s.push(1);
        Stack<Integer> res = stackSort(s);
        Iterator<Integer> iterator = res.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            System.out.println(i);
        }
    }

    private static Stack<Integer> stackSort(Stack<Integer> s){
        Stack<Integer> res = new Stack<>();
        while (!s.empty()) {
            Integer tem = s.pop();
            while (!res.empty() && tem < res.peek()){
                s.push(res.pop());
            }
            res.push(tem);
        }
        return res;
    }

}
