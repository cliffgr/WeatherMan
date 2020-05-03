package com.example.weatherman.data.model.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDesc implements Parcelable
{

    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<WeatherDesc> CREATOR = new Creator<WeatherDesc>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherDesc createFromParcel(Parcel in) {
            return new WeatherDesc(in);
        }

        public WeatherDesc[] newArray(int size) {
            return (new WeatherDesc[size]);
        }

    }
            ;

    protected WeatherDesc(Parcel in) {
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WeatherDesc() {
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