package MathExerciseGenerator;

import java.util.Random;

public class CreatorTest {

    public static void main(String[] args) {
        System.out.println(divideNum("2'2/3","2'1/2"));
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
                if (Integer.parseInt(m1) % Integer.parseInt(n1) == 0) {
                    return String.valueOf(Integer.parseInt(m1) / Integer.parseInt(n1));
                }
                return String.valueOf(Integer.parseInt(m1) / Integer.parseInt(n1)) + "'"
                        + String.valueOf(Integer.parseInt(m1) % Integer.parseInt(n1)) + "/" + n1;
            }
            return m1 + "/" + n1;
        }
        else {
            return str;
        }
    }

    private static String divideNum(String num1, String num2) {
        String ans = "";
        int type1 = getType(num1);
        int type2 = getType(num2);
        int molecular; //分母
        int denominator; //分子
        int molecular1;
        int denominator1;
        int molecular2;
        int denominator2;
        int integer;
        int integer1;
        int integer2;
        switch (type1) {
            case INTEGER:
                switch (type2) {
                    case INTEGER:
                        ans = num1 + "/" + num2;
                        break;
                    case TRUE_SCORE:
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(molecular * Integer.parseInt(num1)) + "/"
                                + num2.substring(0, num2.indexOf("/"));
                        break;
                    case GREATER_TRUE_SCORE:
                        integer = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        denominator = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")))
                                * integer;
                        ans = String.valueOf(molecular * Integer.parseInt(num1)) + "/"
                                + num2.substring(0, num2.indexOf("/"));
                        break;
                    default:
                        break;
                }
                break;
            case TRUE_SCORE:
                switch (type2) {
                    case INTEGER:
                        denominator = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(denominator) + "/" + String.valueOf(molecular * Integer.parseInt(num2));
                        break;
                    case TRUE_SCORE:
                        denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                        denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(denominator1 * molecular2) + "/"
                                + String.valueOf(molecular1 * denominator2);
                        break;
                    case GREATER_TRUE_SCORE:
                        integer = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        denominator2 = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")))
                                + integer * molecular2;
                        ans = String.valueOf(denominator1 * molecular2) + "/"
                                + String.valueOf(molecular1 * denominator2);
                        break;
                    default:
                        break;
                }
            case GREATER_TRUE_SCORE:
                switch (type2){
                    case INTEGER:
                        integer = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        molecular = Integer.parseInt(num1.substring(num1.indexOf("/") + 1)) * Integer.parseInt(num2);
                        denominator = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")))
                                + integer * molecular;
                        ans = String.valueOf(denominator) + "/" + String.valueOf(molecular);
                        break;
                    case TRUE_SCORE:
                        integer = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                        denominator1 = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")))
                                + integer * molecular1;
                        denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(denominator1 * molecular2) + "/"
                                + String.valueOf(molecular1 * denominator2);
                        break;
                    case GREATER_TRUE_SCORE:
                        integer1 = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                        denominator1 = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")))
                                + integer1 * molecular1;
                        integer2 = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        denominator2 = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")))
                                + integer2 * molecular2;
                        ans = String.valueOf(denominator1 * molecular2) + "/"
                                + String.valueOf(molecular1 * denominator2);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return reduction(ans);
    }

}
