package MathExerciseGenerator;

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
            topic = random.nextInt(maxNum) + '\'' + String.valueOf(molecular) + '/' + String.valueOf(denominator);
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
    private int getType(String num) {
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
        }else {
            if(type1 == GREATER_TRUE_SCORE && type2 == TRUE_SCORE){
                ans = num1.substring(0,num1.indexOf("'") + 1);
                num1 = num1.substring(num1.indexOf("'") + 1);
                int molecular; //分母
                int denominator; //分子
                int i = Integer.parseInt(num1.substring(num1.indexOf("/") + 1));
                int j = Integer.parseInt(num2.substring(num2.indexOf("/") + 1));
                molecular = i * j;
                ans = plusTrueScore(num1, num2, ans, molecular, i, j);
            } else if(type1 == GREATER_TRUE_SCORE && type2 == INTEGER){
                ans = String.valueOf(Integer.parseInt(num1.substring(0,num1.indexOf("'"))) + num2) +
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
        }else if(denominator > molecular){
            denominator -= molecular;
            ans = String.valueOf(Integer.parseInt(ans.substring(0,ans.indexOf("'"))) + 1) + "'";
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
