package id.javan.tax.converter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import id.javan.tax.dto.TaxDTO;
import id.javan.tax.dto.UserDTO;
import id.javan.tax.entity.Tax;
import id.javan.tax.entity.TaxStatusEnum;
import id.javan.tax.entity.User;
import id.javan.tax.repository.UserRepository;

public class TaxConverter {
  @Autowired
  private UserRepository userRepository;

  public Tax FormToEntity(TaxDTO taxDTO) {
    Optional<User> optionalUser = userRepository.findById(taxDTO.getUserId()); 
    
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("User not found");
    }

    User user = optionalUser.get();

    Tax tax = new Tax();
    tax.setReceiptNumber(taxDTO.getReceiptNumber());
    tax.setStatus(TaxStatusEnum.DRAFT);
    tax.setUser(user);
    return tax;
  }

  public TaxDTO EntityToForm(Tax entity) {
    TaxDTO taxDTO = new TaxDTO();
    taxDTO.setUserId(entity.getUser().getId());
    taxDTO.setReceiptNumber(entity.getReceiptNumber());
    return taxDTO;
  }
}
