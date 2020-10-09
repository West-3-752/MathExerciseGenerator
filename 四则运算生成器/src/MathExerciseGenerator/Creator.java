package MathExerciseGenerator;

import java.lang.invoke.SwitchPoint;
import java.util.Random;

/**
 * @author Alva
 */
public class Creator {

    private static final int GREATER_TRUE_SCORE = 3;
    private static final int TRUE_SCORE = 2;
    private static final int INTEGER = 1;

    /**
     * 题目生成函数
     *
     * @param n 需要输出的题目数量
     * @param r 题目的数值范围
     * @return exerciseArray 返回值为一个二位数组,[0]为题目,[1]为答案
     */
    public String[][] createExercise(int n, int r) {

        String[][] exerciseArray = new String[n][2];

        for (int i = 0; i < n; i++) {

        }

        return exerciseArray;
    }

    /**
     * 查询答案是否正确函数
     *
     * @param exerciseArray 查询数组,[0]为题目,[1]为答案
     * @return grade 结果数组,下标为题目序号(从0开始),True则为正确,False则为错误
     */
    public Boolean[] correctAnswer(String[][] exerciseArray) {
        Boolean[] grade = new Boolean[exerciseArray.length];

        return grade;
    }

    /**
     * @param exerciseArray 储存题目数组
     * @param pos           储存题目的位置
     * @param maxNum        题目中的最大数值
     * @return exerciseArray 增加题目后的数组
     */
    private String[][] getOneExercise(String[][] exerciseArray, int pos, int maxNum) {
        String exercise = getNum(maxNum);
        int maxSymbolNum = 3;
        for (int j = 0; j <= new Random(maxSymbolNum).nextInt(); j++) {

        }

        return exerciseArray;
    }

    /**
     * 随机数生成函数
     *
     * @param maxNum 生成数的最大值
     * @return topic String类型的数值
     */
    private String getNum(int maxNum) {

        Random random = new Random();
        String topic;
        double type = Math.random();
        int denominator;
        int molecular;
        if (type > 0.5) {
            topic = String.valueOf(random.nextInt(maxNum));
        } else if (type > 0.8) {
            denominator = random.nextInt(maxNum);
            molecular = random.nextInt(denominator);
            topic = String.valueOf(molecular) + '/' + String.valueOf(denominator);
        } else {
            denominator = random.nextInt(maxNum);
            molecular = random.nextInt(denominator);
            topic = random.nextInt(maxNum) + "'" + String.valueOf(molecular) + '/' + String.valueOf(denominator);
        }
        return topic;
    }

    private String getAns(String exercise) {
        String num1 = "";
        String num2 = "";
        String symbol = "";
        for (int i = 0; i < exercise.length(); i++) {
            if (exercise.charAt(i) == ' ' || exercise.charAt(i) == '=') {
                if ("".equals(symbol)) {
                    symbol = String.valueOf(exercise.charAt(++i));
                    num2 = num1;
                    num1 = "";
                    i++;
                } else {

                }
            } else {
                num1 = num1 + String.valueOf(exercise.charAt(i));
            }
        }
        return num2;
    }

    /**
     * 类型辨别函数
     *
     * @param num 需要辨别的数值
     * @return 3:大于1的真分数
     * 2:小于1的真分数
     * 1:整数
     */
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

    /**
     * 取最大公约数函数
     *
     * @param m 分子
     * @param n 分母
     * @return 最大公约数
     */
    private static int getGreatestCommonDivisor(int m, int n) {
        if (n == 0) {
            return m;
        }
        return getGreatestCommonDivisor(n, m % n);
    }

    /**
     * 约分函数
     *
     * @param str 需要约分的分数
     * @return 约分完后的分数
     */
    private static String reduction(String str) {
        if (getType(str) == GREATER_TRUE_SCORE) {
            String[] s = str.split("['/]");
            int m = Integer.parseInt(s[1]);
            int n = Integer.parseInt(s[2]);
            int gcd = getGreatestCommonDivisor(m, n);
            String m1 = String.valueOf(m / gcd);
            String n1 = String.valueOf(n / gcd);
            return s[0] + "'" + m1 + "/" + n1;
        } else if (getType(str) == TRUE_SCORE) {
            String[] s = str.split("[/]");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int gcd = getGreatestCommonDivisor(m, n);
            String m1 = String.valueOf(m / gcd);
            String n1 = String.valueOf(n / gcd);
            if (Integer.parseInt(m1) > Integer.parseInt(n1)) {
                return String.valueOf(Integer.parseInt(m1) / Integer.parseInt(n1)) + "'"
                        + String.valueOf(Integer.parseInt(m1) % Integer.parseInt(n1)) + "/" + n1;
            }
            return m1 + "/" + n1;
        } else {
            return str;
        }
    }

