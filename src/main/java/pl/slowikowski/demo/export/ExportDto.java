package pl.slowikowski.demo.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;
import pl.slowikowski.demo.export.pdf.annotation.PdfIgnoreField;
import pl.slowikowski.demo.export.pdf.annotation.PdfTableName;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExportDto {
    private String fileName;
    private byte[] byteArray;
    private String extension;

    public ExportDto(String fileName, String extension, byte[] byteArray) {
        this.fileName = fileName;
        this.extension = extension;
        this.byteArray = byteArray;
    }

    public static final class PdfBuilder<D extends AbstractDto> {
        private List<D> dtos;
        private String fileName;
        private String extension;

        private PdfBuilder() {
        }

        public static PdfBuilder aExportDto() {
            return new PdfBuilder();
        }

        public PdfBuilder withDtos(List<D> dtos) {
            this.dtos = dtos;
            return this;
        }

        public PdfBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public PdfBuilder withExtension(String extension) {
            this.extension = extension;
            return this;
        }

        @SneakyThrows
        public ExportDto build() {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Class<?> clazz = dtos.iterator().next().getClass();

            PdfWriter.getInstance(document, out);
            document.open();

            List<Field> columns = getFields(clazz);

            if (!columns.isEmpty()) {
                String title = getTitle(clazz);
                document.add(getParagraph(title));
                document.add(Chunk.NEWLINE);
                PdfPTable pdfPTable = getPdfTable(columns);
                document.add(pdfPTable);
            } else {
                document.add(new Phrase("Document is empty"));
            }
            document.close();

            if(fileName == null) {
                fileName = clazz.getSimpleName();
            }

            return new ExportDto(fileName, extension, out.toByteArray());
        }

        private List<Field> getFields(Class<?> clazz) {
            Class<?> superclass = clazz.getSuperclass();
            Field[] fields = clazz.getDeclaredFields();
            Field[] superClassField = superclass.getDeclaredFields();
            List<Field> columns = new ArrayList<>();
            for (Field field : superClassField) {
                if (!field.isAnnotationPresent(PdfIgnoreField.class)) {
                    columns.add(field);
                }
            }
            for (Field field : fields) {
                if (!field.isAnnotationPresent(PdfIgnoreField.class)) {
                    columns.add(field);
                }
            }
            return columns;
        }

        private Paragraph getParagraph(String title) {
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph(title, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            return paragraph;
        }

        private static String getTitle(Class<?> clazz) {
            if (!clazz.isAnnotationPresent(PdfTableName.class)) {
                return "";
            } else {
                PdfTableName pdfTableName = clazz.getAnnotation(PdfTableName.class);
                return pdfTableName.value();
            }
        }

        private PdfPTable getPdfTable(List<Field> columns) {
            PdfPTable pdfPTable = new PdfPTable(columns.size());

            columns.forEach(field -> {
                PdfPCell header = createHeader(field.getName());
                pdfPTable.addCell(header);
            });

            Font cellFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 10, BaseColor.BLACK);
            for (D dto : dtos) {
                columns.forEach(field -> {
                    try {
                        field.setAccessible(true);
                        PdfPCell cell = createCell(String.valueOf(field.get(dto)), cellFont);
                        pdfPTable.addCell(cell);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }
            pdfPTable.setWidthPercentage(100);
            return pdfPTable;
        }

        private PdfPCell createHeader(String name) {
            PdfPCell header = new PdfPCell();
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(name, headerFont));
            return header;
        }

        private PdfPCell createCell(String cellData, Font cellFont) {
            if (cellData.equals("null")) {
                cellData = null;
            }
            PdfPCell cell = new PdfPCell(new Phrase(cellData, cellFont));
            cell.setPadding(10);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return cell;
        }
    }
}
