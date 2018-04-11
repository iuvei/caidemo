package cn.com.testchart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/1/31.
 */

public class PioBean implements Parcelable {
    private Double latitude;
    private Double longtitude;
    private String adress;
    private String name;

    public PioBean(Double latitude, Double longtitude, String adress, String name) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.adress = adress;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PioBean{" +
                "latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
