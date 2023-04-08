package id.javan.tax.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "taxes")
public class Tax implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @NotBlank
  @Size(max = 20)
  @Column(name = "receipt_number")
  private String receiptNumber;

  private TaxStatusEnum status;

  public Tax() {
  }

  public Tax(String receiptNumber) {
    this.receiptNumber = receiptNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReceiptNumber() {
    return receiptNumber;
  }

  public void setReceiptNumber(String receiptNumber) {
    this.receiptNumber = receiptNumber;
  }

  public TaxStatusEnum getStatus() {
    return status;
  }

  public void setStatus(TaxStatusEnum status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
