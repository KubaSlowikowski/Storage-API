package pl.slowikowski.demo.export.excel.book;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.feign_client.dto.BookDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static pl.slowikowski.demo.export.excel.abstraction.AbstractExcelService.createCellAndRow;

@Service
public class BookExcelService {
    public ByteArrayInputStream exportToExcel(List<BookDTO> books, String title) {
        String[] columns = {"ID", "Author", "PublicationYear", "Title", "Reader"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet(title);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(headerFont);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Row for header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(cellStyle);
            }

            int rowIndex = 1;
            cellStyle = workbook.createCellStyle();
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("#"));
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (BookDTO book : books) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex++);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, book.getId(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, book.getAuthor(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, book.getPublicationYear(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, book.getTitle(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex, book.getReader(), cellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private void createCellAndRow(Row row, int columnIndex, String cellValue, CellStyle cellStyle) {
//        Cell cell = row.createCell(columnIndex);
//        cell.setCellValue(cellValue);
//        cell.setCellStyle(cellStyle);
//    }
}
