package main.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ship_positions")
public class ShipPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipId;

    @Transient
    private Double latitude;

    @Transient
    private Double longitude;

    @Column(name = "location")
    private String location;

    private Integer speed;
    private Integer course;
    private Integer heading;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime positionTime;

    @Column(updatable = false, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime createTime;

    @PostLoad
    public void parseLocation() {
        if (this.location != null && this.location.startsWith("POINT(") && this.location.endsWith(")")) {
            try {
                String content = this.location.substring(6, this.location.length() - 1); // 去掉 "POINT(" 和 ")"
                String[] coordinates = content.split(" ");
                this.longitude = Double.parseDouble(coordinates[0]);
                this.latitude = Double.parseDouble(coordinates[1]);
            } catch (Exception e) {
                // 如果解析失败，可以打印日志或设置默认值
                this.longitude = null;
                this.latitude = null;
                System.err.println("Failed to parse location: " + this.location);
            }
        }
    }

    // Getters 和 Setters

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getHeading() {
        return heading;
    }

    public void setHeading(Integer heading) {
        this.heading = heading;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPositionTime() {
        return positionTime;
    }

    public void setPositionTime(LocalDateTime positionTime) {
        this.positionTime = positionTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
