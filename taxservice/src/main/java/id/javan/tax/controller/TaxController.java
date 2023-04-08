package id.javan.tax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.javan.tax.dto.ApproveTaxDTO;
import id.javan.tax.dto.TaxDTO;
import id.javan.tax.entity.Tax;
import id.javan.tax.service.TaxService;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
  @Autowired
  private TaxService taxService;

  @GetMapping("")
  @PreAuthorize("hasRole('MAKER') or hasRole('checker')")
  public List<Tax> getAllTaxes() {
    return taxService.getAllTaxes();
  }

  @PostMapping("")
  @PreAuthorize("hasRole('MAKER')")
  public Tax createTax(@RequestBody TaxDTO tax) {
    return taxService.createTax(tax);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('MAKER') or hasRole('checker')")
  public Optional<Tax> findTaxById(@PathVariable Long id) {
    return taxService.findTaxById(id);
  }

  @PostMapping("/{id}/approve")
  @PreAuthorize("hasRole('APPROVER')")
  public Tax approveTaxById(@PathVariable Long id, @RequestBody ApproveTaxDTO approveTaxDTO) {
    return taxService.approveTaxById(id, approveTaxDTO);
  }
}
