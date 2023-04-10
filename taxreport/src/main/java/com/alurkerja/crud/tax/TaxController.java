package com.alurkerja.crud.tax;

import com.alurkerja.core.controller.CrudController;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/crud/tax")
public class TaxController extends CrudController<Tax, TaxDto, TaxService, TaxRepository> {

    protected TaxController(TaxService taxService) {
        super(taxService);
    }

    @PostMapping("/excel")
    public ResponseEntity<Object> createFromFile(MultipartFile file) throws IOException, InvalidFormatException {
        TaxFromExcel taxFromExcel = new TaxFromExcel();
        List<Tax> categories = taxFromExcel.read(file.getInputStream());
        for(Tax tax : categories){
            this.crudService.create(tax);
        }
        return success(file.getOriginalFilename() + " was uploaded");
    }

    @GetMapping("/excel")
    public void exportToExcell(HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        List<Tax> categories = this.crudService.findAll();
        TaxToExcel taxToExcel = new TaxToExcel(categories);
        taxToExcel.export(outputStream);
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"tax.xlsx\"");

        outputStream.flush();
        httpServletResponse.flushBuffer();
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) throws IOException {

        return this.success(file.getOriginalFilename());
    }
}
