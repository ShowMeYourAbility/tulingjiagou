package com.llmcu.l0101;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/30 0:38
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            while (true) {
                // 1、字符串个数
                int times = scn.nextInt();
                if (times < 1 || times > 50) {
                    break;
                }
                // 2、依次获取字符串
                List<String> allInput = new ArrayList<>();
                for (int i = 0; i < times; i++) {
                    String before = scn.next();
                    allInput.add(before);
                }
                // 3、处理
                for (String input : allInput) {
                    StringBuilder result = new StringBuilder();
                    InputBag inputBag = new InputBag(input);
                    while (inputBag.hasNext()) {
                        result.append(inputBag.next());
                    }
                    System.out.println(result);
                }
            }
        }
    }

}

class InputBag implements Iterator<String> {
    private int currentPos;
    private int lastEndPos;
    private int currentNormalEnd;
    private Character[] last3Char = new Character[3];
    private Character[] last4Char = new Character[4];
    private String input;


    public InputBag(String input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        int length = input.length();
        return currentPos < length;
    }

    @Override
    public String next() {
        boolean neverMeet = true;
        boolean notReallyEnd = false;
        for (; currentPos < input.length(); currentPos++) {
            last3Char[0] = last3Char[1];
            last3Char[1] = last3Char[2];
            last3Char[2] = input.charAt(currentPos);
            last4Char[0] = last4Char[1];
            last4Char[1] = last4Char[2];
            last4Char[2] = last4Char[3];
            last4Char[3] = input.charAt(currentPos);
            boolean threeLetterCondition = Objects.nonNull(last3Char[0]) && Objects.nonNull(last3Char[1]) && Objects.nonNull(last3Char[2]) && Objects.equals(last3Char[0], last3Char[1]) && Objects.equals(last3Char[1], last3Char[2]);
            boolean fourLetterCondition = Objects.nonNull(last4Char[0]) && Objects.nonNull(last4Char[1]) && Objects.nonNull(last4Char[2]) && Objects.nonNull(last4Char[3]) && Objects.equals(last4Char[0], last4Char[1]) && Objects.equals(last4Char[2], last4Char[3]);
            if (neverMeet && (threeLetterCondition || fourLetterCondition)) {
                neverMeet = false;
                currentNormalEnd = currentPos - 3;
                last3Char[2] = last3Char[1];
                last3Char[1] = last3Char[0];
                last3Char[0] = null;
                last4Char[3] = last4Char[2];
                last4Char[2] = last4Char[1];
                last4Char[1] = last4Char[0];
                last4Char[0] = null;
            } else if (!neverMeet && (threeLetterCondition || fourLetterCondition)) {
                last3Char[2] = last3Char[1];
                last3Char[1] = last3Char[0];
                last3Char[0] = null;
                last4Char[3] = last4Char[2];
                last4Char[2] = last4Char[1];
                last4Char[1] = last4Char[0];
                last4Char[0] = null;
            } else if (!neverMeet && !(threeLetterCondition || fourLetterCondition)) {
                last3Char[2] = last3Char[1];
                last3Char[1] = last3Char[0];
                last3Char[0] = null;
                last4Char[3] = last4Char[2];
                last4Char[2] = last4Char[1];
                last4Char[1] = last4Char[0];
                last4Char[0] = null;
//                currentPos--;
                notReallyEnd = true;
                break;
            }
        }
        if (neverMeet) {
            return input.substring(lastEndPos, input.length());
        }
        String normalPart = input.substring(lastEndPos, currentNormalEnd < lastEndPos ? lastEndPos : currentNormalEnd);
        StringBuilder builder = new StringBuilder();
        List<Character> collect = Stream.of(last4Char).filter(Objects::nonNull).collect(Collectors.toList());
        for (Character character : collect) {
            builder.append(character);
        }
        if (notReallyEnd) {
            lastEndPos = currentPos;
            last3Char[0] = null;
            last3Char[1] = null;
            last3Char[2] = null;
            last4Char[0] = null;
            last4Char[1] = null;
            last4Char[2] = null;
            last4Char[3] = null;
        }
        return normalPart + builder.toString();
    }
}