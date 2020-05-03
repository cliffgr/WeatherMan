package com.example.weatherman.data.model.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherIconUrl implements Parcelable
{

    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<WeatherIconUrl> CREATOR = new Creator<WeatherIconUrl>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherIconUrl createFromParcel(Parcel in) {
            return new WeatherIconUrl(in);
        }

        public WeatherIconUrl[] newArray(int size) {
            return (new WeatherIconUrl[size]);
        }

    }
            ;

    protected WeatherIconUrl(Parcel in) {
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WeatherIconUrl() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}