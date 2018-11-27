package AlgorithmDemo;

import java.util.HashMap;
import java.util.Map;

public class IntegerAlgorithmDdemo {

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(2, 3));
    }

    public static String multiply(String num1, String num2){
        // 先把string翻转
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        int[] d = new int[n1.length()+n2.length()];		// 构建数组存放乘积
        for(int i=0; i<n1.length(); i++){
            for(int j=0; j<n2.length(); j++){
                d[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');		// 在正确位置累加乘积
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<d.length; i++){
            int digit = d[i]%10;		// 当前位
            int carry = d[i]/10;		// 进位
            if(i+1<d.length){
                d[i+1] += carry;
            }
            sb.append(digit);
        }
        sb.reverse();
         // 移除前导零
         while(sb.charAt(0)=='0' && sb.length()>1){
         	sb.deleteCharAt(0);
         }
        return sb.toString();
    }

    /**
     * @author zepto
     * @Description 分式转小数
     * @date 2018/10/29
     * @return java.lang.String
     **/
    public static String fractionToDecimal(int numerator, int denominator) {
        long longNumerator = Math.abs((long)numerator);
        long longDenominator = Math.abs((long)denominator);
        StringBuilder sb = new StringBuilder();
        if ((long)numerator*denominator < 0) sb.append("-");
        sb.append(longNumerator/longDenominator);
        long remainer = longNumerator % longDenominator;
        if ( remainer == 0) return sb.toString();
        sb.append(".");
        StringBuilder fracSb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        int index = 0;
        while (remainer != 0){
            remainer *= 10;
            long nextRemainer = remainer % longDenominator;
            if (map.containsKey(remainer)){
                fracSb.insert(map.get(remainer), "(");
                fracSb.append(")");
                break;
            }
            map.put(remainer, index++);
            fracSb.append(remainer / longDenominator);
            remainer = nextRemainer;
        }
        sb.append(fracSb);
        return sb.toString();
    }
}
