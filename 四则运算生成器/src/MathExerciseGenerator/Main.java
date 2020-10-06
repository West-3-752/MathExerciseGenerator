package MathExerciseGenerator;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author GC
 */
public class Main{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ReadWriteTxt readWriteTxt = new ReadWriteTxt();
        Creator creator = new Creator();
        System.out.println("四则运算生成器");
        //过滤
        String s = scanner.next();
        int n = scanner.nextInt();
        //过滤
        String s1 = scanner.next();
        int r = scanner.nextInt();
        /*生成练习题数组*/
        String[][] exercises = creator.createExercise(n,r);
        //在执行目录下生成练习题文本以及答案文本
        readWriteTxt.createTxt("D:\\1.txt");
        readWriteTxt.createTxt("D:\\2.txt");
        //将练习题和答案分别存入两个文本中
        for (int i = 0; i < n; i++){
            String j =String.valueOf(i+1);
            readWriteTxt.writeTxt("D:\\2.txt", j + "." + " ");
            readWriteTxt.writeTxt("D:\\2.txt", exercises[i][0]);
            readWriteTxt.writeTxt("D:\\2.txt","\n");
            readWriteTxt.writeTxt("D:\\1.txt", j + "." + " ");
            readWriteTxt.writeTxt("D:\\1.txt", exercises[i][1]);
            readWriteTxt.writeTxt("D:\\1.txt","\n");
        }
        System.out.println("输入题目路径与答案路径判定对错并统计");
        String path1 = scanner.nextLine();
        String path2 = scanner.nextLine();
        String[][] correctWrong = compareAnswer(path1, path2);
        String correct = exchange(correctWrong[0]);
        String wrong = exchange(correctWrong[1]);
        readWriteTxt.createTxt("D:\\3.txt");
        readWriteTxt.writeTxt("D:\\3.txt", "Correct：" + correctWrong[0].length + "（" + correct);
        readWriteTxt.writeTxt("D:\\3.txt","\n");
        readWriteTxt.writeTxt("D:\\3.txt", "Wrong：" + correctWrong[1].length + "（" + wrong);
    }

    /**
     * 答案比较函数
     * @param path1 题目文件的路径
     * @param path2 答案文件的路径
     * @return correctWrong[2][]，[0]为对的题目序号数组 ，[1]为错的题目序号数组
     * @throws IOException
     */
    public static String[][] compareAnswer(String path1, String path2) throws IOException {
        ReadWriteTxt readWriteTxt = new ReadWriteTxt();
        Creator creator = new Creator();
        String[] exercise = readWriteTxt.readTxt(path1);
        String[] answer = readWriteTxt.readTxt(path2);
        exercise = select(exercise);
        answer = select(answer);
        String[][] exerciseArray = new String[exercise.length][2];
        for (int i = 0; i < exercise.length; i++){
            exerciseArray[i][0] = exercise[i];
            exerciseArray[i][1] = answer[i];
        }
        Boolean[] booleans = creator.correctAnswer(exerciseArray);
        String[][] correctWrong = new String[2][];
        int i = 0;
        int correctLength = 0;
        int wrongLength = 0;
        while (i < booleans.length){

            if (booleans[i] == true){
                correctWrong[0][correctLength] = String.valueOf(i);
                correctLength++;
            }
            else {
                correctWrong[1][wrongLength] = String.valueOf(i);
                wrongLength++;
            }
            i++;
        }
        return correctWrong;
    }

    /**
     * 题目序号数组规范输出函数
     * @param strings 题目序号数组
     * @return s 规范后的字符串
     */
    public static String exchange(String[] strings){
        String s = null;
        for (int i = 0; i < strings.length; i++){
            s = s + strings[i] + "，";
        }
        s+= "）";
        return s;
    }

    /**
     * 题目序号过滤函数
     * @param strings 需要过滤的字符串数组
     * @return 过滤完的字符串数组
     */
    public static String[] select(String[] strings){
        String[] selectArray = new String[strings.length];
        for (int i = 0; i < strings.length; i++){
            selectArray[i] = strings[i].substring(strings[i].indexOf(" ")+1);
        }
        return selectArray;
    }
}
