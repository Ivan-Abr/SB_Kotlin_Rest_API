package com.example.webApp.excel

import com.example.webApp.entity.Factor
import jakarta.servlet.http.HttpServletResponse
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFFont
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.IOException


class ExcelExporter(private var listobjects: List<Factor>)
{
    private var workbook: XSSFWorkbook = XSSFWorkbook()

    private  var sheet: XSSFSheet = workbook.createSheet()
    fun ExcelExporter(listobjects: List<Factor>) {
        this.listobjects = listobjects
        workbook = XSSFWorkbook()
    }
    private fun writeHeaderLine(){
        sheet = workbook.createSheet("Factors")

        var row:Row = sheet.createRow(0)
        var style: CellStyle = workbook.createCellStyle()
        var font: XSSFFont = workbook.createFont()
        font.bold = true
        font.setFontHeight(16)
        style.setFont(font)

        createCell(row,0,"name",style)
        createCell(row, 1, "shortname", style)
        //createCell(row,2,"Компонент",style)
    }



    private fun createCell(
        row: Row,
        columnCount: Int,
        value: Any,
        style: CellStyle
    ) {
        sheet.autoSizeColumn(columnCount)
        val cell = row.createCell(columnCount)
        if (value is Int) {
            cell.setCellValue(value.toDouble())
        } else if (value is Boolean) {
            cell.setCellValue(value)
        } else {
            cell.setCellValue(value as String)
        }
        cell.cellStyle = style
    }

    private fun writeDataLines(){
        var rowCount: Int = 1
        var style: CellStyle = workbook.createCellStyle()
        var font: XSSFFont = workbook.createFont()
        font.setFontHeight(14)
        style.setFont(font)

        for (obj:Factor in listobjects){
            var row = sheet.createRow(rowCount++)
            var columnCount = 0

            createCell(row, columnCount++, obj.factorName, style)
            createCell(row, columnCount++, obj.factorShortName, style)
        }
    }

    @Throws(IOException::class)
    fun export(response: HttpServletResponse) {
        writeHeaderLine()
        writeDataLines()
        val outputStream = response.outputStream
        workbook.write(outputStream)
        workbook.close()
        outputStream.close()
    }
//    public fun export(response: HttpServletResponse)throws IOException{
//
//    }

}