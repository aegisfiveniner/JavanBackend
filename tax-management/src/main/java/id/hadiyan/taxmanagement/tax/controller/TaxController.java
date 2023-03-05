package id.hadiyan.taxmanagement.tax.controller;

import id.hadiyan.taxmanagement.tax.service.TaxService;
import id.hadiyan.taxmanagement.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TaxController {
    private final TaxService taxService;

    @GetMapping("/")
    public ResponseEntity<Object> index(Pageable pageable) {
        BaseResponse<Object> response = new BaseResponse<>(
                HttpStatus.OK,
                taxService.findAll(pageable)
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submit() {
        taxService.submit();
        BaseResponse<Object> response = new BaseResponse<>(HttpStatus.CREATED, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/check")
    public ResponseEntity<Object> check(@PathVariable String id) {
        taxService.check(id);
        BaseResponse<Object> response = new BaseResponse<>(HttpStatus.OK, "success");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Object> approve(@PathVariable String id) {
        taxService.approve(id);
        BaseResponse<Object> response = new BaseResponse<>(HttpStatus.OK, "success");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Object> reject(@PathVariable String id) {
        taxService.reject(id);
        BaseResponse<Object> response = new BaseResponse<>(HttpStatus.OK, "success");
        return ResponseEntity.ok().body(response);
    }
}
