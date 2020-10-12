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
        String[][] exerciseCheckingArray = new String[n][4];
        String[] checkingArray = new String[4];
        StringBuilder exercise;
        String part = "";
        String ans = "-1";
        String num1;
        String num2;
        double type = 0;
        Boolean repeat = false;

        for (int i = 0; i < n; i++) {
            do {
                num1 = getNum(r);
                ans = "-1";
                exercise = new StringBuilder(num1);
                for (int j = 0; j < 3; j++) {
                    do {
                        type = Math.random();
                        num2 = getNum(r);
                        if (type < 0.25) {
                            part = num1 + " + " + num2;
                            ans = plusNum(num1, num2);
                        } else if (type < 0.5) {
                            part = num1 + " - " + num2;
                            ans = minusNum(num1, num2);
                        } else if (type < 0.75) {
                            while ("1".equals(num2)) {
                                num2 = getNum(r);
                            }
                            part = num1 + " * " + num2;
                            ans = multiplyNum(num1, num2);
                        } else {
                            while ("1".equals(num2)) {
                                num2 = getNum(r);
                            }
                            part = num1 + " / " + num2;
                            ans = divideNum(num1, num2);
                        }
                    } while (!ifPositive(ans));
                    if (type >= 0.5 && (exercise.toString().contains("+") || exercise.toString().contains("-"))
                            && (!exercise.toString().contains("*") || !exercise.toString().contains("/"))) {
                        exercise = new StringBuilder("( " + exercise + " )");
                    }
                    checkingArray[j] = num1;
                    num1 = ans;
                    exercise = new StringBuilder(exercise + part.substring(part.indexOf(" ")));
                }
                exerciseArray[i][0] = exercise.toString();
                exerciseArray[i][1] = ans;
                checkingArray[3] = ans;
                sortArray(checkingArray);
                if (i != 0) {
                    repeat = ifRepeat(checkingArray, exerciseCheckingArray, i);
                }
                if (!repeat) {
                    exerciseCheckingArray[i][0] = checkingArray[0];
                    exerciseCheckingArray[i][1] = checkingArray[1];
                    exerciseCheckingArray[i][2] = checkingArray[2];
                    exerciseCheckingArray[i][3] = checkingArray[3];
                }
            } while (repeat);
        }
        return exerciseArray;
    }

    private void sortArray(String[] array) {
        String num1 = array[0];
        String num2 = array[1];
        String num3 = array[2];
        if (ifBigger(num1, num2)) {
            if (ifBigger(num1, num3)) {
                if (ifBigger(num3, num2)) {
                    array[2] = num3;
                    array[3] = num2;
                }
            } else {
                array[0] = num3;
                array[1] = num1;
                array[2] = num2;
            }
        } else {
            if (ifBigger(num2, num3)) {
                array[0] = num2;
                if (ifBigger(num1, num3)) {
                    array[1] = num1;
                    array[2] = num3;
                } else {
                    array[1] = num3;
                    array[2] = num1;
                }
            } else {
                array[0] = num3;
                array[1] = num2;
                array[2] = num1;
            }
        }
    }

    private Boolean ifBigger(String num1, String num2) {
        num1 = toTrueScore(num1);
        num2 = toTrueScore(num2);
        int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
        int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
        int denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
        int molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
        return denominator1 * molecular2 >= denominator2 * molecular1;
    }

    private Boolean ifRepeat(String[] checkingArray, String[][] exerciseCheckingArray, int num) {
        for (int i = 0; i < num; i++) {
            if (checkingArray[3].equals(exerciseCheckingArray[i][3])
                    && checkingArray[2].equals(exerciseCheckingArray[i][2])
                    && checkingArray[1].equals(exerciseCheckingArray[i][1])
                    && checkingArray[0].equals(exerciseCheckingArray[i][0])) {
                return true;
            }
        }
        return false;
    }

    private Boolean ifPositive(String ans) {
        int type = getType(ans);
        switch (type) {
            case INTEGER:
                if (Integer.parseInt(ans) < 0) {
                    return false;
                }
                break;
            case TRUE_SCORE:
                if (Integer.parseInt(ans.substring(0, ans.indexOf("/"))) < 0) {
                    return false;
                }
                break;
            case GREATER_TRUE_SCORE:
                if (Integer.parseInt(ans.substring(0, ans.indexOf("'"))) < 0) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
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
            topic = String.valueOf(random.nextInt(maxNum - 1) + 1);
        } else if (type > 0.8) {
            denominator = random.nextInt(maxNum - 1) + 1;
            molecular = random.nextInt(denominator - 1) + 1;
            topic = String.valueOf(molecular) + '/' + String.valueOf(denominator);
        } else {
            denominator = random.nextInt(maxNum - 1) + 1;
            while (denominator == 1) {
                denominator = random.nextInt(maxNum - 1) + 1;
            }
            molecular = random.nextInt(denominator - 1) + 1;
            topic = String.valueOf(random.nextInt(maxNum - 1) + 1) + "'" + String.valueOf(molecular)
                    + '/' + String.valueOf(denominator);
        }
        return topic;
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
            if (Integer.parseInt(m1) == 0) {
                return s[0];
            } else if (Integer.parseInt(m1) > Integer.parseInt(n1)) {
                int integer = Integer.parseInt(s[0]) + Integer.parseInt(m1) / Integer.parseInt(n1);
                return String.valueOf(integer) + "'" + String.valueOf(Integer.parseInt(m1) % Integer.parseInt(n1)) + "/"
                        + n1;
            }
            return s[0] + "'" + m1 + "/" + n1;
        } else if (getType(str) == TRUE_SCORE) {
            int m = Integer.parseInt(str.substring(0, str.indexOf("/")));
            int n = Integer.parseInt(str.substring(str.indexOf("/") + 1));
            int gcd = getGreatestCommonDivisor(m, n);
            String m1 = String.valueOf(m / gcd);
            String n1 = String.valueOf(n / gcd);
            if (Integer.parseInt(m1) > Integer.parseInt(n1)) {
                if (Integer.parseInt(m1) % Integer.parseInt(n1) == 0) {
                    return String.valueOf(Integer.parseInt(m1) / Integer.parseInt(n1));
                }
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
        num1 = toTrueScore(num1);
        num2 = toTrueScore(num2);
        int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
        int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
        int denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
        int molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
        int molecular = molecular1 * molecular2;
        return reduction(String.valueOf(denominator1 * molecular2 + denominator2 * molecular1)
                + "/" + String.valueOf(molecular));
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
        num1 = toTrueScore(num1);
        num2 = toTrueScore(num2);
        int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
        int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
        int denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
        int molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
        int molecular = molecular1 * molecular2;
        return reduction(String.valueOf(denominator1 * molecular2 - denominator2 * molecular1)
                + "/" + String.valueOf(molecular));
    }

    /**
     * 乘法函数
     *
     * @param num1 乘数1
     * @param num2 乘数2
     * @return ans 结果
     */
    private String multiplyNum(String num1, String num2) {
        num1 = toTrueScore(num1);
        num2 = toTrueScore(num2);
        int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
        int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
        int denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
        int molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
        return reduction(String.valueOf(denominator1 * denominator2)
                + "/" + String.valueOf(molecular2 * molecular1));
    }

    private String divideNum(String num1, String num2) {
        if (getType(num1) == INTEGER) {
            if ("0".equals(num1)) {
                return "-1";
            }
        }
        num1 = toTrueScore(num1);
        num2 = toTrueScore(num2);
        int denominator1 = Integer.parseInt(num1.substring(0, num1.indexOf("/")));
        int molecular1 = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
        int denominator2 = Integer.parseInt(num2.substring(0, num2.indexOf("/")));
        int molecular2 = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
        return reduction(String.valueOf(denominator1 * molecular2)
                + "/" + String.valueOf(denominator2 * molecular1));
    }

    private String toTrueScore(String num) {
        int type = getType(num);
        int molecular1; //分母
        int denominator1; //分子
        if (type == INTEGER) {
            denominator1 = Integer.parseInt(num);
            molecular1 = 1;
        } else if (type == TRUE_SCORE) {
            denominator1 = Integer.parseInt(num.substring(0, num.indexOf("/")));
            molecular1 = Integer.parseInt(num.substring(num.indexOf("/") + 1));
        } else {
            molecular1 = Integer.parseInt(num.substring(num.indexOf("/") + 1));
            denominator1 = Integer.parseInt(num.substring(num.indexOf("'") + 1, num.indexOf("/")))
                    + Integer.parseInt(num.substring(0, num.indexOf("'"))) * molecular1;
        }
        return denominator1 + "/" + molecular1;
    }
}
