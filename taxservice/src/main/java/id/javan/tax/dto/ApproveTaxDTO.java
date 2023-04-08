package id.javan.tax.dto;

import javax.validation.constraints.NotBlank;

public class ApproveTaxDTO {
  @NotBlank
  private String status;  
  
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
