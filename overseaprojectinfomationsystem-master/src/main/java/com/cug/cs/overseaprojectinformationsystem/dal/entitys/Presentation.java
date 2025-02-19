package com.cug.cs.overseaprojectinformationsystem.dal.entitys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description 技术宣讲pojo
 */
//@Table(name = "market_tecpresentation")
@TableName(value = "market_tecpresentation")
@Data
@ToString
public class Presentation {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String organizer;

    @Column(name = "pre_theme")
    private String preTheme;

    @Column(name = "pre_content")
    private String preContent;

    @Column(name = "pre_format")
    private String preFormat;

    @Column(name = "pre_place")
    private String prePlace;

    private String comInvolved;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    private String staff;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getPreTheme() {
        return preTheme;
    }

    public void setPreTheme(String preTheme) {
        this.preTheme = preTheme;
    }

    public String getPreContent() {
        return preContent;
    }

    public void setPreContent(String preContent) {
        this.preContent = preContent;
    }

    public String getPreFormat() {
        return preFormat;
    }

    public void setPreFormat(String preFormat) {
        this.preFormat = preFormat;
    }

    public String getPrePlace() {
        return prePlace;
    }

    public void setPrePlace(String prePlace) {
        this.prePlace = prePlace;
    }

    public String getComInvolved() {
        return comInvolved;
    }

    public void setComInvolved(String comInvolved) {
        this.comInvolved = comInvolved;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
