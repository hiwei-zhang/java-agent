package com.hiwei.test.agent;

import java.lang.instrument.Instrumentation;

public class DemoAgent {

    /**
     * 该方法在main方法之前运行
     */
    public static void premain(String arg, Instrumentation instrumentation) {
        instrumentation.addTransformer(new DemoTransformer());
        System.out.println("执行premain方法");
    }
}
