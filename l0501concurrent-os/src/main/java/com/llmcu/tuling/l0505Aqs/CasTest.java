package com.llmcu.tuling.l0505Aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 模仿CAS操作
 * 1、unsafe的生成。
 * 1.1、不能直接使用Unsafe.getUnsafe()，因为这个方法被@CallerSensitive（调用者敏感）注解，只有bootstrapClassLoader和ExtClassLoader加载的类才能调用这个方法。
 * 1.2、但是Unsafe提供了一个Unsafe类型的常量，可以使用反射获取Unsafe的实例。
 * 1.2.1、Field.get(Object)方法，可以获取指定对象的指定字段的值。If the underlying field is a static field, the obj argument is ignored; it may be null.（如果是静态属性，参数会被忽略，传null即可）
 * 2、静态变量Unsafe通过cas原子性修改volatile变量的值。
 * 2.1、另一个代表偏移量的静态变量辅助实现。
 *
 *
 * @author liuling
 * @date 2022/5/20 23:56
 */
public class CasTest {
    // 1.1不能使用Unsafe.getUnsafe()
//    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final Unsafe unsafe;
    private static final long stateOffset;

    static {
        try {
            // 1.2使用反射获取Unsafe的实例
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            // 1.2.1因为是静态属性，参数传null即可
            unsafe = (Unsafe) unsafeField.get(null);
            stateOffset = unsafe.objectFieldOffset(CasTest.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    private volatile int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    public static void main(String[] args) {
        CasTest casTest = new CasTest();
//        casTest.setState(1);
        System.out.println(casTest.getState());
        System.out.println(casTest.compareAndSetState(2, 1));
        System.out.println(casTest.getState());
    }
}
