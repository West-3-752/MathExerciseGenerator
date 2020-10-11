package MathExerciseGenerator;

import java.util.Random;

public class CreatorTest {

    public static void main(String[] args) {
        Creator creator = new Creator();
        String[][] a = creator.createExercise(100, 10);
        for (int i = 0; i < 100; i++) {
            System.out.println(a[i][0]);
            System.out.println(a[i][1]);
        }
    }

}
