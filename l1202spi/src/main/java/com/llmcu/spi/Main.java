package com.llmcu.spi;

import java.util.ServiceLoader;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/16 21:07
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);
        load.forEach(animal -> System.out.println(animal.yell()));
    }
}
