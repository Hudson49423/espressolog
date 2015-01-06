package espressolog.com.espressolog;

import android.util.Log;

/**
 * Created by hudson49423 on 1/1/15.
 */
public class LogItem {
    private String shotTime;
    private String shotWeight;
    private String brewRatio;
    private String temperature;
    private String date;
    private String rating;
    private int id;
    private String dose;

    public LogItem() {
    }

    public LogItem(String shotTime, String shotWeight, String temperature, String brewRatio,
                String date, String rating){
        this.brewRatio = brewRatio;
        this.temperature = temperature;
        this.shotTime = shotTime;
        this.shotWeight = shotWeight;
        this.date = date;
    }

    public String getShotTime(){
        return shotTime;
    }

    public String getShotWeight(){
        return shotWeight;
    }

    public String getBrewRatio(){
        return "47%";
    }

    public String getTemperature(){
        return temperature;
    }

    public String getDate(){
        return date;
    }

    public String getRating(){
        return rating;
    }

    public int getId(){ return id; }

    public String getDose() { return dose; }

    public String toString(){
        return "LOG";
    }

    public void setId(int id){
        this.id = id;
    }

    public void setShotTime(String shotTime){
        this.shotTime = shotTime;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setShotWeight(String shotWeight) { this.shotWeight = shotWeight; }

    public void setBrewRatio(String brewRatio) { this.brewRatio = brewRatio; }

    public void setTemperature(String temperature) { this.temperature = temperature; }

    public void setRating(String rating) { this.rating = rating; }

    public void setDose(String dose) { this.dose = dose; }


    public void setDataFromArray(String[] data){

        Log.v("Test3", "test");
        if(data.length == 6){
            shotTime = data[0];
            shotWeight = data[1];
            temperature = data[2];
            date = data[3];
            rating = data[4];
            dose = data[5];
        }
    }
}
