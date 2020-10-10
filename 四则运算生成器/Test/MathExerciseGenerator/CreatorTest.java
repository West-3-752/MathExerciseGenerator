package MathExerciseGenerator;

import java.util.Random;

public class CreatorTest {

    public static void main(String[] args) {

        System.out.println(plusNum("2/3", "1/2"));
        System.out.println("shabi");
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

    private static String plusNum(String num1, String num2) {
        String ans = "";
        String e;
        int type1 = getType(num1);
        int type2 = getType(num2);
        if (type1 < type2) {
            type1 = type1 + type2;
            type2 = type1 - type2;
            type1 = type1 - type2;
            e = num1;
            num1 = num2;
            num2 = e;
        }
        if (type1 == type2) {
            switch (type1) {
                case INTEGER:
                    ans = String.valueOf((Integer.parseInt(num1) + Integer.parseInt(num2)));
                    break;
                case GREATER_TRUE_SCORE:
                    ans = String.valueOf(Integer.parseInt(num1.substring(0, num1.indexOf("'"))) +
                            Integer.parseInt(num2.substring(0, num2.indexOf("'")))) + "'";
                    num1 = num1.substring(num1.indexOf("'") + 1);
                    num2 = num2.substring(num2.indexOf("'") + 1);
                case TRUE_SCORE:
                    int molecular; //分母
                    int denominator; //分子
                    int i = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                    int j = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                    molecular = i * j;
                    ans = plusTrueScore(num1, num2, ans, molecular, i, j);
                    break;
                default:
                    break;
            }
        } else {
            if (type1 == GREATER_TRUE_SCORE && type2 == TRUE_SCORE) {
                ans = num1.substring(0, num1.indexOf("'") + 1);
                num1 = num1.substring(num1.indexOf("'") + 1);
                int molecular; //分母
                int denominator; //分子
                int i = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                int j = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                molecular = i * j;
                ans = plusTrueScore(num1, num2, ans, molecular, i, j);
            } else if (type1 == GREATER_TRUE_SCORE && type2 == INTEGER) {
                ans = String.valueOf(Integer.parseInt(num1.substring(0, num1.indexOf("'"))) + num2) +
                        num1.substring(num1.indexOf("'"));
            } else {
                ans = String.valueOf(num2) + "'" + num1;
            }
        }
        return ans;
    }

    private static String plusTrueScore(String num1, String num2, String ans, int molecular, int i, int j) {
        int denominator;
        denominator = Integer.parseInt(num1.substring(0, num1.indexOf("/"))) * j
                + Integer.parseInt(num2.substring(0, num2.indexOf("/"))) * i;
        if (denominator > molecular && "".equals(ans)) {
            denominator -= molecular;
            ans = ans + "1'";
        } else if (denominator > molecular) {
            denominator -= molecular;
            ans = String.valueOf(Integer.parseInt(ans.substring(0, ans.indexOf("'"))) + 1) + "'";
        }
        int cause = 1;
        for (int k = 1; k <= denominator; k++) {
            if (denominator % k == 0 && molecular % k == 0) {
                cause = k;
            }
        }
        ans = ans + String.valueOf(denominator / cause) + '/' + String.valueOf(molecular / cause);
        return ans;
    }

}
