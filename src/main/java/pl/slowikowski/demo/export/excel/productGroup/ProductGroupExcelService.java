package pl.slowikowski.demo.export.excel.productGroup;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static pl.slowikowski.demo.export.excel.abstraction.AbstractExcelService.createCellAndRow;

@Service
public class ProductGroupExcelService {

    public ByteArrayInputStream exportToExcel(List<ProductGroupDTO> products, String title) {
        String[] columns = {"ID", "Name", "Description", "Products"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper creationHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet(title);
            sheet.autoSizeColumn(columns.length);

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

            for (ProductGroupDTO group : products) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex++);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, group.getId(), cellStyle);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, group.getName(), cellStyle);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(row, columnIndex++, group.getDescription(), cellStyle);

                sheet.autoSizeColumn(columnIndex);
                createCellAndRow(
                        row,
                        columnIndex++,
                        group.getProducts()
                                .stream()
                                .map(ProductDTO::toString)
                                .collect(Collectors.toList())
                                .toString(),
                        cellStyle);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
