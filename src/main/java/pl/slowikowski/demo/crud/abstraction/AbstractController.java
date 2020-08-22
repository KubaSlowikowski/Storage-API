package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slowikowski.demo.email.Message;
import pl.slowikowski.demo.export.ExportDto;

import javax.validation.Valid;
import java.time.LocalDate;

@CrossOrigin(origins = "${storage.front.url}", allowCredentials = "true")
public abstract class AbstractController<T extends CommonService<E>, E extends AbstractDto> {

    private final T service;

    public AbstractController(T service) {
        this.service = service;
    }

    @GetMapping
    Page<E> findAll(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return service.getAll(pageable, search);
    }

    @GetMapping(path = "/{id}")
    E findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    E save(@Valid @RequestBody E dto) {
        return service.save(dto);
    }

    @PutMapping(path = "/{id}")
    E update(@PathVariable("id") Long id, @Valid @RequestBody E dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    E delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @GetMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> getPdf(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        ExportDto pdfReport = service.getAllInFile(pageable, search, ".pdf");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + pdfReport.getFileName() + "_" + LocalDate.now() + pdfReport.getExtension())
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport.getByteArray());
    }

    @GetMapping(path = "/email")
    void send(@PageableDefault Pageable pageable, @RequestParam(value = "search", required = false) String search, @ModelAttribute @Valid Message message) {
        service.sendAllInMail(pageable, search, message);
    }
}
