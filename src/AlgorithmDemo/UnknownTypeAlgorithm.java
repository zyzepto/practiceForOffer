package AlgorithmDemo;

import java.util.*;

public class UnknownTypeAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] price = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            price[i] = scanner.nextInt();
            sum += price[i];
        }
        scanner.nextLine();
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String s = scanner.nextLine();
            String[] strings = s.split(" ");
            Interval interval = new Interval(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]));
            intervals.add(interval);
        }
        System.out.println(disCount(intervals, price, sum));
    }

    public static int disCount(List<Interval> list, int[] price, int sum){
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start < o2.start ? 1 : -1;
            }
        });
        Arrays.sort(price);
        int index = price.length - 1;
        for (Interval interval : list){
            if (interval.start <= price[index]){
                sum -= interval.end;
                index--;
            }
        }
        return sum;
    }

    public static List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start > o2.start ? 1 : -1;
            }
        });
        List<Interval> list = new ArrayList<>();
        for (int j = 0; j < intervals.size(); j++) {
            Interval tem = intervals.get(j);
            for (int i = j+1; i < intervals.size(); i++) {
                Interval interval = intervals.get(i);
                if (tem.end >= interval.start) {
                    if (tem.end >= interval.end){
                    }else {
                        tem.end = interval.end;
                    }
                    j++;
                }
            }
            list.add(new Interval(tem.start, tem.end));
        }
        return list;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
