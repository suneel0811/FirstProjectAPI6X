package org.example.utills;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilExcel {
    //Open that FileInputStream
    //Understand the workbook
    //Sheet
    //Column
    //Cell
    //Close the Stream


    //This wil me use - Data Provider of the TestNG
    //Object[][]
    public static String File_Name="src/test/resources/Book1.xlsx";
    static Workbook workbook;
    static Sheet sheet;
    public static Object[][] getTestData(String sheetName){
        FileInputStream file=null;

        try {
            file=new FileInputStream(File_Name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            //create - it's a input stream
            workbook= WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet=workbook.getSheet(sheetName);
        Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i=0; i<sheet.getLastRowNum(); i++){
            for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++){
                    data[i][j]=sheet.getRow(i+1).getCell(j).toString();

            }
        }

        return data;
    }

    public static Object[][] getData(){
        //In future i can write the logic to select which sheet i want to open
        //Ask user which sheet to open
        //Dataprovider Sheet1,Sheet2
        //Sheet1 ->u/p -QA
        //Sheet2 ->u/p -Prod
        return  getTestData("Sheet1");
    }
}
