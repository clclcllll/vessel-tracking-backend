package main.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ships")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String mmsi;

    @Column(length = 20, nullable = false)
    private String imo;

    @Column(length = 20, nullable = false)
    private String callSign;

    @Column(length = 100, nullable = false)
    private String shipName;

    @Column(nullable = false)
    private Integer shipTypeId;

    @Column(precision = 6, scale = 2)
    private BigDecimal length;

    @Column(precision = 6, scale = 2)
    private BigDecimal width;

    @Column
    private Integer draft;

    @Column
    private Integer countryId;

    @Column(nullable = false)
    private Timestamp createTime;

    @Column(nullable = false)
    private Timestamp updateTime;

    @Column
    private Double aisDistanceFromPortside;

    @Column
    private Double aisDistanceFromStern;

    @Column
    private Timestamp eta;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Integer getShipTypeId() {
        return shipTypeId;
    }

    public void setShipTypeId(Integer shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Double getAisDistanceFromPortside() {
        return aisDistanceFromPortside;
    }

    public void setAisDistanceFromPortside(Double aisDistanceFromPortside) {
        this.aisDistanceFromPortside = aisDistanceFromPortside;
    }

    public Double getAisDistanceFromStern() {
        return aisDistanceFromStern;
    }

    public void setAisDistanceFromStern(Double aisDistanceFromStern) {
        this.aisDistanceFromStern = aisDistanceFromStern;
    }

    public Timestamp getEta() {
        return eta;
    }

    public void setEta(Timestamp eta) {
        this.eta = eta;
    }
}
