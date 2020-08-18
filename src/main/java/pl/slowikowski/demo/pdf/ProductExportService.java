package pl.slowikowski.demo.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductExportService {
    private final PdfPTable table = new PdfPTable(5);

    public ByteArrayInputStream productsPdfReport(List<ProductDTO> products) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            //add text to pdf file
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
            Paragraph paragraph = new Paragraph("Products list", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            //make column titles
            Stream.of("ID", "Name", "Description", "Price", "Sold", "Group ID").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.GREEN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle, headerFont));
                table.addCell(header);
            });

            for (ProductDTO product : products) {
                createCell(Long.toString(product.getId()));
                createCell(product.getDescription());
                createCell(Integer.toString(product.getPrice()));
                createCell(Boolean.toString(product.isSold()));
                createCell(Long.toString(product.getGroupId()));
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void createCell(String cellData) {
        PdfPCell cell = new PdfPCell(new Phrase(cellData));
        cell.setPadding(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }
}
