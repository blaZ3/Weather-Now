package com.example.weathernow.network.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vivek on 03/12/17.
 */

public class Hour {

    @SerializedName("time_epoch")
    @Expose
    private long timeEpoch;

    @SerializedName("time")
    @Expose
    private String time;

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
    private Integer pressureMb;

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

    @SerializedName("windchill_c")
    @Expose
    private Double windchillC;

    @SerializedName("windchill_f")
    @Expose
    private Double windchillF;

    @SerializedName("heatindex_c")
    @Expose
    private Double heatindexC;

    @SerializedName("heatindex_f")
    @Expose
    private Double heatindexF;

    @SerializedName("dewpoint_c")
    @Expose
    private Double dewpointC;

    @SerializedName("dewpoint_f")
    @Expose
    private Double dewpointF;

    @SerializedName("will_it_rain")
    @Expose
    private Integer willItRain;

    @SerializedName("chance_of_rain")
    @Expose
    private Integer chanceOfRain;

    @SerializedName("will_it_snow")
    @Expose
    private Integer willItSnow;

    @SerializedName("chance_of_snow")
    @Expose
    private Integer chanceOfSnow;

    @SerializedName("vis_km")
    @Expose
    private Double visKm;

    @SerializedName("vis_miles")
    @Expose
    private Double visMiles;

    public long getTimeEpoch() {
        return timeEpoch;
    }

    public void setTimeEpoch(long timeEpoch) {
        this.timeEpoch = timeEpoch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Integer getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(Integer pressureMb) {
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

    public Double getWindchillC() {
        return windchillC;
    }

    public void setWindchillC(Double windchillC) {
        this.windchillC = windchillC;
    }

    public Double getWindchillF() {
        return windchillF;
    }

    public void setWindchillF(Double windchillF) {
        this.windchillF = windchillF;
    }

    public Double getHeatindexC() {
        return heatindexC;
    }

    public void setHeatindexC(Double heatindexC) {
        this.heatindexC = heatindexC;
    }

    public Double getHeatindexF() {
        return heatindexF;
    }

    public void setHeatindexF(Double heatindexF) {
        this.heatindexF = heatindexF;
    }

    public Double getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(Double dewpointC) {
        this.dewpointC = dewpointC;
    }

    public Double getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(Double dewpointF) {
        this.dewpointF = dewpointF;
    }

    public Integer getWillItRain() {
        return willItRain;
    }

    public void setWillItRain(Integer willItRain) {
        this.willItRain = willItRain;
    }

    public Integer getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(Integer chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public Integer getWillItSnow() {
        return willItSnow;
    }

    public void setWillItSnow(Integer willItSnow) {
        this.willItSnow = willItSnow;
    }

    public Integer getChanceOfSnow() {
        return chanceOfSnow;
    }

    public void setChanceOfSnow(Integer chanceOfSnow) {
        this.chanceOfSnow = chanceOfSnow;
    }

    public Double getVisKm() {
        return visKm;
    }

    public void setVisKm(Double visKm) {
        this.visKm = visKm;
    }

    public Double getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(Double visMiles) {
        this.visMiles = visMiles;
    }


    @Override
    public String toString() {
        return "Hour{" +
                "timeEpoch=" + timeEpoch +
                ", time='" + time + '\'' +
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
                ", windchillC=" + windchillC +
                ", windchillF=" + windchillF +
                ", heatindexC=" + heatindexC +
                ", heatindexF=" + heatindexF +
                ", dewpointC=" + dewpointC +
                ", dewpointF=" + dewpointF +
                ", willItRain=" + willItRain +
                ", chanceOfRain='" + chanceOfRain + '\'' +
                ", willItSnow=" + willItSnow +
                ", chanceOfSnow='" + chanceOfSnow + '\'' +
                ", visKm=" + visKm +
                ", visMiles=" + visMiles +
                '}';
    }
}