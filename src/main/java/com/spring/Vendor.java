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
import java.util.Formatter;

/**
 * @author sunny
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
    private int cnn;
    private String permit;
    private String block;

    public Vendor() {
    }

    public Vendor(String name, String address) {
        this.applicant = name;
        this.location = address;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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

    public int getCnn() {
        return cnn;
    }

    public void setCnn(int cnn) {
        this.cnn = cnn;
    }

    @Override
    public String toString() {
        //return permit + "-" + block + "-" + cnn + "\t" + applicant + "\t\t" + location + "\t" + dayorder + "\t" + dayofweekstr + "\t" + getStarttime() + "\t" + getEndtime();
        //return applicant + "\t\t" + location +"\t" +starttime + "-" + endtime;
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%-30.30s  %-30.30s %-5.30s %-5.30s", applicant, location, starttime, endtime);
        return sb.toString();
    }


}
