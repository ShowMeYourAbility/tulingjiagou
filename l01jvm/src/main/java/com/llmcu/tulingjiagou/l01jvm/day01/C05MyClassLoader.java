package com.llmcu.tulingjiagou.l01jvm.day01;

import java.io.FileInputStream;

public class C05MyClassLoader extends ClassLoader{
    private String classPath;

    public C05MyClassLoader(String classPath) {
        super();
        this.classPath = classPath;
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();

                long t1 = System.nanoTime();
                if(name.startsWith("com.llmcu")){
                    c = findClass(name);
                }else{
                    c = getParent().loadClass(name);
                }
//                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        C05MyClassLoader myClassLoader = new C05MyClassLoader("D:\\workspace\\tulinjiagou\\l01jvm\\myclasses");
        Class<?> user1Class = myClassLoader.loadClass("com.llmcu.tulingjiagou.l01jvm.day01.User1");
        System.out.println(user1Class.getClassLoader().getClass().getName());
    }

}
