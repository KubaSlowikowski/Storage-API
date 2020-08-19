package pl.slowikowski.demo.export.pdf.productGroup;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductGroupPdfService {
    private PdfPTable table;

    public ByteArrayInputStream exportToPdf(List<ProductGroupDTO> groups, String title, int numColumns) {
        table = new PdfPTable(numColumns);
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
            Stream.of("ID", "Name", "Description", "Products").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle, headerFont));
                table.addCell(header);
            });

            for (ProductGroupDTO group : groups) {
                createCell(Long.toString(group.getId()));
                createCell(group.getName());
                createCell(group.getDescription());
                createCell(group.getProducts()
                        .stream()
                        .map(ProductDTO::toString)
                        .collect(Collectors.toList())
                        .toString());
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void createCell(String cellData) {
        Font cellFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 10, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(cellData, cellFont));
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
