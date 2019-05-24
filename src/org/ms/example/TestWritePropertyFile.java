package org.ms.example;

import java.io.*;
import java.util.Properties;

public class TestWritePropertyFile {

    public static void main(String[] args) {
        execute();
    }

    public static void execute(){
        Properties props = new Properties();
        File file = new File("D:\\test.properties");

        InputStreamReader input = null;
        try {
            input = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(input);
            props.load(br);

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(props.getProperty("name"));

        props.setProperty("age", "28");
        props.setProperty("sex", "male");
        props.setProperty("address", "zhengzhou");

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            props.store(bw, "edit age");

            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
