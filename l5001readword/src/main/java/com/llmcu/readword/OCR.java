package com.llmcu.readword;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/9/7 15:52
 */
public class OCR {
    public static void main(String[] args) throws TencentCloudSDKException, IOException {
        Credential cred = new Credential("AKIDEJAl7CWbS3OTJuFLBl5AzYsvjHacOuA9", "lGWXIn1zYswkHYYIm0ziCSSCgvue8MK2");
        OcrClient client = new OcrClient(cred,"ap-shanghai");
        GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
        req.setIsPdf(true);
        String fileName="E:\\test\\小学数学苏教版六年级上册_加水印.pdf";
        String ImageBase64 = encodeBase64ByFile(new File(fileName));
        req.setImageBase64(ImageBase64);
        GeneralBasicOCRResponse generalBasicOCRResponse = client.GeneralBasicOCR(req);
        TextDetection[] textDetections = generalBasicOCRResponse.getTextDetections();
        for (TextDetection textDetection : textDetections) {
            System.out.println(textDetection.getDetectedText());
        }

        System.out.println(generalBasicOCRResponse);
    }
    public static String encodeBase64ByFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputStream.read(buffer);
        inputStream.close();
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(buffer);
    }
}
