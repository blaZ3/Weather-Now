package com.example.weathernow.network.pojos;

import android.text.format.DateFormat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vivek on 03/12/17.
 */

public class Forecastday {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("date_epoch")
    @Expose
    private long dateEpoch;

    @SerializedName("day")
    @Expose
    private Day day;

    @SerializedName("astro")
    @Expose
    private Astro astro;

    @SerializedName("hour")
    @Expose
    private List<Hour> hour = null;

    public String getDate() {
        return date;
    }

    public String getDateAsDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dateDt = format.parse(date);
            String dayOfTheWeek = (String) DateFormat.format("EEEE", dateDt);
            return dayOfTheWeek;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(long dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }


    @Override
    public String toString() {
        return "Forecastday{" +
                "date='" + date + '\'' +
                ", dateEpoch=" + dateEpoch +
                ", day=" + day +
                ", astro=" + astro +
                ", hour=" + hour +
                '}';
    }
}