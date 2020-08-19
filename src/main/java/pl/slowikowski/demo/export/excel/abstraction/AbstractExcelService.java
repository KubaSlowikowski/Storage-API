package pl.slowikowski.demo.export.excel.abstraction;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;

import java.time.LocalDate;

public abstract class AbstractExcelService {
    public static void createCellAndRow(Row row, int columnIndex, Object cellValue, CellStyle cellStyle) {
        if (cellValue instanceof Long) {
            cellValue = Long.toString((Long) cellValue);
        } else if (cellValue instanceof Integer) {
            cellValue = Integer.toString((Integer) cellValue);
        } else if (cellValue instanceof ReaderDTO) {
            cellValue = cellValue.toString();
        } else if (cellValue instanceof LocalDate) {
            cellValue = cellValue.toString();
        } else if (cellValue instanceof Boolean) {
            cellValue = Boolean.toString((Boolean) cellValue);
        }

        Cell cell = row.createCell(columnIndex);
        if (cellValue == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue((String) cellValue);
        }
        cell.setCellStyle(cellStyle);
    }
}
