/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

/**
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {

    private @Id
    @GeneratedValue
    Long id;
    private String applicant;
    private String location;
    private int dayorder;
    private String dayofweekstr;
    private String starttime;
    private String endtime;
    private boolean open;

    public Vendor() {
    }

    public Vendor(String name, String address) {
        this.applicant = name;
        this.location = address;
        this.open = this.isOperating(starttime, endtime);
    }

    /**
     * @return the applicant
     */
    public String getApplicant() {
        return applicant;
    }

    /**
     * @param applicant the applicant to set
     */
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getDayOrder() {

        return dayorder;
    }

    /**
     * @return the dayofweekstr
     */
    public String getDayofweekstr() {
        return dayofweekstr;
    }

    /**
     * @param dayofweekstr the dayofweekstr to set
     */
    public void setDayofweekstr(String dayofweekstr) {
        this.dayofweekstr = dayofweekstr;
    }

    /**
     * @return the dayorder
     */
    public int getDayorder() {
        return dayorder;
    }

    /**
     * @param dayorder the dayorder to set
     */
    public void setDayorder(int dayorder) {
        this.dayorder = dayorder;
    }

    public boolean getOpen() {
        open = isOperating(starttime, endtime);
        return open;
    }

    public String getStatus() {
        if (isOperating(starttime, endtime)) {
            return "OPEN";
        }
        return "CLOSED";
    }

    private boolean isOperating(String starttime, String endtime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("Ka");
        LocalTime cur = LocalTime.now();
        LocalTime start = LocalTime.parse(starttime, dtf);
        LocalTime closed = LocalTime.parse(endtime, dtf);
        return cur.isAfter(start) && cur.isBefore(closed);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-30.30s  %-30.30s %-5.30s %-5.30s %-5.30s", applicant, location, starttime, endtime, getStatus());
        return sb.toString();
    }
}
