package main.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trend_predictions")
public class TrendPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipId;
    private LocalDateTime predictionTime;

    // 假设你有一个合适的类型处理点数据
    private String predictedPosition; // 请根据需要将其替换为合适的类型

    private BigDecimal confidence;

    @Column(updatable = false, insertable = false)
    private LocalDateTime createTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public LocalDateTime getPredictionTime() {
        return predictionTime;
    }

    public void setPredictionTime(LocalDateTime predictionTime) {
        this.predictionTime = predictionTime;
    }

    public String getPredictedPosition() {
        return predictedPosition;
    }

    public void setPredictedPosition(String predictedPosition) {
        this.predictedPosition = predictedPosition;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
