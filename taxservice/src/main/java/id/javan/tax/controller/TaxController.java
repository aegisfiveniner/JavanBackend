package id.javan.tax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.javan.tax.dto.ApproveTaxDTO;
import id.javan.tax.dto.TaxDTO;
import id.javan.tax.entity.RoleEnum;
import id.javan.tax.entity.Tax;
import id.javan.tax.entity.User;
import id.javan.tax.security.jwt.JwtUtils;
import id.javan.tax.service.TaxService;
import id.javan.tax.service.UserService;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
  @Autowired
  private TaxService taxService;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping("")
  @PreAuthorize("hasRole('MAKER') or hasRole('CHECKER') or hasRole('APPROVER')")
  public List<Tax> getAllTaxes(@RequestHeader("Authorization") String tokenHeader) {
    String token = tokenHeader.substring(7);
    if (!jwtUtils.validateJwtToken(token)) {
      throw new RuntimeException("Invalid token");
    }

    Optional<User> optionalUser = userService.findUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found");
    }

    User user = optionalUser.get();

    if (user.hasRole(RoleEnum.ROLE_APPROVER)) {
      return taxService.getAllTaxesForApprover();
    }

    return taxService.getAllTaxes();
  }

  @PostMapping("")
  @PreAuthorize("hasRole('MAKER')")
  public Tax createTax(@RequestBody TaxDTO tax, @RequestHeader("Authorization") String tokenHeader) {
    String token = tokenHeader.substring(7);
    if (!jwtUtils.validateJwtToken(token)) {
      throw new RuntimeException("Invalid token");
    }

    Optional<User> optionalUser = userService.findUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found");
    }

    User user = optionalUser.get();

    tax.setUserId(user.getId());
    return taxService.createTax(tax);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('MAKER') or hasRole('CHECKER')")
  public Optional<Tax> findTaxById(@PathVariable Long id) {
    return taxService.findTaxById(id);
  }

  @PostMapping("/{id}/approve")
  @PreAuthorize("hasRole('CHECKER')")
  public Tax approveTaxById(@PathVariable Long id, @RequestBody ApproveTaxDTO approveTaxDTO) {
    return taxService.approveTaxById(id, approveTaxDTO);
  }
}
