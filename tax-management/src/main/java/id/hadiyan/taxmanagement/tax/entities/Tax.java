package id.hadiyan.taxmanagement.tax.entities;

import id.hadiyan.taxmanagement.enums.TaxStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tax")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tax {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaxStatus status;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "user_name")
    private String userName;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "checked_at")
    private LocalDateTime checkedAt;

    @Column(name = "checked_by")
    private String checkedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;
}
