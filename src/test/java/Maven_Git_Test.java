import com.innovative.excelfilereader.MetaData;
import com.innovative.excelfilereader.worksheet;
import com.innovative.excelfilereader.ExcelInfo;
import com.innovative.excelfilereader.ExcelReader;
import com.innovative.excelfilereader.*;
import com.innovative.runexcelreader.Cli;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

public class Maven_Git_Test {


    @Test
    public void testNotNullMetaData() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            MetaData metaData = new MetaData(filePath);
            Assert.assertNotNull(metaData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSheetCount() throws Exception {
            try {
                String filePath = "TestExcel.xls";
                MetaData metaData = new MetaData(filePath);
                int sheetCount = metaData.getNoOfWorksheets();
                Assert.assertTrue(sheetCount == 3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Test
    public void testSheetNames() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            MetaData metaData = new MetaData(filePath);
            List<String> sheetNames = metaData.getSheetNames();
            Assert.assertTrue(sheetNames.contains("1. Orders"));
            Assert.assertTrue(sheetNames.contains("2. Returns"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWorkSheets() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            MetaData metaData = new MetaData(filePath);
            ArrayList<worksheet> sheets = metaData.getSheets();
            Assert.assertTrue(sheets.size() == 3);
            Assert.assertTrue(((worksheet) sheets.get(0)).getColoumnCounts() == 20);
            Assert.assertTrue(((worksheet) sheets.get(0)).getRowCounts() == 8400);
            Assert.assertTrue(((worksheet) sheets.get(1)).getColoumnCounts() == 2);
            Assert.assertTrue(((worksheet) sheets.get(1)).getRowCounts() == 573);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidExcelInfo() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            ExcelReader excelReader = new ExcelReader();
            ExcelInfo excelInfo = excelReader.readExcel(filePath, 1);
            Assert.assertNotNull(excelInfo);
            excelInfo = excelReader.readExcel(filePath, 2);
            Assert.assertNotNull(excelInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAttributesExcelInfo() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            ExcelReader excelReader = new ExcelReader();
            ExcelInfo excelInfo = excelReader.readExcel(filePath, 1);
            Assert.assertTrue(excelInfo.getNoofWorksheets() == 3);
            Assert.assertTrue(excelInfo.getRowCounts() == 8400);
            Assert.assertTrue(excelInfo.getColoumnCounts() == 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataTypesInExcelInfo() throws Exception {
        try {
            List<String> dataTypesToTest = Arrays.asList("int", "String");
            String filePath = "TestExcel.xls";
            ExcelReader excelReader = new ExcelReader();
            ExcelInfo excelInfo = excelReader.readExcel(filePath, 2);
            List<String> dataTypes = excelInfo.getColoumnDataTypes();
            for (String dataType : dataTypes) {
                Assert.assertTrue(dataTypesToTest.contains(dataType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testValidCli() {
        String filePath = "TestExcel.xls";
        String[] args = new String[]{"-f", filePath};
        Cli cli = new Cli(args);
        Assert.assertNotNull(cli);
    }

    @Test
    public void testMetadataDetails() throws Exception {
        try {
            String filePath = "C:\\Users\\Akhil Kumar\\Documents\\MavenProject\\Sample - Superstore Sales.xls";
            String[] args = new String[]{"-f", filePath};
            Cli cli = new Cli(args);
            cli.MetadataDetails(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetWorkSheet() throws Exception {
        try {
            String filePath = "TestExcel.xls";
            String[] args = new String[]{"-f", filePath};
            Cli cli = new Cli(args);
            cli.getWorksheet(filePath, 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotNullWorkSheet() {
        Sheet sampleSheet = this.getTestSheet();
        worksheet ws = new worksheet(sampleSheet);
        Assert.assertNotNull(ws);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidWorkSheet() {
        Sheet sampleSheet = null;
        new worksheet((Sheet)sampleSheet);
    }

    @Test
    public void testRowAndColumnCount() {
        Sheet sampleSheet = this.getTestSheet();
        worksheet ws = new worksheet(sampleSheet);
        Assert.assertTrue(ws.getRowCounts() == 2);
        Assert.assertTrue(ws.getColoumnCounts() == 3);
    }

    @Test
    public void testDataTypes() {
        Sheet sampleSheet = this.getTestSheet();
        worksheet ws = new worksheet(sampleSheet);
        List<String> dataTypesToTest = Arrays.asList("String", "int", "float", "boolean");
        List<String> dataTypes = ws.getColoumnDataTypes();
        Iterator var5 = dataTypes.iterator();

        while(var5.hasNext()) {
            String dataType = (String)var5.next();
            Assert.assertTrue(dataTypesToTest.contains(dataType));
        }

    }

    private Sheet getTestSheet() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(1.1F);
        row.createCell(1).setCellValue("A string");
        row.createCell(2).setCellValue(true);
        row = sheet.createRow(1);
        row.createCell(0).setCellValue(1.1F);
        row.createCell(1).setCellValue("A string");
        row.createCell(2).setCellValue(true);
        return sheet;
    }



}
