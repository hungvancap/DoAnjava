/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.awt.List;
import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class MoFileController {
    public String maKT;public JTable table;
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_CAU1 = 1;
    public static final int COLUMN_INDEX_CAU2 = 2;
    public static final int COLUMN_INDEX_CAU3 = 3;
    public static final int COLUMN_INDEX_TONG = 4;
    public MoFileController(String maKT,JTable tb) {
        this.maKT = maKT;this.table=tb;
    }
    public void readExcel() throws FileNotFoundException, IOException{
            JFileChooser fc=new JFileChooser();
            fc.showSaveDialog(null);
            File f=fc.getSelectedFile();
            FileInputStream fr=new FileInputStream(f);
            Workbook workbook = new XSSFWorkbook(fr);
            Sheet sheet=workbook.getSheetAt(0);
	    Iterator<Row> iterator = sheet.iterator();
            String []title={"MSSV","Cau1","Cau2","Cau3","Tong"};
            DefaultTableModel model=new DefaultTableModel(title,0);
            while (iterator.hasNext()) {
                Vector v=new Vector();
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
                
        }
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) { 
                    Vector vt=new Vector();
                    Cell cell=cellIterator.next();
                    Object cellValue=getCellValue(cell);
                    
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                 // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_INDEX_ID:  
                    vt.add(getCellValue(cell));break;
                case COLUMN_INDEX_CAU1:
                    vt.add(getCellValue(cell));break;
                case COLUMN_INDEX_CAU2:
                    vt.add(getCellValue(cell));break;
                case COLUMN_INDEX_CAU3:
                    vt.add(getCellValue(cell));break;
                case COLUMN_INDEX_TONG:
                    vt.add(getCellValue(cell));break;
                    default:
                    break;
                 }
                    if (vt.size()==2) {
                        int i=0;
                        v.add(vt.get(1));
                        v.add(i);
                        v.add(i);
                        v.add(i);
                        v.add(vt.get(2));
                        System.out.println(vt.get(0));
                    }
                    else{
                        v.add(vt);
                    }
                }
               model.addRow(v);
                
            }
            table.setModel(model);
        }

    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
    
}

