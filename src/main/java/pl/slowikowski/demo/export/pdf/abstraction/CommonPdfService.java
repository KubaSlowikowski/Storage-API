package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.abstraction.AbstractDto;
import pl.slowikowski.demo.export.ExportDto;

import java.util.List;

@Service
public class CommonPdfService<D extends AbstractDto> {

    public ExportDto exportToPdf(List<D> dtos, String fileName, String extension) {
        return ExportDto.PdfBuilder.aExportDto() //FIXME sparametryzowac builder
                .withDtos(dtos)
                .withFileName(fileName)
                .withExtension(extension)
                .build();
    }
}
