package main.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ports")
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "port_code", length = 20)
    private String portCode; // 港口代码

    @Column(name = "port_name", length = 100)
    private String portName; // 港口名称

    @Column(length = 255)
    private String abbreviation; // 港口名称缩写

    @Column(length = 255)
    private String country; // 国家

    @Column(name = "country_id")
    private Integer countryId; // 国家ID

    @Column(name = "port_type", length = 20)
    private String portType; // 港口类型

    @Column(name = "port_size", length = 20)
    private String portSize; // 港口大小

    @Column(length = 20)
    private String position; // 港口位置（Point 类型）

    @Column(name = "time_zone", length = 20)
    private String timeZone; // 时区

    @Column(length = 255)
    private String anchorage; // 锚地

    @Column(name = "berth_draft", length = 255)
    private String berthDraft; // 泊位吃水

    @Column(name = "chart_number")
    private Integer chartNumber; // 海图号

    @Column(name = "port_authority", length = 255)
    private String portAuthority; // 港务局

    @Column(length = 255)
    private String address; // 地址

    @Column(length = 255)
    private String phone; // 电话

    @Column(length = 255)
    private String fax; // 传真

    @Column(name = "website", length = 255)
    private String website; // 网站

    @Column(columnDefinition = "TEXT")
    private String introduction; // 介绍

    @Column(name = "create_time", updatable = false, insertable = false)
    private LocalDateTime createTime; // 创建时间

    @Column(name = "update_time")
    private LocalDateTime updateTime; // 更新时间

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getPortSize() {
        return portSize;
    }

    public void setPortSize(String portSize) {
        this.portSize = portSize;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAnchorage() {
        return anchorage;
    }

    public void setAnchorage(String anchorage) {
        this.anchorage = anchorage;
    }

    public String getBerthDraft() {
        return berthDraft;
    }

    public void setBerthDraft(String berthDraft) {
        this.berthDraft = berthDraft;
    }

    public Integer getChartNumber() {
        return chartNumber;
    }

    public void setChartNumber(Integer chartNumber) {
        this.chartNumber = chartNumber;
    }

    public String getPortAuthority() {
        return portAuthority;
    }

    public void setPortAuthority(String portAuthority) {
        this.portAuthority = portAuthority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
}
