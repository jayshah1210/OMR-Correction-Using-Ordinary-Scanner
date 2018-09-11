
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dash
 */
public class ExcelReader {
    
           static int p=0,f=0;

    public static String mainData(String roll) throws FileNotFoundException, IOException
    {
        FileInputStream fis= new FileInputStream(new File("C:\\Users\\Dash\\Documents\\BE project\\db.xls"));
        
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        
        HSSFSheet sheet= wb.getSheetAt(0);
        
        FormulaEvaluator forlulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        
        String data = "";
        for(Row row:sheet) {
            for (Cell cell : row)
            {
                switch(forlulaEvaluator.evaluateInCell(cell).getCellType())
                 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                        
                }             
        }
            
            
            
            System.out.println(roll);
    }
        for(Row row:sheet) {
            
            Cell cell1 = row.getCell(0);
            if(cell1.getNumericCellValue() == Double.parseDouble(roll))
            {

            for (Cell cell : row)
            {
                {
                    switch(forlulaEvaluator.evaluateInCell(cell).getCellType())

                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            data += BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString()+"         ";
                            break;
                        case Cell.CELL_TYPE_STRING:
                            data += cell.getStringCellValue()+"          ";
                            break;

                    }     
                }
                
            }
            }

    }
return data;
}
    public static String mainDataDetail() throws FileNotFoundException, IOException
    {
        FileInputStream fis= new FileInputStream(new File("C:\\Users\\Dash\\Documents\\BE project\\db.xls"));
        
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        
        HSSFSheet sheet= wb.getSheetAt(0);
        
        FormulaEvaluator forlulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        
        String data = "<html>";
        for(Row row:sheet) {
            Cell[] cells = {row.getCell(0), row.getCell(5)};
            for (Cell cell : cells)
            {
                switch(forlulaEvaluator.evaluateInCell(cell).getCellType())
                 
                {
                    case Cell.CELL_TYPE_NUMERIC:
                            data += BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         ";
                        break;
                    case Cell.CELL_TYPE_STRING:
                        data += cell.getStringCellValue() + "&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;";
                        if(cell.getStringCellValue().equalsIgnoreCase("Pass"))
                        p++;
                        else
                            f++;
                        break;
                        
                }             
        }
            
            
            
            data += "<br>";
    }
        data += "</html>";
    return data;
}
    static int[] pf ()
    {
        int [] x = {p,f};
        return x;
    }

  
}