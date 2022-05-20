package cn.com.snails.core.utils;

import cn.hutool.core.map.MapUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author Wang Junwei
 * @Date 2022/5/18 13:16
 * @Description poi excel 导出
 */
public class ExcelUtils {


    /**
     * 默认表格内容格式
     *
     * @param workbook
     * @return
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
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setDataFormat(format.getFormat("@"));
        return style;
    }

    /**
     * 默认表头格式
     *
     * @param workbook
     * @return
     */
    public static CellStyle getHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        //设置字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        // 设置边框
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        //设置单元格格式
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setDataFormat(format.getFormat("@"));
        return style;
    }


    /**
     * excel导出处理类
     *
     * @param file
     */
    public static Excel getInstance(File file) {
        return new Excel(new XSSFWorkbook(), file);
    }

    public static Excel getInstance(Workbook workbook, File file) {
        return new Excel(workbook, file);
    }

    /**
     * Excel导出工具类
     */
    public static class Excel {

        private final ExcelBuilder builder;

        public Excel(Workbook workbook, File file) {
            if (workbook == null) {
                throw new NullPointerException("ExcelUtils create Excel Exception：workbook is null");
            }
            this.builder = ExcelBuilder.builder().build();
            this.builder.setWorkbook(workbook);
            this.builder.setFile(file);
            this.builder.setSheetContent(new SheetContent());
            this.builder.setSheetCache(MapUtil.newConcurrentHashMap());
        }

        /**
         * 参数构建
         */
        public Excel cellStyle(CellStyle cellStyle) {
            this.builder.setCellStyle(cellStyle);
            return this;
        }

        public Excel headerCellStyle(CellStyle cellStyle) {
            this.builder.setHeaderCellStyle(cellStyle);
            return this;
        }

        public Excel headers(Object[] headers) {
            this.builder.setHeader(headers);
            this.builder.writeHeader();
            return this;
        }

        public Excel headers(Class<?> headers) {
            this.builder.setHeader(headers);
            this.builder.writeHeader();
            return this;
        }

        public Excel sheet(String sheetName) {
            this.builder.setSheetName(sheetName);
            return this;
        }

        /**
         * 使用
         */
        public Excel write(List<Object[]> data) {
            this.builder.write(data);
            return this;
        }

        public Excel writeObject(List<?> data) {
            this.builder.writeClassList(data);
            return this;
        }

        public void finish() {
            this.builder.finish();
        }

    }

    @Data
    @Builder
    private static class ExcelBuilder {
        private Workbook workbook;
        private File file;
        /**
         * sheet参数和缓存
         */
        private SheetContent sheetContent;
        private Map<String, SheetContent> sheetCache;

        private Object[] headers;

        /**
         * 识别的字段内容
         */
        private FiledContent filedContent;

        /**
         * 创建或选择sheet
         *
         * @param sheetName
         */
        public void setSheetName(String sheetName) {
            if (null != sheetCache.get(sheetName)) {
                sheetContent = sheetCache.get(sheetName);
                return;
            }
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheetContent.setCellIndex(0);
                sheetContent.setSheet(workbook.createSheet(sheetName));
                sheetContent.setHeaderInsert(false);
            } else {
                int lastRowNum = sheet.getLastRowNum();
                sheetContent.setCellIndex(lastRowNum);
                sheetContent.setSheet(sheet);
                sheetContent.setHeaderInsert(lastRowNum > 0);
            }
            sheetCache.put(sheetName, sheetContent);
        }


        /**
         * 设置表格格式
         *
         * @param cellStyle
         */
        public void setCellStyle(CellStyle cellStyle) {
            checkSheet();
            sheetContent.setCellStyle(cellStyle);
        }

        public void setHeaderCellStyle(CellStyle cellStyle) {
            checkSheet();
            sheetContent.setHeaderCellStyle(cellStyle);
        }

        /**
         * 设置表头
         *
         * @param headers
         */
        public void setHeader(Object[] headers) {
            checkSheet();
            sheetContent.setHeaders(headers);
        }

        public void setHeader(Class<?> clazz) {
            checkSheet();
            FiledContent filedContent = ClassBuilderUtils.parseDeclaredFile(clazz);
            this.filedContent = filedContent;
            sheetContent.setHeaders(filedContent.getHeader());
        }

        /**
         * 输出表头到表格
         */
        public void writeHeader() {
            if (!sheetContent.isHeaderInsert()) {
                writeRow(Collections.singletonList(sheetContent.getHeaders()), sheetContent.getHeaderCellStyle(workbook));
                sheetContent.setHeaderInsert(true);
                autoSizeColumn(sheetContent.getHeaders().length);
            }
        }

        /**
         * 输出内容到表格
         *
         * @param data
         */
        public void write(List<Object[]> data) {
            if (CollectionUtils.isEmpty(data)) {
                return;
            }
            writeRow(data, sheetContent.getCellStyle());
        }

        private void checkSheet() {
            if (null == sheetContent || null == sheetContent.getSheet()) {
                throw new NullPointerException("ExcelUtils set sheet header failed ：sheet is null");
            }
        }

        /**
         * 解析类对象
         *
         * @param data
         */
        public void writeClassList(List<?> data) {
            List<Object[]> rows = new ArrayList<>();
            if (CollectionUtils.isEmpty(data)) {
                return;
            }
            // 表头
            Class<?> aClass = data.get(0).getClass();
            if (aClass != filedContent.getClazz()) {
                throw new IllegalArgumentException("ExcelUtils write Class Exception：The parameter class-" +
                        aClass + " is different from the header class-" + filedContent.getClazz());
            }
            final Map<String, FiledCache> filedCacheMap = filedContent.getFiledCacheMap();
            final List<String> filedOder = filedContent.getFiledOder();
            for (Object obj : data) {
                try {
                    Object[] row = new Object[filedOder.size()];
                    for (int i = 0; i < filedOder.size(); i++) {
                        Field field = filedCacheMap.get(filedOder.get(i)).getField();
                        field.setAccessible(true);
                        row[i] = field.get(obj);
                    }
                    rows.add(row);
                } catch (IllegalAccessException ignored) {
                }
            }
            write(rows);
        }

        /**
         * 输出流到文件
         */
        public void finish() {
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            } catch (IOException e) {
                throw new RuntimeException("ExcelUtils output stream Exception：" + e.getMessage());
            } finally {
                clear();
            }
        }

        /**
         * 表头列宽自适应
         *
         * @param length
         */
        private void autoSizeColumn(int length) {
            for (int i = 0; i < length; i++) {
                // 表头
                sheetContent.getSheet().autoSizeColumn(i);
                // 解决部分字段比表头长
//                sheetContent.getSheet().setColumnWidth(i,sheetContent.getSheet().getColumnWidth(i) * 17/10);
            }
        }

        private void clear() {
            workbook = null;
            file = null;
            sheetContent = null;
            sheetCache.clear();
            headers = null;
        }

        /**
         * 添加行数据
         *
         * @param data
         */
        private void writeRow(List<Object[]> data, CellStyle cellStyle) {
            for (Object[] obj : data) {
                if (obj == null || obj.length == 0) {
                    return;
                }
                //创建所需的行数
                Row row = sheetContent.getSheet().createRow(sheetContent.cellIndex++);
                for (int j = 0; j < obj.length; j++) {
                    //设置单元格的数据类型
                    Cell cell = setCell(row, obj, j);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        /**
         * 设置值
         *
         * @param row
         * @param obj
         * @param j
         * @return
         */
        private Cell setCell(Row row, Object[] obj, int j) {
            Cell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
            Object value = obj[j];
            if (!"".equals(value) && value != null) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                } else if (value instanceof Short) {
                    cell.setCellValue((short) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Float) {
                    cell.setCellValue((Float) value);
                } else if (value instanceof BigDecimal) {
                    double doubleVal = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleVal);
                } else if (value instanceof Date) {
                    // 可以加上时间格式化功能
                    cell.setCellValue((Date) value);
                }
            } else {
                cell.setCellValue("");
            }
            return cell;
        }
    }

    static class ClassBuilderUtils {
        /**
         * 解析表头和表头顺序
         *
         * @param clazz
         */
        public static FiledContent parseDeclaredFile(Class<?> clazz) {
            List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
            Map<String, FiledCache> filedCacheMap = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                ExcelHeader excelHeader = field.getDeclaredAnnotation(ExcelHeader.class);
                if (excelHeader != null) {
                    FiledCache filedCache = FiledCache.builder().field(field).oder(excelHeader.order()).cellName(excelHeader.value()).build();
                    filedCacheMap.put(field.getName(), filedCache);
                }
            }
            if (CollectionUtils.isEmpty(filedCacheMap)) {
                throw new IllegalArgumentException("ExcelUtils create Excel Exception：No available headers identified in class-" + clazz.getName());
            }
            Object[] objects = new Object[filedCacheMap.size()];
            int[] i = {0};
            List<String> filedOder = filedCacheMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(k -> k.getValue().getOder()))
                    .peek(k -> objects[i[0]++] = k.getValue().cellName)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            return FiledContent.builder().clazz(clazz).filedCacheMap(filedCacheMap).filedOder(filedOder).header(objects).build();
        }
    }


    /**
     * 表格相关数据
     */
    @Data
    private static class SheetContent {
        private boolean headerInsert;
        private Sheet sheet;
        private CellStyle cellStyle;
        private CellStyle headerCellStyle;
        private int cellIndex;
        private Object[] headers;

        public CellStyle getCellStyle(Workbook workbook) {
            return cellStyle == null ? getStyle(workbook) : cellStyle;
        }

        public CellStyle getHeaderCellStyle(Workbook workbook) {
            return headerCellStyle == null ? getHeaderStyle(workbook) : headerCellStyle;
        }

    }

    /**
     * 解析类的字段数据
     */
    @Data
    @Builder
    private static class FiledContent {
        private Class<?> clazz;
        private Object[] header;
        private Map<String, FiledCache> filedCacheMap;
        private List<String> filedOder;
    }

    @Data
    @Builder
    private static class FiledCache {
        private Field field;
        private int oder;
        private String cellName;
    }

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface ExcelHeader {
        /**
         * 表名
         *
         * @return
         */
        String value() default "";

        int order() default Integer.MAX_VALUE;
    }

}
