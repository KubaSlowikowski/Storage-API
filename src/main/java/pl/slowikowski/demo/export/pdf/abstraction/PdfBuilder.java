package pl.slowikowski.demo.export.pdf.abstraction;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PdfBuilder<D extends AbstractDto> {
    private List<D> dtos;
    private String title;
    private List<String> fieldsToIgnore;
    private PdfPTable table;

    private PdfBuilder() {
    }

    public static PdfBuilder aPdf() {
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

    public ByteArrayInputStream build() {

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

        //table size
        table = new PdfPTable(fields.size());

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            //add text to pdf file
            Font font = FontFactory.getFont(FontFactory.COURIER, 17, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph(title, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            //make column titles
            fields.forEach(field -> {
                PdfPCell header = new PdfPCell();
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(field.getName(), headerFont));
                table.addCell(header);
            });
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
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
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
