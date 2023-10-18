package com.llmcu.readword;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetection;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/9/7 11:25
 */
public class Main {


    public static String readDoc(String path) throws IOException {
        String resullt = "";
        //首先判断文件中的是doc/docx
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor re = new WordExtractor(is);
                resullt = re.getText();
                re.close();
            } else if (path.endsWith(".docx")) {
                InputStream is = new FileInputStream(path);
                XWPFDocument doc = new XWPFDocument(is);
                List<XWPFParagraph> paras = doc.getParagraphs();
                System.out.println("paras.size():"+paras.size());


                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                resullt = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return resullt;
    }

    public static String readPDF(String file) throws IOException, TencentCloudSDKException {
        StringBuilder result = new StringBuilder();
        FileInputStream is = null;
        PDDocument document = PDDocument.load(new File(file));
        PDFRenderer renderer = new PDFRenderer(document);

        is = new FileInputStream(file);
        PDFParser parser = new PDFParser(new RandomAccessBuffer(is));
        parser.parse();
        PDDocument doc = parser.getPDDocument();
        PDFTextStripper textStripper =new PDFTextStripper();
        for(int i = 1; i<=(Math.min(doc.getNumberOfPages(), 3)); i++)
        {
            textStripper.setStartPage(i);
            textStripper.setEndPage(i);
            textStripper.setSortByPosition(true);//按顺序行读
            String s=textStripper.getText(doc);
            
            

            System.out.println("==============第"+i+"页数据开始==============");
            System.out.println(s);
            if(StringUtils.isEmpty(s)|| Objects.equals(s,"\r\n")){
                Credential cred = new Credential("AKIDEJAl7CWbS3OTJuFLBl5AzYsvjHacOuA9", "lGWXIn1zYswkHYYIm0ziCSSCgvue8MK2");
                OcrClient client = new OcrClient(cred,"ap-shanghai");
                PDPage currentPage = textStripper.getCurrentPage();
                InputStream contents = currentPage.getContents();

                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                UUID uuid = UUID.randomUUID();
                File currentImage = new File("E:\\test\\"+uuid+".png");
                currentImage.createNewFile();
                ImageIO.write(image,"png",currentImage);
                String ImageBase64 = encodeBase64ByFile(currentImage);
                GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
//                req.setIsPdf(true);
                req.setImageBase64(ImageBase64);
                GeneralBasicOCRResponse generalBasicOCRResponse = client.GeneralBasicOCR(req);
                TextDetection[] textDetections = generalBasicOCRResponse.getTextDetections();
                for (TextDetection textDetection : textDetections) {
                    System.out.println(textDetection.getDetectedText());
                }
                System.out.println(generalBasicOCRResponse);
            }
            System.out.println("==============第"+i+"页数据结束==============");
            result.append(s);
        }
//        //文本为空，读图片提取图片里的文字，
//        if(result.toString().trim().length()==0){
//            for(int i=1;i<doc.getNumberOfPages();i++){
//                PDPage page=doc.getPage(i-1);
//                PDResources resources = page.getResources();
//                Iterable<COSName> xobjects =resources.getXObjectNames();
//                if(xobjects!=null) {
//                    Iterator<COSName> imageIter = xobjects.iterator();
//                    while (imageIter.hasNext()) {
//                        COSName cosName = imageIter.next();
//                        boolean isImageXObject = resources.isImageXObject(cosName);
//                        if (isImageXObject) {
//                            //获取每页资源的图片
//                            PDImageXObject ixt = (PDImageXObject) resources.getXObject(cosName);
//                            File outputfile = new File("D:\\tmp\\" + cosName.getName() + ".jpg");
//                            ImageIO.write(ixt.getImage(), "jpg", outputfile);//可保存图片到本地
//							 //调用图片识别文字接口
//							//...
//                        }
//                    }
//                }
//            }
//        }
        doc.close();
        return result.toString();
    }

    public static String encodeBase64ByFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputStream.read(buffer);
        inputStream.close();
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(buffer);
    }

    //main方法测试
    public static void main(String[] args) {

        String filename="E:\\test\\test.docx";
//        String filename2="E:\\test\\简历-刘凌-java开发-全职.pdf";
        String filename2="E:\\test\\小学数学苏教版六年级上册_加水印.pdf";
        String textContent = null;
        try {
            textContent = readDoc(filename);
            System.out.println(readPDF(filename2));
        } catch (IOException | TencentCloudSDKException e) {
            e.printStackTrace();
        }
        System.out.println(textContent);

    }
}
