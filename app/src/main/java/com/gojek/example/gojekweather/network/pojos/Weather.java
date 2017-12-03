package com.gojek.example.gojekweather.network.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vivek on 03/12/17.
 */

public class Weather {

    @SerializedName("last_updated_epoch")
    @Expose
    private long lastUpdatedEpoch;

    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    @SerializedName("temp_f")
    @Expose
    private Double tempF;

    @SerializedName("is_day")
    @Expose
    private Integer isDay;

    @SerializedName("condition")
    @Expose
    private Condition condition;

    @SerializedName("wind_mph")
    @Expose
    private Double windMph;

    @SerializedName("wind_kph")
    @Expose
    private Double windKph;

    @SerializedName("wind_degree")
    @Expose
    private Double windDegree;

    @SerializedName("wind_dir")
    @Expose
    private String windDir;

    @SerializedName("pressure_mb")
    @Expose
    private Double pressureMb;

    @SerializedName("pressure_in")
    @Expose
    private Double pressureIn;

    @SerializedName("precip_mm")
    @Expose
    private Double precipMm;

    @SerializedName("precip_in")
    @Expose
    private Double precipIn;

    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    @SerializedName("cloud")
    @Expose
    private Integer cloud;

    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;

    @SerializedName("feelslike_f")
    @Expose
    private Double feelslikeF;

    @SerializedName("vis_km")
    @Expose
    private Double visKm;

    @SerializedName("vis_miles")
    @Expose
    private Integer visMiles;

    public long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Double getTempC() {
        return tempC;
    }

    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

    public Double getTempF() {
        return tempF;
    }

    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Integer getIsDay() {
        return isDay;
    }

    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Double windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Double getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(Double pressureMb) {
        this.pressureMb = pressureMb;
    }

    public Double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public Double getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(Double precipMm) {
        this.precipMm = precipMm;
    }

    public Double getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(Double precipIn) {
        this.precipIn = precipIn;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloud() {
        return cloud;
    }

    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public Double getVisKm() {
        return visKm;
    }

    public void setVisKm(Double visKm) {
        this.visKm = visKm;
    }

    public Integer getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(Integer visMiles) {
        this.visMiles = visMiles;
    }


    @Override
    public String toString() {
        return "Weather{" +
                "lastUpdatedEpoch=" + lastUpdatedEpoch +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", tempC=" + tempC +
                ", tempF=" + tempF +
                ", isDay=" + isDay +
                ", condition=" + condition +
                ", windMph=" + windMph +
                ", windKph=" + windKph +
                ", windDegree=" + windDegree +
                ", windDir='" + windDir + '\'' +
                ", pressureMb=" + pressureMb +
                ", pressureIn=" + pressureIn +
                ", precipMm=" + precipMm +
                ", precipIn=" + precipIn +
                ", humidity=" + humidity +
                ", cloud=" + cloud +
                ", feelslikeC=" + feelslikeC +
                ", feelslikeF=" + feelslikeF +
                ", visKm=" + visKm +
                ", visMiles=" + visMiles +
                '}';
    }
}