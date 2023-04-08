package id.javan.tax.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.javan.tax.converter.TaxConverter;
import id.javan.tax.dto.ApproveTaxDTO;
import id.javan.tax.dto.TaxDTO;
import id.javan.tax.entity.Tax;
import id.javan.tax.entity.TaxStatusEnum;
import id.javan.tax.repository.TaxRepository;

@Service
public class TaxService {
  @Autowired
  private TaxRepository taxRepository;

  @Autowired
  private TaxConverter taxConverter;

  public List<Tax> getAllTaxes() {
    return taxRepository.findAll();
  }

  public Tax createTax(TaxDTO taxDTO) {
    Tax tax = taxConverter.FormToEntity(taxDTO);
    Tax savedTax = taxRepository.save(tax);
    return savedTax;
  }

  public Optional<Tax> findTaxById(Long id) {
    return taxRepository.findById(id);
  }

  public String deleteTaxById(Long id) {
    taxRepository.deleteById(id);
    return "Successfully deleted tax with id: " + id;
  }

  public Tax updateTax(Tax tax) {
    Tax savedTax = taxRepository.save(tax);
    return savedTax;
  }

  public Tax approveTaxById(Long id, ApproveTaxDTO approveTaxDTO) {
    Optional<Tax> optionalTax = taxRepository.findById(id);
    
    if (optionalTax.isEmpty()) {
      throw new RuntimeException("Tax with id " + id + " not found.");
    }

    Tax tax = optionalTax.get();

    String approvalStatus = approveTaxDTO.getStatus();
    TaxStatusEnum taxStatusEnum = TaxStatusEnum.valueOf(approvalStatus);
    
    tax.setStatus(taxStatusEnum);

    Tax savedTax = taxRepository.save(tax);
    return savedTax;
  }
}