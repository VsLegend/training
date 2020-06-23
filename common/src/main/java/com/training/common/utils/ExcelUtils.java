package com.training.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @User: wong
 * @Date: 2020/6/15
 * @Description: Excel工具类
 */
@Slf4j
public class ExcelUtils {

  private static final String EXCEL_SUFFIX = ".xlsx";

  /**
   * 导出 保存文件
   */
  public static void outPutExcel(Workbook workbook, String fileName) {
    try {
      FileOutputStream fos = new FileOutputStream(fileName);
      workbook.write(fos);
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
      log.info("保存文件失败");
    }
  }

  /**
   * 导出 输出流
   */
  public static void outPutStream(HttpServletResponse response, Workbook workbook, String path) {
    OutputStream out = null;
    try {
      response.setCharacterEncoding("utf-8");
      response.setHeader("Content-disposition",
              "attachment;filename=" + new String(path.getBytes(), "iso8859-1"));
      out = response.getOutputStream();
      workbook.write(out);
    } catch (Exception e) {
      e.printStackTrace();
      log.info("输出流失败");
    } finally {
      IOCloseUtils.closeWorkBook(workbook);
      IOCloseUtils.closeOutStream(out);
    }
  }


  /**
   * 处理数据
   */
  public static void processListData(List<String> dataList, Workbook workbook, CellStyle cellStyle, String sheetName, int startCellNum) {
    Sheet sheet = workbook.getSheet(sheetName);
    //获取数据放入单元格
    for (int i = 0; i < dataList.size(); i++) {
      String data = dataList.get(i);
      //创建所需的行数
      Row row = sheet.createRow(i + startCellNum);
      //设置单元格的数据类型
      Cell cell = row.createCell(0, CellType.STRING);
      cell.setCellValue(data);
      cell.setCellStyle(cellStyle);
    }
    // 自适应列宽
    sheet.autoSizeColumn(0);
  }

  /**
   * 处理数据
   */
  public static void processData(List<Object[]> dataList, Workbook workbook, CellStyle cellStyle, String sheetName, int startCellNum)
          throws Exception {
    Sheet sheet = workbook.getSheet(sheetName);
    //获取数据放入单元格
    for (int i = 0; i < dataList.size(); i++) {
      Object[] obj = dataList.get(i);
      //创建所需的行数
      Row row = sheet.createRow(i + startCellNum);
      for (int j = 0; j < obj.length; j++) {
        //设置单元格的数据类型
        Cell cell = null;
        cell = row.createCell(j, CellType.STRING);
        if (!"".equals(obj[j]) && obj[j] != null) {
          if (obj[j] instanceof String) {
            cell.setCellValue((String) obj[j]);
          } else if (obj[j] instanceof Integer) {
            cell.setCellValue((Integer) obj[j]);
          } else if (obj[j] instanceof Long) {
            cell.setCellValue((Long) obj[j]);
          } else if (obj[j] instanceof Short) {
            cell.setCellValue((short) obj[j]);
          } else if (obj[j] instanceof Double) {
            cell.setCellValue((Double) obj[j]);
          } else if (obj[j] instanceof Float) {
            cell.setCellValue((Float) obj[j]);
          } else if (obj[j] instanceof BigDecimal) {
            double doubleVal = ((BigDecimal) obj[j]).doubleValue();
            cell.setCellValue(doubleVal);
          }
        } else {
          cell.setCellValue("");
        }
        cell.setCellStyle(cellStyle);
      }
    }
  }

  /**
   * 设置格式
   */
  public static CellStyle getStyle(Workbook workbook) {
    CellStyle style = workbook.createCellStyle();
    DataFormat format = workbook.createDataFormat();
    //设置字体
    Font redFont = workbook.createFont();
    redFont.setFontName("宋体");
    redFont.setFontHeightInPoints((short) 12);
    style.setFont(redFont);
    //设置单元格格式
    style.setAlignment(HorizontalAlignment.LEFT);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setDataFormat(format.getFormat("@"));
    return style;
  }

}
