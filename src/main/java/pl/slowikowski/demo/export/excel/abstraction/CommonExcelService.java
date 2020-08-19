package pl.slowikowski.demo.export.excel.abstraction;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommonExcelService<D extends AbstractDto> {
    public ByteArrayInputStream exportToExcel(List<D> dtos, String title, List<String> fieldsToIgnore) {
        List<Field> fields = new ArrayList<>(List.of(AbstractDto.class.getDeclaredFields()));
        fields.addAll(List.of(dtos.get(0).getClass().getDeclaredFields()));

        if (fieldsToIgnore != null) {
            for (String ignore : fieldsToIgnore) {
                Set<Field> toRemove = fields.stream()
                        .filter(field -> field.getName().equals(ignore))
                        .collect(Collectors.toSet());
                if (!toRemove.isEmpty()) {
                    toRemove.forEach(fields::remove);
                }
            }
        }

        List<String> columns = fields.stream().map(Field::getName).collect(Collectors.toList());
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
            setBorder(cellStyle);
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Row for header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < columns.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns.get(col));
                cell.setCellStyle(cellStyle);
            }

            int rowIndex = 1;
            cellStyle = workbook.createCellStyle();
            setBorder(cellStyle);
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("#"));
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            for (D dto : dtos) {
                Row row = sheet.createRow(rowIndex++);
                int columnIndex = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    sheet.autoSizeColumn(columnIndex);
                    createCellAndRow(row, columnIndex++, String.valueOf(field.get(dto)), cellStyle);
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createCellAndRow(Row row, int columnIndex, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(columnIndex);
        if (cellValue.equals("null")) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(cellValue);
        }
        cell.setCellStyle(cellStyle);
    }

    private void setBorder(CellStyle cellStyle) {
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
    }
}
