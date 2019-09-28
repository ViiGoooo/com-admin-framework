package com.admin.framework.component.excel;

import com.admin.framework.component.utils.FileUtil;
import com.admin.framework.component.utils.IOUtil;
import com.admin.framework.component.utils.MapUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ZSW
 * @date 2019/1/22
 */
public class ExcelHandler{

    /**
     * 读取excel
     * @param filePath
     * @return
     */
    public static Map<String,List<Map>> readExcel(String filePath) throws FileNotFoundException {
        File file = FileUtil.getFile(filePath);
        return readExcel(file);
    }

    /**
     * 读取excel
     * @param file
     * @return
     */
    public static Map<String,List<Map>> readExcel(File file) throws FileNotFoundException {
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        return readExcel(new FileInputStream(file));
    }

    /**
     * 读取excel
     * @param inputStream
     * @return
     */
    public static Map<String,List<Map>> readExcel(InputStream inputStream){
        Workbook book = getWorkBook(inputStream);
        return readExcel(book);
    }


    /**
     * 读取excel
     * @param book
     * @return
     */
    private static Map<String,List<Map>> readExcel(Workbook book){
        Map<String,List<Map>> result = new HashMap(16);
        int sheets = book.getNumberOfSheets();
        for(int x = 0 ; x < sheets ; x ++){
            Sheet sheet = book.getSheetAt(x);
            String sheetName = sheet.getSheetName();
            Row firstRow = sheet.getRow(0);
            if(firstRow == null){
                continue;
            }
            int rowNumber = sheet.getLastRowNum();
            int columnNumber = firstRow.getPhysicalNumberOfCells();

            List<String> headers = new ArrayList<String>();
            for(int i = 0 ; i < columnNumber; i++){
                Cell cell = firstRow.getCell(i);
                String header = getCellValue(cell);
                headers.add(header);
            }
            List<Map> list = new ArrayList<Map>();
            for(int y = 1 ; y <= rowNumber ; y++){
                Row row = sheet.getRow(y);
                Map map = new HashMap();
                for (int z = 0;z < columnNumber;z++){
                    Cell cell = row.getCell(z);
                    String cellValue = getCellValue(cell);
                    String header = headers.get(z);
                    map.put(header,cellValue);
                }
                boolean allEmpty = MapUtil.isAllEmpty(map);
                if(!allEmpty){
                    list.add(map);
                }
            }
            result.put(sheetName,list);
        }
        return result;
    }


    /**
     * 获取workbook
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Workbook getWorkBook(File file) throws FileNotFoundException {
        InputStream ins = new FileInputStream(file);
        return getWorkBook(ins);
    }

    /**
     * 获取workbook
     * @param inputStream
     * @return
     */
    public static Workbook getWorkBook(InputStream inputStream){
        Workbook book = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = IOUtil.cloneInputStream(inputStream);
            book = new HSSFWorkbook(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        }catch (Exception e){
            try {
                book = new XSSFWorkbook(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new RuntimeException("文件无法读取");
            }
        }
        return book;
    }



    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        CellType cellType = null;
        if(cell instanceof XSSFCell){
            XSSFCell c = (XSSFCell) cell;
            cellType = c.getCellType();
        }
        if(cell instanceof HSSFCell){
            HSSFCell c = (HSSFCell) cell;
            cellType = c.getCellType();
        }
        switch (cellType){
            case _NONE:
                cellValue = "";
                break;
            case NUMERIC:
                double numericCellValue = cell.getNumericCellValue();
                boolean integerForDouble = isIntegerForDouble(numericCellValue);
                if(integerForDouble){
                    long round = Math.round(numericCellValue);
                    cellValue = String.valueOf(round);
                }else{
                    cellValue = String.valueOf(numericCellValue);
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }

        return cellValue;

    }

    public static boolean isIntegerForDouble(double obj) {
        // 精度范围
        double eps = 1e-10;
        return obj-Math.floor(obj) < eps;
    }

    /**
     * 导出数据
     * */
    public static void export(List<ExcelEntity> list,OutputStream out) throws IOException {
        //创建对象
        Workbook book = new HSSFWorkbook();
        for(ExcelEntity excel:list){
            String sheetName = excel.getSheetName();
            List<List<String>> data = excel.getData();
            //创建sheet
            Sheet sheet = book.createSheet(sheetName);
            for(int x = 0 ; x < data.size();x++){
                List<String> columns = data.get(x);
                Row row = sheet.createRow(x);
                for(int y = 0 ; y < columns.size() ; y++){
                    Cell cell = row.createCell(y);
                    cell.setCellValue(columns.get(y));
                }
            }
        }
        book.write(out);
    }

    public static void main(String[] args) {
        try {
            Map<String, List<Map>> stringListMap = ExcelHandler.readExcel("C:\\Users\\Administrator\\Downloads\\apply (3).xlsx");
            int x  = 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
