package org.ms.example;

import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class JarTool {

    public JarTool change(String jarPath, String jarFilePath, String ip) throws IOException {

        if (jarPath != null && jarFilePath != null && ip != null) {
            File file = new File(jarPath);
            JarFile jarFile = new JarFile(file);// 通过jar包的路径 创建 jar包实例
            // JarEntry entry = jarFile.getJarEntry("META-INF/config/adapterconfig.json");// 通过某个文件在jar包中的位置来获取这个文件
            JarEntry entry = jarFile.getJarEntry("resources/properties/db.properties");// 通过某个文件在jar包中的位置来获取这个文件

            InputStream input = jarFile.getInputStream(entry); // 创建该文件输入流

            List<JarEntry> lists = new LinkedList<JarEntry>();
            for (Enumeration<JarEntry> entrys = jarFile.entries(); entrys.hasMoreElements();) {
                JarEntry jarEntry = entrys.nextElement();
                lists.add(jarEntry);
            }

            process(lists, entry, jarPath, jarFilePath, input, ip); // 修改文件内容
            jarFile.close();
        }

        return null;
    }

    private static void process(List<JarEntry> lists, JarEntry entry, String jarPath, String jarFilePath, InputStream input, String ip)
            throws IOException {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buf = new StringBuffer();
        String line = null;

        while ((line = br.readLine()) != null) {
            // // 此处根据实际需要修改某些行的内容
            // if (line.trim().startsWith(
            //         "host:\"http://127.0.0.1:8080/server.do\"")) {
            //     buf.append("host:\"" + ip + "/server.do\",");
            // } else if (line.trim()
            //         .startsWith("host:\"http://127.0.0.1:8080/\"")) {
            //     buf.append("host:\"" + ip + "/\",");
            // }
            if(line.trim().startsWith("db.oracle.username")){
                buf.append("db.oracle.username=ryan");
            }
            // 如果不用修改, 则按原来的内容回写
            else {
                buf.append(line);
            }
            buf.append(System.getProperty("line.separator"));
        }

        write(lists, entry, jarPath, buf.toString());// 将修改后的内容写入jar包中的指定文件

        br.close();
    }

    public static void write(List<JarEntry> lists, JarEntry entry, String jarPath, String content) throws IOException {

        JarOutputStream jos = null;
        FileOutputStream fos = new FileOutputStream(jarPath);
        jos = new JarOutputStream(fos);

        //这种办法，实际相当于又把jar包中的内容，又重新生成了一遍，只是在这个过程中，遇到了我们想修改的文件时，顺便把其内容给修改了  comment by wys 20190524
        try {
            for (JarEntry je : lists) {
                JarEntry newEntry = new JarEntry(je.getName());
                jos.putNextEntry(newEntry);

                // if (je.getName().equals("META-INF/config/adapterconfig.json")) {
                if (je.getName().equals("resources/properties/db.properties")) {
                    jos.write(content.getBytes());
                    continue;
                }
            }
            // 将内容写入文件中

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (jos != null) {
                try {
                    jos.close();
                } catch (IOException e) {
                    jos = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        JarTool jarTool = new JarTool();
        long startTime = System.currentTimeMillis();
        try {
            jarTool.change("D:\\workspace\\myIdea\\byeMess\\out\\artifacts\\byeMess_jar\\byeMess.jar", "resources/properties/db.properties", "123");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}
