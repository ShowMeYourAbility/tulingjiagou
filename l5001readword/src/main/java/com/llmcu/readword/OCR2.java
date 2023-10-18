package com.llmcu.readword;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/9/7 15:52
 */
public class OCR2 {
    public static void main(String[] args) throws TesseractException {
        String fileName = "E:\\test\\小学数学苏教版六年级上册_加水印.pdf";
        File file = new File(fileName);
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:\\shuangdi\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
//        instance.setLanguage("zho");
        instance.setLanguage("chi_sim");
        String result = instance.doOCR(file);


        System.out.println(result);
    }
}
