package zazzera.antoine.whereismydrone;

import android.os.Parcel;
import android.os.Parcelable;

public class Temperature implements Parcelable {
    private String id;
    private Double celcius, fahrenheit;

    public Temperature(){
        id = "5710140ee4b06cd8db816809";
        celcius = fahrenheit = 0.0;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCelcius() {
        return celcius;
    }

    public void setCelcius(Double celcius) {
        this.celcius = celcius;
    }

    public Double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    /**
     * Constucteur permettant de créer un objet Car à partir d'un Parcel
     * @param in
     */
    private Temperature(Parcel in){
        id = in.readString();
        celcius = in.readDouble();
        fahrenheit = in.readDouble();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeDouble(celcius);
        dest.writeDouble(fahrenheit);
    }

    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };
}
