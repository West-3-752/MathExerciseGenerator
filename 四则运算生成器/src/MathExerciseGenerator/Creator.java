package MathExerciseGenerator;

import java.util.Random;

/**
 * @author Alva
 */
public class Creator {

    /**
     * 题目生成函数
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
     * @param exerciseArray 查询数组,[0]为题目,[1]为答案
     * @return grade 结果数组,下标为题目序号(从0开始),True则为正确,False则为错误
     */
    public Boolean[] correctAnswer(String[][] exerciseArray){
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
        for (int j = 0; j <= new Random(3).nextInt(); j++) {
        
        }

        return exerciseArray;
    }

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

}
