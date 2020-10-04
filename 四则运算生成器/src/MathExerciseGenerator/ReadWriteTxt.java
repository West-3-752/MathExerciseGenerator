package MathExerciseGenerator;

import java.io.*;

/**
 * @author GC
 */
public class ReadWriteTxt {
    /**
     * 创建txt文档函数
     * @param path 路径
     * @throws IOException
     */
    public void creatTxt(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    /**
     * 读取文本函数
     * @param path 路径
     * @return 返回读取的字符串
     * @throws IOException
     */
    public String readTxt(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = null;
        String str = null;
        while ((str = bufferedReader.readLine())!=null) {
            stringBuffer.append(str);
        }
        String string = new String(stringBuffer);
        fileReader.close();
        bufferedReader.close();
        return string;
    }

    /**
     * 写入文本函数
     * @param path 路径
     * @param date 写入得到数据
     * @throws IOException
     */

    public void writeTxt(String path,String date) throws IOException{
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(date);
        fileWriter.close();
        bufferedWriter.close();
    }
}
