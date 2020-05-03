package com.example.weatherman.data.model.weather;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hourly implements Parcelable {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("tempC")
    @Expose
    private String tempC;
    @SerializedName("tempF")
    @Expose
    private String tempF;
    @SerializedName("windspeedMiles")
    @Expose
    private String windspeedMiles;
    @SerializedName("windspeedKmph")
    @Expose
    private String windspeedKmph;
    @SerializedName("winddirDegree")
    @Expose
    private String winddirDegree;
    @SerializedName("winddir16Point")
    @Expose
    private String winddir16Point;
    @SerializedName("weatherCode")
    @Expose
    private String weatherCode;
    @SerializedName("weatherIconUrl")
    @Expose
    private List<WeatherIconUrl> weatherIconUrl = null;
    @SerializedName("weatherDesc")
    @Expose
    private List<WeatherDesc> weatherDesc = null;
    @SerializedName("precipMM")
    @Expose
    private String precipMM;
    @SerializedName("precipInches")
    @Expose
    private String precipInches;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("visibilityMiles")
    @Expose
    private String visibilityMiles;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("pressureInches")
    @Expose
    private String pressureInches;
    @SerializedName("cloudcover")
    @Expose
    private String cloudcover;
    @SerializedName("HeatIndexC")
    @Expose
    private String heatIndexC;
    @SerializedName("HeatIndexF")
    @Expose
    private String heatIndexF;
    @SerializedName("DewPointC")
    @Expose
    private String dewPointC;
    @SerializedName("DewPointF")
    @Expose
    private String dewPointF;
    @SerializedName("WindChillC")
    @Expose
    private String windChillC;
    @SerializedName("WindChillF")
    @Expose
    private String windChillF;
    @SerializedName("WindGustMiles")
    @Expose
    private String windGustMiles;
    @SerializedName("WindGustKmph")
    @Expose
    private String windGustKmph;
    @SerializedName("FeelsLikeC")
    @Expose
    private String feelsLikeC;
    @SerializedName("FeelsLikeF")
    @Expose
    private String feelsLikeF;
    @SerializedName("chanceofrain")
    @Expose
    private String chanceofrain;
    @SerializedName("chanceofremdry")
    @Expose
    private String chanceofremdry;
    @SerializedName("chanceofwindy")
    @Expose
    private String chanceofwindy;
    @SerializedName("chanceofovercast")
    @Expose
    private String chanceofovercast;
    @SerializedName("chanceofsunshine")
    @Expose
    private String chanceofsunshine;
    @SerializedName("chanceoffrost")
    @Expose
    private String chanceoffrost;
    @SerializedName("chanceofhightemp")
    @Expose
    private String chanceofhightemp;
    @SerializedName("chanceoffog")
    @Expose
    private String chanceoffog;
    @SerializedName("chanceofsnow")
    @Expose
    private String chanceofsnow;
    @SerializedName("chanceofthunder")
    @Expose
    private String chanceofthunder;
    @SerializedName("uvIndex")
    @Expose
    private String uvIndex;
    public final static Parcelable.Creator<Hourly> CREATOR = new Creator<Hourly>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Hourly createFromParcel(Parcel in) {
            return new Hourly(in);
        }

        public Hourly[] newArray(int size) {
            return (new Hourly[size]);
        }

    };

    protected Hourly(Parcel in) {
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.tempC = ((String) in.readValue((String.class.getClassLoader())));
        this.tempF = ((String) in.readValue((String.class.getClassLoader())));
        this.windspeedMiles = ((String) in.readValue((String.class.getClassLoader())));
        this.windspeedKmph = ((String) in.readValue((String.class.getClassLoader())));
        this.winddirDegree = ((String) in.readValue((String.class.getClassLoader())));
        this.winddir16Point = ((String) in.readValue((String.class.getClassLoader())));
        this.weatherCode = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.weatherIconUrl, (WeatherIconUrl.class.getClassLoader()));
        in.readList(this.weatherDesc, (WeatherDesc.class.getClassLoader()));
        this.precipMM = ((String) in.readValue((String.class.getClassLoader())));
        this.precipInches = ((String) in.readValue((String.class.getClassLoader())));
        this.humidity = ((String) in.readValue((String.class.getClassLoader())));
        this.visibility = ((String) in.readValue((String.class.getClassLoader())));
        this.visibilityMiles = ((String) in.readValue((String.class.getClassLoader())));
        this.pressure = ((String) in.readValue((String.class.getClassLoader())));
        this.pressureInches = ((String) in.readValue((String.class.getClassLoader())));
        this.cloudcover = ((String) in.readValue((String.class.getClassLoader())));
        this.heatIndexC = ((String) in.readValue((String.class.getClassLoader())));
        this.heatIndexF = ((String) in.readValue((String.class.getClassLoader())));
        this.dewPointC = ((String) in.readValue((String.class.getClassLoader())));
        this.dewPointF = ((String) in.readValue((String.class.getClassLoader())));
        this.windChillC = ((String) in.readValue((String.class.getClassLoader())));
        this.windChillF = ((String) in.readValue((String.class.getClassLoader())));
        this.windGustMiles = ((String) in.readValue((String.class.getClassLoader())));
        this.windGustKmph = ((String) in.readValue((String.class.getClassLoader())));
        this.feelsLikeC = ((String) in.readValue((String.class.getClassLoader())));
        this.feelsLikeF = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofrain = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofremdry = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofwindy = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofovercast = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofsunshine = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceoffrost = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofhightemp = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceoffog = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofsnow = ((String) in.readValue((String.class.getClassLoader())));
        this.chanceofthunder = ((String) in.readValue((String.class.getClassLoader())));
        this.uvIndex = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Hourly() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
    }

    public String getWindspeedMiles() {
        return windspeedMiles;
    }

    public void setWindspeedMiles(String windspeedMiles) {
        this.windspeedMiles = windspeedMiles;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(String windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getWinddirDegree() {
        return winddirDegree;
    }

    public void setWinddirDegree(String winddirDegree) {
        this.winddirDegree = winddirDegree;
    }

    public String getWinddir16Point() {
        return winddir16Point;
    }

    public void setWinddir16Point(String winddir16Point) {
        this.winddir16Point = winddir16Point;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<WeatherIconUrl> getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(List<WeatherIconUrl> weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public List<WeatherDesc> getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(List<WeatherDesc> weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(String precipMM) {
        this.precipMM = precipMM;
    }

    public String getPrecipInches() {
        return precipInches;
    }

    public void setPrecipInches(String precipInches) {
        this.precipInches = precipInches;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getVisibilityMiles() {
        return visibilityMiles;
    }

    public void setVisibilityMiles(String visibilityMiles) {
        this.visibilityMiles = visibilityMiles;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPressureInches() {
        return pressureInches;
    }

    public void setPressureInches(String pressureInches) {
        this.pressureInches = pressureInches;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(String cloudcover) {
        this.cloudcover = cloudcover;
    }

    public String getHeatIndexC() {
        return heatIndexC;
    }

    public void setHeatIndexC(String heatIndexC) {
        this.heatIndexC = heatIndexC;
    }

    public String getHeatIndexF() {
        return heatIndexF;
    }

    public void setHeatIndexF(String heatIndexF) {
        this.heatIndexF = heatIndexF;
    }

    public String getDewPointC() {
        return dewPointC;
    }

    public void setDewPointC(String dewPointC) {
        this.dewPointC = dewPointC;
    }

    public String getDewPointF() {
        return dewPointF;
    }

    public void setDewPointF(String dewPointF) {
        this.dewPointF = dewPointF;
    }

    public String getWindChillC() {
        return windChillC;
    }

    public void setWindChillC(String windChillC) {
        this.windChillC = windChillC;
    }

    public String getWindChillF() {
        return windChillF;
    }

    public void setWindChillF(String windChillF) {
        this.windChillF = windChillF;
    }

    public String getWindGustMiles() {
        return windGustMiles;
    }

    public void setWindGustMiles(String windGustMiles) {
        this.windGustMiles = windGustMiles;
    }

    public String getWindGustKmph() {
        return windGustKmph;
    }

    public void setWindGustKmph(String windGustKmph) {
        this.windGustKmph = windGustKmph;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(String feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public String getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(String feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    public String getChanceofrain() {
        return chanceofrain;
    }

    public void setChanceofrain(String chanceofrain) {
        this.chanceofrain = chanceofrain;
    }

    public String getChanceofremdry() {
        return chanceofremdry;
    }

    public void setChanceofremdry(String chanceofremdry) {
        this.chanceofremdry = chanceofremdry;
    }

    public String getChanceofwindy() {
        return chanceofwindy;
    }

    public void setChanceofwindy(String chanceofwindy) {
        this.chanceofwindy = chanceofwindy;
    }

    public String getChanceofovercast() {
        return chanceofovercast;
    }

    public void setChanceofovercast(String chanceofovercast) {
        this.chanceofovercast = chanceofovercast;
    }

    public String getChanceofsunshine() {
        return chanceofsunshine;
    }

    public void setChanceofsunshine(String chanceofsunshine) {
        this.chanceofsunshine = chanceofsunshine;
    }

    public String getChanceoffrost() {
        return chanceoffrost;
    }

    public void setChanceoffrost(String chanceoffrost) {
        this.chanceoffrost = chanceoffrost;
    }

    public String getChanceofhightemp() {
        return chanceofhightemp;
    }

    public void setChanceofhightemp(String chanceofhightemp) {
        this.chanceofhightemp = chanceofhightemp;
    }

    public String getChanceoffog() {
        return chanceoffog;
    }

    public void setChanceoffog(String chanceoffog) {
        this.chanceoffog = chanceoffog;
    }

    public String getChanceofsnow() {
        return chanceofsnow;
    }

    public void setChanceofsnow(String chanceofsnow) {
        this.chanceofsnow = chanceofsnow;
    }

    public String getChanceofthunder() {
        return chanceofthunder;
    }

    public void setChanceofthunder(String chanceofthunder) {
        this.chanceofthunder = chanceofthunder;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(tempC);
        dest.writeValue(tempF);
        dest.writeValue(windspeedMiles);
        dest.writeValue(windspeedKmph);
        dest.writeValue(winddirDegree);
        dest.writeValue(winddir16Point);
        dest.writeValue(weatherCode);
        dest.writeList(weatherIconUrl);
        dest.writeList(weatherDesc);
        dest.writeValue(precipMM);
        dest.writeValue(precipInches);
        dest.writeValue(humidity);
        dest.writeValue(visibility);
        dest.writeValue(visibilityMiles);
        dest.writeValue(pressure);
        dest.writeValue(pressureInches);
        dest.writeValue(cloudcover);
        dest.writeValue(heatIndexC);
        dest.writeValue(heatIndexF);
        dest.writeValue(dewPointC);
        dest.writeValue(dewPointF);
        dest.writeValue(windChillC);
        dest.writeValue(windChillF);
        dest.writeValue(windGustMiles);
        dest.writeValue(windGustKmph);
        dest.writeValue(feelsLikeC);
        dest.writeValue(feelsLikeF);
        dest.writeValue(chanceofrain);
        dest.writeValue(chanceofremdry);
        dest.writeValue(chanceofwindy);
        dest.writeValue(chanceofovercast);
        dest.writeValue(chanceofsunshine);
        dest.writeValue(chanceoffrost);
        dest.writeValue(chanceofhightemp);
        dest.writeValue(chanceoffog);
        dest.writeValue(chanceofsnow);
        dest.writeValue(chanceofthunder);
        dest.writeValue(uvIndex);
    }

    public int describeContents() {
        return 0;
    }

}