package com.hiwei.test.agent;

import java.io.*;
import java.lang.instrument.Instrumentation;
import java.nio.charset.StandardCharsets;

public class DemoAgent {
    /**
     * 该方法在main方法之前运行
     * @param arg 文件路径
     * @param instrumentation
     */
    public static void premain(String arg, Instrumentation instrumentation) {
        //读取mock文件
        try {
            FileInputStream file = new FileInputStream(arg);
            BufferedReader rd = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            String configStr = readToString(rd);
            MockConfig.parseConfig(configStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        instrumentation.addTransformer(new DemoTransformer());
    }
    private static String readToString(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = rd.read()) != -1) {
            sb.append((char) i);
        }
        return sb.toString();
    }
}
