package pl.slowikowski.demo.export.excel.product;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ProductExcelService {

    public ByteArrayInputStream exportToExcel(List<ProductDTO> products, String title) {
        String[] columns = {"ID", "Name", "Description", "Price", "Sold", "Group ID"};
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

            for (ProductDTO product : products) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex++);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, Long.toString(product.getId()), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, product.getName(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, product.getDescription(), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, Integer.toString(product.getPrice()), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, Boolean.toString(product.isSold()), cellStyle);
                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex, Long.toString(product.getGroupId()), cellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createCellAndRow(Row row, int columnIndex, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }
}
