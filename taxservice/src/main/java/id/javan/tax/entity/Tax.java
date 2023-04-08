package id.javan.tax.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "taxes")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Tax {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @NotBlank
  @Size(max = 20)
  @Column(name = "receipt_number")
  private String receiptNumber;

  @Enumerated(EnumType.STRING)
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
