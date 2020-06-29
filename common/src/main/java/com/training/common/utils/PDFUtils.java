package com.training.common.utils;

import com.spire.pdf.PdfDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @User: wong
 * @Date: 2020/6/23
 * @Description: PDF工具类
 */
@Slf4j
public class PDFUtils {


  public static List<File> pdf2Jpg(String source) throws IOException {
    return null;
  }

  /**
   * pdf转jpg
   */
  public static List<File> pdf2Jpg(String path, String target) throws IOException {
    log.info("");
    File pdf = FileUtils.createFileNotExists(path);
    String pdfName = FilenameUtils.getBaseName(pdf.getName());
    File destDir = pdf.getParentFile().getAbsoluteFile();
    Document document = new Document();
    String filePath = pdf.getAbsolutePath();
    // 缩放比例
    float scale = 1f;
    // 旋转角度
    float rotation = 0f;
    List<File> resultFile = new ArrayList<>();
    try {
      document.setFile(filePath);
      for (int i = 0; i < document.getNumberOfPages(); i++) {
        BufferedImage image =
                (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.PRINT,
                        org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
        RenderedImage rendImage = image;
        String imgName = pdfName + "_" + StringUtils.leftPad(String.valueOf(i + 1), 3, "0");
        File file = new File(destDir.getAbsoluteFile() + File.separator + imgName + ".jpg");
        ImageIO.write(rendImage, "png", file);
        image.flush();
        resultFile.add(file);
      }
      return resultFile;
    } catch (Exception e) {
      log.warn("第一次pdf转换图片失败", e);
    } finally {
      document.dispose();
    }
    PdfDocument doc = new PdfDocument();
    doc.loadFromFile(filePath);
    try {
      for (int i = 0; i < doc.getPages().getCount(); i++) {
        BufferedImage image = doc.saveAsImage(i);
        String imgName = pdfName + "_" + StringUtils.leftPad(String.valueOf(i), 3, "0");
        File file = new File(destDir.getAbsoluteFile() + File.separator + imgName + ".jpg");
        ImageIO.write(image, "png", file);
        image.flush();
        resultFile.add(file);
      }
    } finally {
      doc.close();
    }
    return resultFile;
  }

}

