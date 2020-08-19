package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CommonPdfService<D extends AbstractDto> {

    public ByteArrayInputStream exportToPdf(List<D> dtos, String title) {
        return PdfBuilder.aPdf()
                .withDtos(dtos)
                .withTitle(title)
                .build();
    }

    public ByteArrayInputStream exportToPdf(List<D> dtos, String title, List<String> fieldsToIgnore) {
        return PdfBuilder.aPdf()
                .withDtos(dtos)
                .withTitle(title)
                .withFieldsToIgnore(fieldsToIgnore)
                .build();
    }
}
