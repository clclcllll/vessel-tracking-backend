package main.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "association_rules")
public class AssociationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String rule;

    private BigDecimal support;
    private BigDecimal confidence;
    private BigDecimal lift;

    @Column(updatable = false, insertable = false)
    private LocalDateTime createTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public BigDecimal getSupport() {
        return support;
    }

    public void setSupport(BigDecimal support) {
        this.support = support;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public BigDecimal getLift() {
        return lift;
    }

    public void setLift(BigDecimal lift) {
        this.lift = lift;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
