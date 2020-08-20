package pl.slowikowski.demo.export.pdf.abstraction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;
import pl.slowikowski.demo.export.ExportDto;

public abstract class AbstractExportPdfService {

    public abstract ExportDto exportToPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search);
}
