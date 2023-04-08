package id.javan.tax.dto;

import javax.validation.constraints.NotBlank;

public class TaxDTO {
  @NotBlank
  private String receiptNumber;

  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getReceiptNumber() {
    return receiptNumber;
  }

  public void setReceiptNumber(String receiptNumber) {
    this.receiptNumber = receiptNumber;
  }
}