    /**
     * 加法函数
     *
     * @param num1 加数1
     * @param num2 加数2
     * @return ans 结果
     */
    private String plusNum(String num1, String num2) {
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

    private String plusTrueScore(String num1, String num2, String ans, int molecular, int i, int j) {
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

    /**
     * 减法函数
     *
     * @param num1 被减数
     * @param num2 减数
     * @return ans 结果
     */
    private String minusNum(String num1, String num2) {
        String ans = "";
        int type1 = getType(num1);
        int type2 = getType(num2);
        int molecular; //分母
        int denominator; //分子
        switch (type1) {
            case INTEGER:
                switch (type2) {
                    case INTEGER:
                        if (Integer.parseInt(num1) - Integer.parseInt(num2) < 0) return "-1";
                        ans = String.valueOf(Integer.parseInt(num1) - Integer.parseInt(num2));
                        break;
                    case TRUE_SCORE:
                        if (Integer.parseInt(num1) > 1) {
                            ans = String.valueOf(Integer.parseInt(num1) - 1) + "'";
                        }
                        denominator = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = ans + String.valueOf((molecular - denominator)) + "/" + String.valueOf(molecular);
                        break;
                    case GREATER_TRUE_SCORE:
                        int integer = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        if (integer >= Integer.parseInt(num1)) {
                            return "-1";
                        } else if (integer + 1 == Integer.parseInt(num1)) {
                            ans = num2.substring(num2.indexOf("'") + 1);
                        } else {
                            ans = String.valueOf(Integer.parseInt(num1) - integer - 1) + "'"
                                    + num2.substring(num2.indexOf("'") + 1);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case TRUE_SCORE:
                switch (type2) {
                    case INTEGER:
                    case GREATER_TRUE_SCORE:
                        return "-1";
                    case TRUE_SCORE:
                        int i = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                        int j = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        denominator = Integer.parseInt(num1.substring(0, num1.indexOf("/"))) * j
                                - Integer.parseInt(num2.substring(0, num2.indexOf("/"))) * i;
                        if (denominator < 0) {
                            return "-1";
                        }
                        ans = String.valueOf(denominator) + "/" + String.valueOf(i * j);
                        break;
                    default:
                        break;
                }
                break;
            case GREATER_TRUE_SCORE:
                int i = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                int j = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                switch (type2) {
                    case INTEGER:
                        int integer = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        if (integer < Integer.parseInt(num2)) {
                            return "-1";
                        } else if (integer == Integer.parseInt(num2)) {
                            ans = num1.substring(num1.indexOf("'") + 1);
                        } else {
                            ans = String.valueOf(integer - Integer.parseInt(num2)) + num1.substring(num1.indexOf("'"));
                        }
                        break;
                    case TRUE_SCORE:
                        denominator = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/"))) * j
                                - Integer.parseInt(num2.substring(0, num2.indexOf("/"))) * i;
                        if (denominator < 0) {
                            if (Integer.parseInt(num1.substring(0, num1.indexOf("'"))) == 1) {
                                ans = String.valueOf(i * j + denominator) + "/" + String.valueOf(i * j);
                            } else {
                                ans = String.valueOf(Integer.parseInt(num1.substring(0, num1.indexOf("'"))) - 1)
                                        + "'" + String.valueOf(i * j + denominator) + "/" + String.valueOf(i * j);
                            }
                        } else if (denominator == 0) {
                            ans = num1.substring(0, num1.indexOf("'"));
                        } else {
                            ans = num1.substring(0, num1.indexOf("'") + 1)
                                    + String.valueOf(denominator) + "/" + String.valueOf(i * j);
                        }
                        break;
                    case GREATER_TRUE_SCORE:
                        int integer1 = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        int integer2 = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        denominator = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/"))) * j
                                - Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/"))) * i;
                        if (integer2 > integer1) {
                            return "-1";
                        } else if (integer2 == integer1) {
                            if (denominator < 0) {
                                return "-1";
                            } else if (denominator == 0) {
                                return "0";
                            }
                            ans = String.valueOf(denominator) + "/" + String.valueOf(i * j);
                        } else if (denominator == 0) {
                            ans = String.valueOf(integer1 - integer2);
                        } else {
                            if (denominator < 0) {
                                denominator = i * j + denominator;
                                if (integer1 != integer2 + 1) {
                                    ans = String.valueOf(integer1 - integer2 - 1) + "'";
                                }
                            } else {
                                ans = String.valueOf(integer1 - integer2) + "'";
                            }
                            ans = ans + String.valueOf(denominator) + "/" + String.valueOf(i * j);
                        }
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return ans;
    }

    /**
     * 乘法函数
     *
     * @param num1 乘数1
     * @param num2 乘数2
     * @return ans 结果
     */
    private String multiplyNum(String num1, String num2) {
        String ans = "";
        int type1 = getType(num1);
        int type2 = getType(num2);
        int molecular; //分母
        int denominator; //分子
        int integer;
        String e;
        if (type2 < type1) {
            type1 = type1 + type2;
            type2 = type1 - type2;
            type1 = type1 - type2;
            e = num1;
            num1 = num2;
            num2 = e;
        }
        switch (type1) {
            case INTEGER:
                switch (type2) {
                    case INTEGER:
                        ans = String.valueOf(Integer.parseInt(num1) * Integer.parseInt(num2));
                        break;
                    case TRUE_SCORE:
                        denominator = Integer.parseInt(num2.substring(0, num2.indexOf("/"))) * Integer.parseInt(num1);
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        if (denominator > molecular) {
                            ans = "1'" + String.valueOf(denominator - molecular) + num2.substring(num2.indexOf("/"));
                        }
                        break;
                    case GREATER_TRUE_SCORE:
                        integer = Integer.parseInt(num2.substring(0, num2.indexOf("'"))) * Integer.parseInt(num1);
                        denominator = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")))
                                * Integer.parseInt(num1);
                        molecular = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        if (denominator > molecular) {
                            ans = String.valueOf(integer + 1) + "'" + String.valueOf(denominator - molecular) + "/"
                                    + String.valueOf(molecular);
                        } else {
                            ans = String.valueOf(integer) + "'" + String.valueOf(denominator) + "/"
                                    + String.valueOf(molecular);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case TRUE_SCORE:
                int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
                int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                int denominator2;
                int molecular2;
                switch (type2) {
                    case TRUE_SCORE:
                        denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(denominator1 * denominator2) + "/"
                                + String.valueOf(molecular1 * molecular2);
                        break;
                    case GREATER_TRUE_SCORE:
                        denominator2 = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        denominator2 += Integer.parseInt(num2.substring(0, num2.indexOf("'"))) * molecular2;
                        integer = (denominator2 * denominator1) / (molecular1 * molecular2);
                        denominator = (denominator2 * denominator1) % (molecular1 * molecular2);
                        ans = String.valueOf(integer) + "'" + String.valueOf(denominator) + "/"
                                + String.valueOf(denominator1 * denominator2);
                    default:
                        break;
                }
                break;
            case GREATER_TRUE_SCORE:
                int integer1 = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                int integer2 = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                denominator1 = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")));
                molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                denominator2 = Integer.parseInt(num2.substring(num2.indexOf("'") + 1, num2.indexOf("/")));
                molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                denominator1 += integer1 * molecular1;
                denominator2 += integer2 * molecular2;
                integer = (denominator2 * denominator1) / (molecular1 * molecular2);
                denominator = (denominator2 * denominator1) % (molecular1 * molecular2);
                ans = String.valueOf(integer) + "'" + String.valueOf(denominator) + "/"
                        + String.valueOf(denominator1 * denominator2);
                break;
            default:
                break;
        }
        return ans;
    }

    private String divideNum(String num1, String num2) {
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
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1)) * Integer.parseInt(num2);
                        denominator1 = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")))
                                + integer * molecular1;
                        denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                        ans = String.valueOf(denominator1 * molecular2) + "/"
                                + String.valueOf(molecular1 * denominator2);
                        break;
                    case GREATER_TRUE_SCORE:
                        integer1 = Integer.parseInt(num1.substring(0, num1.indexOf("'")));
                        molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1)) * Integer.parseInt(num2);
                        denominator1 = Integer.parseInt(num1.substring(num1.indexOf("'") + 1, num1.indexOf("/")))
                                + integer1 * molecular1;
                        integer2 = Integer.parseInt(num2.substring(0, num2.indexOf("'")));
                        molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1)) * Integer.parseInt(num2);
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
