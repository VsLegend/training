package com.training.common.utils;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel 复制工具
 */
@Slf4j
public class ExcelCopyUtils {

  private static final String EXCEL_SUFFIX = ".xlsx";

  /**
   * poi导入excel工具类
   *
   * @param row 开始行
   * @param cell 列数
   */
  public static Map<Integer, List<Object>> importExcel(int row, MultipartFile file, int cell) {
    Map<Integer, List<Object>> content = new HashMap<>();
    InputStream stream = null;
    try {
      stream = file.getInputStream();
      Workbook wb = new XSSFWorkbook(stream);
      Sheet sheet = null;
      if (wb != null) {
        sheet = wb.getSheetAt(0);
      }
      List<Object> list = null;
      for (int i = row; i <= sheet.getLastRowNum(); i++) {
        Row rowLast = sheet.getRow(i);
        if (rowLast != null) {
          list = new ArrayList<>();
          int j = 0;
          while (j < cell) {
            Cell cellStr = rowLast.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cellStr != null) {
              cellStr.setCellType(CellType.STRING);
              list.add(cellStr.toString());
            } else {
              list.add("");
            }
            j++;
          }
          int num = 0;
          for (Object str : list) {
            if ("".equals(str.toString())) {
              num++;
            }
          }
          if (num < list.size()) {
            content.put(i, list);
          }
        }
      }
      wb.close();
    } catch (Exception e) {
      log.info("导入失败！原因：" + e.getMessage());
    } finally {
      IOCloseUtils.closeInStream(stream);
    }
    return content;
  }

  public static Map<Integer, List<String>> importExcelBak(int row, MultipartFile file,
      int cellLength) {
    Map<Integer, List<String>> content = new HashMap<>();
    InputStream stream = null;
    try {
      stream = file.getInputStream();
      Workbook wb = new XSSFWorkbook(stream);
      Sheet sheet = null;
      if (wb != null) {
        sheet = wb.getSheetAt(0);
      }
      List<String> list = null;
      for (int i = row; i <= sheet.getLastRowNum(); i++) {
        Row rowLast = sheet.getRow(i);
        if (rowLast != null) {
          list = new ArrayList<>();
          int j = 0;
          while (j < cellLength) {
            Cell cellStr = rowLast.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cellStr != null) {
              Cell cell = rowLast.getCell(j);
              cell.setCellType(CellType.STRING);
              list.add(cell.toString());
            } else {
              list.add("");
            }
            j++;
          }
          int num = 0;
          for (Object str : list) {
            if ("".equals(str.toString())) {
              num++;
            }
          }
          if (num < list.size()) {
            content.put(i, list);
          }
        }
      }
      wb.close();
    } catch (Exception e) {
      log.info("导入失败！原因：" + e.getMessage());
    } finally {
      IOCloseUtils.closeInStream(stream);
    }
    return content;
  }

  /**
   * 导出数据
   */
  public static void export(HttpServletResponse response, String name,
                            List<Object[]> dataList, String fileName, int startCellNum) {
    InputStream is = null;
    OutputStream out = null;
    XSSFWorkbook workbook = null;
    try {
      File file = ResourceUtils.getFile("classpath:excelTemplate/" + name + EXCEL_SUFFIX);
      is = new FileInputStream(file);
      workbook = new XSSFWorkbook(is);
      XSSFSheet sheet = workbook.getSheetAt(0);
      XSSFCellStyle style = getStyle(workbook);
      processData(dataList, sheet, startCellNum, style, response, fileName, out, workbook, "",
          false);
    } catch (Exception e) {
      log.info(e.getMessage());
    } finally {
      IOCloseUtils.closeWorkBook(workbook);
      IOCloseUtils.closeOutStream(out);
      IOCloseUtils.closeInStream(is);
    }
  }

  /**
   * 导出数据
   */
  public static void exportForTitle(HttpServletResponse response, String name,
                                    List<Object[]> dataList, String fileName, int startCellNum, String title, String fileUrl) {
    InputStream is = null;
    OutputStream out = null;
    XSSFWorkbook workbook = null;
    try {
      File file = ResourceUtils.getFile("classpath:excelTemplate/" + name + EXCEL_SUFFIX);
      is = new FileInputStream(file);
      workbook = new XSSFWorkbook(is);
      XSSFSheet sheet = workbook.getSheetAt(0);
      XSSFCellStyle style = getStyle(workbook);
      XSSFRow row = sheet.getRow(0);
      row.getCell(0).setCellValue(title);
      processData(dataList, sheet, startCellNum, style, response, fileName, out, workbook, fileUrl,
          true);
    } catch (Exception e) {
      log.info(e.getMessage());
    } finally {
      IOCloseUtils.closeWorkBook(workbook);
      IOCloseUtils.closeOutStream(out);
      IOCloseUtils.closeInStream(is);
    }
  }


  /**
   * 导出数据subsidy
   */
  public static void exportSubsidy(HttpServletResponse response, String name,
                                   List<Object[]> dataList, String fileName, int startCellNum,
                                   String date, boolean microFlag, String url) {
    InputStream is = null;
    OutputStream out = null;
    XSSFWorkbook workbook = null;
    try {
      File file = ResourceUtils.getFile("classpath:excelTemplate/" + name + EXCEL_SUFFIX);
      is = new FileInputStream(file);
      workbook = new XSSFWorkbook(is);
      XSSFSheet sheet = workbook.getSheetAt(0);
      XSSFCellStyle style = getStyle(workbook);
      if (!StringUtils.isEmpty(date)) {
        XSSFRow rowTwo = sheet.getRow(0);
        rowTwo.getCell(0).setCellValue(date);
      }
      //获取数据放入单元格
      processData(dataList, sheet, startCellNum, style, response, fileName, out, workbook, url,
          microFlag);
    } catch (Exception e) {
      log.info(e.getMessage());
    } finally {
      IOCloseUtils.closeWorkBook(workbook);
      IOCloseUtils.closeOutStream(out);
      IOCloseUtils.closeInStream(is);
    }
  }



  public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
    XSSFCellStyle style = workbook.createCellStyle();
    XSSFDataFormat format = workbook.createDataFormat();
    //设置字体
    XSSFFont redFont = workbook.createFont();
    redFont.setFontName("宋体");
    redFont.setFontHeightInPoints((short) 12);
    style.setFont(redFont);
    //设置单元格格式
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);
    style.setDataFormat(format.getFormat("@"));
    return style;
  }

  //从第row行的第column列开始获取cellLength个单元格(0开始)
  public static Map<Integer, List<String>> importIntegral(MultipartFile file, int row, int column,
                                                          int cellLength) {
    //返回值
    Map<Integer, List<String>> content = new HashMap<>();
    //输入流
    InputStream stream = null;
    //工作簿
    Workbook wb = null;
    //工作表
    Sheet sheet = null;
    //行
    List<String> lineList = null;

    try {
      stream = file.getInputStream();
      wb = new XSSFWorkbook(stream);
      if (wb != null) {
        sheet = wb.getSheetAt(0);
      }

      List<String> list = new ArrayList<>();
      list.add(String.valueOf(sheet.getLastRowNum() - 1));
      content.put(9999, list);

      //遍历每一行
      for (int i = row; i <= sheet.getLastRowNum(); i++) {

        int nullCount = 0;
        //当前行
        Row thisLine = sheet.getRow(i);
        if (thisLine != null) {
          lineList = new ArrayList<>();
          //从第几列开始读取0开始
          int flag = column;
          while (flag < cellLength) {
            Cell cell = thisLine.getCell(flag, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell != null) {
              cell.setCellType(Cell.CELL_TYPE_STRING);
              lineList.add(cell.toString());
            } else {
              lineList.add("");
              nullCount++;
            }
            flag++;
          }
          if (nullCount != cellLength) {
            content.put(i, lineList);
          }

        }
      }
      wb.close();
    } catch (Exception e) {
      log.info("导入失败！原因：" + e.getMessage());
    } finally {
      IOCloseUtils.closeInStream(stream);
    }
    return content;
  }

  private static void processData(List<Object[]> dataList, XSSFSheet sheet, int startCellNum,
                                  XSSFCellStyle style,
                                  HttpServletResponse response, String fileName, OutputStream out, XSSFWorkbook workbook,
                                  String dataPath, boolean microFlag)
      throws Exception {
    //获取数据放入单元格
    for (int i = 0; i < dataList.size(); i++) {
      Object[] obj = dataList.get(i);
      //创建所需的行数
      XSSFRow row = sheet.createRow(i + startCellNum);
      for (int j = 0; j < obj.length; j++) {
        //设置单元格的数据类型
        XSSFCell cell = null;
        cell = row.createCell(j, XSSFCell.CELL_TYPE_STRING);
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
        cell.setCellStyle(style);
      }
    }
    if (!microFlag) {
      response.setCharacterEncoding("utf-8");
      response.setHeader("Content-disposition",
          "attachment;filename=" + new String(fileName.getBytes(), "iso8859-1") + EXCEL_SUFFIX);
      out = response.getOutputStream();
      workbook.write(out);
    } else {
      File file = new File(Joiner.on("").join(dataPath, fileName, EXCEL_SUFFIX));
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      workbook.write(fileOutputStream);
    }
  }
}
