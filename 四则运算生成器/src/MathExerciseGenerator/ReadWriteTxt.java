package MathExerciseGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GC
 */
public class ReadWriteTxt {
    /**
     * 创建txt文档函数
     *
     * @param path 路径
     * @throws IOException
     */
    public void createTxt(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("");
        fileWriter.close();
    }

    /**
     * 读取文本函数
     *
     * @param path 路径
     * @return 返回读取的字符串数组
     * @throws IOException
     */
    public String[] readTxt(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> list = new ArrayList<>();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str);
        }
        Object[] array = list.toArray();
        String[] stringArray = new String[list.size()];
        int i = 0;
        for (Object o : array) {
            if (o instanceof String) {
                //对每个元素进行类型判断
                stringArray[i] = (String) o;
            }
            i++;
        }
        bufferedReader.close();
        fileReader.close();
        return stringArray;
    }

    /**
     * 写入文本函数
     *
     * @param path 路径
     * @param date 写入得到数据
     * @throws IOException
     */

    public void writeTxt(String path, String date) throws IOException {
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(date);
        bufferedWriter.close();
        fileWriter.close();
    }

}
