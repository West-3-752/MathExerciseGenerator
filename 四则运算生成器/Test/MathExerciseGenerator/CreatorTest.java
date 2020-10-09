package MathExerciseGenerator;

import java.util.Random;

public class CreatorTest {

    public static void main(String[] args) {
        System.out.println(reduction("44/16"));
    }

    private static final int GREATER_TRUE_SCORE = 3;
    private static final int TRUE_SCORE = 2;
    private static final int INTEGER = 1;

    private static int getType(String num) {
        char[] numArray = num.toCharArray();
        for (char e : numArray
        ) {
            if (e == '\'') {
                return GREATER_TRUE_SCORE;
            } else if (e == '/') {
                return TRUE_SCORE;
            }
        }
        return INTEGER;
    }


    private static int getGreatestCommonDivisor(int m ,int n){
        if(n == 0){
            return m;
        }
        return getGreatestCommonDivisor(n,m%n);
    }

    /**
     * 约分函数
     * @param str 需要约分的分数
     * @return  约分完后的分数
     */
    private static String reduction(String str){
        if (getType(str)==GREATER_TRUE_SCORE){
            String[] s = str.split("['/]");
            int m = Integer.parseInt(s[1]);
            int n = Integer.parseInt(s[2]);
            int gcd = getGreatestCommonDivisor(m,n);
            String m1 = String.valueOf(m/gcd);
            String n1 = String.valueOf(n/gcd);
            return s[0] + "'" + m1 + "/" + n1;
        }
        else if (getType(str)==TRUE_SCORE){
            String[] s = str.split("[/]");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int gcd = getGreatestCommonDivisor(m,n);
            String m1 = String.valueOf(m/gcd);
            String n1 = String.valueOf(n/gcd);
            if (Integer.parseInt(m1) > Integer.parseInt(n1)) {
                return String.valueOf(Integer.parseInt(m1) / Integer.parseInt(n1)) + "'"
                        + String.valueOf(Integer.parseInt(m1) % Integer.parseInt(n1)) + "/" + n1;
            }
            return m1 + "/" + n1;
        }
        else {
            return str;
        }
    }

}
