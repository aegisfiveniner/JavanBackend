package id.javan.tax.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.javan.tax.dto.TaxDTO;
import id.javan.tax.entity.Tax;
import id.javan.tax.repository.TaxRepository;

@Service
public class TaxService {
  @Autowired
  private TaxRepository taxRepository;

  public List<Tax> getAllTaxes() {
    return taxRepository.findAll();
  }

  public Tax createTax(TaxDTO taxDTO) {
    Tax tax = new Tax();
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
}