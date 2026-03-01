
package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;

public class ExcelUtil {

    private static Workbook workbook;
    public static Sheet sheet;

    public static void loadExcel(String fileName, String sheetName) {
        try {
            InputStream file = ExcelUtil.class.getClassLoader()
                    .getResourceAsStream("testData/" + fileName);

            if (file == null) {
                throw new RuntimeException("❌ Excel file not found in resources/testData/");
            }

            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("❌ Sheet '" + sheetName + "' not found in Excel file");
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load Excel: " + e.getMessage());
        }
    }

    public static String getCellData(int rowNum, int colNum) {
        return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
    }

    public static int getRowNumberByType(String type) {
        for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {
            String cellValue = getCellData(r, 0); // column 0 = Type
            if (cellValue.equalsIgnoreCase(type)) {
                return r;
            }
        }
        throw new RuntimeException("No row found for type: " + type);
    }

}
