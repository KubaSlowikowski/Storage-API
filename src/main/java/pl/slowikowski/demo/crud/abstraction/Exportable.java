package pl.slowikowski.demo.crud.abstraction;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.slowikowski.demo.export.ExportDto;

import java.util.List;

interface Exportable<D extends AbstractDto> {

    @PreAuthorize("hasRole('USER')")
    default ExportDto toPdfReport(List<D> dtos, String extension) {
        return toPdfReport(dtos, null, extension);
    }

    @PreAuthorize("hasRole('USER')")
    default ExportDto toPdfReport(List<D> dtos, String fileName, String extension) {
        return ExportDto.PdfBuilder.aExportDto() //FIXME sparametryzowac builder
                .withDtos(dtos)
                .withFileName(fileName)
                .withExtension(extension)
                .build();
    }
}
