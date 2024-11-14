package main.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "embedding_matrix")
public class EmbeddingMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipId;

    @Column(columnDefinition = "TEXT")
    private String vector;

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

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
