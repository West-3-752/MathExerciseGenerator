package MathExerciseGenerator;

import java.io.IOException;

/**
 * @author GC
 */
public class Main{
    public static void main(String[] args) throws IOException {
        ReadWriteTxt readWriteTxt = new ReadWriteTxt();
        readWriteTxt.creatTxt("./Exercises.txt");
    }
}
