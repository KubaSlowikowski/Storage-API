package pl.slowikowski.demo.export.excel.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static pl.slowikowski.demo.export.excel.abstraction.AbstractExcelService.createCellAndRow;

@Service
public class ReaderExcelService {
    public ByteArrayInputStream exportToExcel(List<ReaderDTO> readers, String title) {
        String[] columns = {"ID", "FirstName", "LastName", "Email", "JoinDate", "Role", "Username"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet(title);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(headerFont);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);

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

            for (ReaderDTO reader : readers) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex++);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getId(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getFirstName(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getLastName(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getEmail(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getJoinDate(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, reader.getRole(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex, reader.getUsername(), cellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
