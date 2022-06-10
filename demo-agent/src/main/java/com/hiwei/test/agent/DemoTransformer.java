package com.hiwei.test.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class DemoTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        ClassPool classPool = ClassPool.getDefault();
        String realClassName = className.replaceAll("/", ".");

        //获取类
        if(MockConfig.isMock(realClassName)){
            try {
                CtClass ctClass = classPool.get(realClassName);
                CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                for (CtMethod m : declaredMethods) {
                    String mName = m.getName();
                    Object mockData = MockConfig.getMockData(realClassName, mName);
                    if(mockData!=null){
                        String mock = String.format("if(true){ return %s ;}", mockData);
                        m.insertBefore(mock);
                    }
                }
                return ctClass.toBytecode();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
