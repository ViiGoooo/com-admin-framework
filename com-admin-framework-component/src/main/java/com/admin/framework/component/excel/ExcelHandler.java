package com.admin.framework.component.excel;

import com.admin.framework.component.utils.FileUtil;
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
public class ExcelHandler {

    /**
     * 读取excel
     * @param file
     * @return
     */
    public static Map<String,List<Map<String,Object>>> readExcel(File file){
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        Map<String,List<Map<String,Object>>> result = new HashMap();
        try {
            Workbook book = getWorkBook(file);
            return readExcel(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取excel
     * @param filePath
     * @return
     */
    public static Map<String,List<Map<String,Object>>> readExcel(String filePath){
        File file = FileUtil.getFile(filePath);
        return readExcel(file);
    }


    /**
     * 读取excel
     * @param book
     * @return
     */
    private static Map<String,List<Map<String,Object>>> readExcel(Workbook book){
        Map<String,List<Map<String,Object>>> result = new HashMap(16);
        int sheets = book.getNumberOfSheets();
        for(int x = 0 ; x < sheets ; x ++){
            Sheet sheet = book.getSheetAt(x);
            String sheetName = sheet.getSheetName();
            Row firstRow = sheet.getRow(0);
            int rowNumber = sheet.getLastRowNum();
            int columnNumber = firstRow.getPhysicalNumberOfCells();

            List<String> headers = new ArrayList<String>();
            for(int i = 0 ; i < columnNumber; i++){
                String header = firstRow.getCell(i).getStringCellValue();
                headers.add(header);
            }
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            for(int y = 1 ; y <= rowNumber ; y++){
                Row row = sheet.getRow(y);
                Map map = new HashMap();
                for (int z = 0;z < columnNumber;z++){
                    Cell cell = row.getCell(z);
                    String cellValue = getCellValue(cell);
                    String header = headers.get(z);
                    map.put(header,cellValue);
                }
                list.add(map);
            }
            result.put(sheetName,list);
        }
        return result;
    }




    public static Workbook getWorkBook(File file){
        Workbook book = null;
        try {
            InputStream ins = new FileInputStream(file);
            book = new HSSFWorkbook(ins);
        }catch (Exception e){
            try {
                InputStream ins = new FileInputStream(file);
                book = new XSSFWorkbook(ins);
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new RuntimeException("文件"+file.getName()+"无法读取");
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
    public static void export(List<ExcelEntity> list,File file) throws Exception{
        //创建对象
        Workbook book = getWorkBook(file);

        OutputStream out = new FileOutputStream(file);

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
        book.close();
    }

}
