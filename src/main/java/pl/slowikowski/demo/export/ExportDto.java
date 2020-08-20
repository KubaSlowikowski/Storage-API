package pl.slowikowski.demo.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        private String title;
        private List<String> fieldsToIgnore;
        private PdfPTable table;
        private Document document;
        private List<Field> fields;

        private PdfBuilder() {
        }

        public static PdfBuilder aExportDto() {
            return new PdfBuilder();
        }

        public PdfBuilder withDtos(List<D> dtos) {
            this.dtos = dtos;
            return this;
        }

        public PdfBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public PdfBuilder withFieldsToIgnore(List<String> fieldsToIgnore) {
            this.fieldsToIgnore = fieldsToIgnore;
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

        public ExportDto build() {

            fields = new ArrayList<>(List.of(AbstractDto.class.getDeclaredFields()));
            fields.addAll(List.of(dtos.get(0).getClass().getDeclaredFields()));

            ignoreFields();

            table = new PdfPTable(fields.size());
            document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            try {
                PdfWriter.getInstance(document, out);
                createDocument();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            return new ExportDto(fileName, extension, out.toByteArray());
        }

        private void createDocument() throws DocumentException {
            document.open();
            addTitle();
            initTableHeaders();
            fillCells();
            document.add(table);
            document.close();
        }

        private void ignoreFields() {
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
        }

        private void addTitle() throws DocumentException {
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph(title, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);
        }

        private void initTableHeaders() {
            fields.forEach(field -> {
                PdfPCell header = new PdfPCell();
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(field.getName(), headerFont));
                table.addCell(header);
            });
        }

        private void fillCells() {
            Font cellFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 10, BaseColor.BLACK);
            for (D dto : dtos) {
                fields.forEach(field -> {
                    try {
                        field.setAccessible(true);
                        createCell(String.valueOf(field.get(dto)), cellFont);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        private void createCell(String cellData, Font cellFont) {
            if (cellData.equals("null")) {
                cellData = null;
            }
            PdfPCell cell = new PdfPCell(new Phrase(cellData, cellFont));
            cell.setPadding(10);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}
