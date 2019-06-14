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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class MoFileController {
    public String maKT;public JTable table;

    public MoFileController(String maKT,JTable tb) {
        this.maKT = maKT;this.table=tb;
    }
    public void readExcel() throws FileNotFoundException, IOException{
            JFileChooser fc=new JFileChooser();
            fc.showSaveDialog(null);
            File f=fc.getSelectedFile();
            FileInputStream fr=new FileInputStream(f);
            Workbook workbook = getWorkbook(fr,f.getPath());
            List<Book> listBooks = MoFileController.readBooksFromExcelFile(f.getPath());
	    System.out.println(listBooks);
    }

    private Workbook getWorkbook(FileInputStream fr, String path) throws IOException {
        Workbook workbook = null;
	 
	    if (path.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(fr);
	    } else if (path.endsWith("xls")) {
	        workbook = new HSSFWorkbook(fr);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
    }
    
}

