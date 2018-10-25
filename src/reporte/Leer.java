/*
 *  AUTOR: Ing. Flores Garia Diana Laura.
 *  Fecha de Inicio: 22 de Octubre de 2018.
 *  Última modificación: 22/10/2018.
 */
package reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.CellType;

public class Leer {
     public void readExcelFile(File excelFile){
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excelFile);
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;            
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue;  
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int r = 0; r < rows; r++) {
                hssfRow = hssfSheet.getRow(r);
                if (hssfRow == null){
                    break;
                }else{
                    System.out.print("Row: " + r + " -> ");
                    for (int c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                        cellValue = hssfRow.getCell(c) == null?"":
                                (hssfRow.getCell(c).getCellType() == CellType.STRING)?hssfRow.getCell(c).getStringCellValue():
                                (hssfRow.getCell(c).getCellType() == CellType.NUMERIC)?"" + hssfRow.getCell(c).getNumericCellValue():
                                (hssfRow.getCell(c).getCellType() == CellType.BOOLEAN)?"" + hssfRow.getCell(c).getBooleanCellValue():
                                (hssfRow.getCell(c).getCellType() == CellType.BLANK)?"BLANK":
                                (hssfRow.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":
                                (hssfRow.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";                       
                        System.out.print("[Column " + c + ": " + cellValue + "] ");
                    }
                    System.out.println();
                }
            }            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No se encontró el fichero: " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error al procesar el fichero: " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error al procesar el fichero después de cerrarlo: " + ex);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Leer leer = new Leer();
        leer.readExcelFile(new File("C:\\Users\\YOO\\Desktop\\Reporte\\workbook.xls"));
    }
}
