package main.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voyages")
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipId;
    private String voyageNumber;
    private Integer departurePortId;
    private Integer arrivalPortId;
    private LocalDateTime eta;  // Estimated Time of Arrival
    private LocalDateTime etd;  // Estimated Time of Departure

    @Column(updatable = false, insertable = false)
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    private String arrival;

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

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public Integer getDeparturePortId() {
        return departurePortId;
    }

    public void setDeparturePortId(Integer departurePortId) {
        this.departurePortId = departurePortId;
    }

    public Integer getArrivalPortId() {
        return arrivalPortId;
    }

    public void setArrivalPortId(Integer arrivalPortId) {
        this.arrivalPortId = arrivalPortId;
    }

    public LocalDateTime getEta() {
        return eta;
    }

    public void setEta(LocalDateTime eta) {
        this.eta = eta;
    }

    public LocalDateTime getEtd() {
        return etd;
    }

    public void setEtd(LocalDateTime etd) {
        this.etd = etd;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
