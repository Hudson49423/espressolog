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
        this.rating = rating;
    }

    public String getShotTime(){
        return shotTime;
    }

    public String getShotWeight(){
        return shotWeight;
    }

    public String getBrewRatio(){
        if (brewRatio == null) {
            return calculateBrewRatio();
        }
        else {
            return brewRatio;
        }
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

    private String calculateBrewRatio(){
        if ((dose != null) && (shotWeight != null)) {
            try {
                double doseInt = Double.parseDouble(dose);
                double shotWeightInt = Double.parseDouble(shotWeight);
                double brewRatioNoRound = doseInt / shotWeightInt;
                double percent = brewRatioNoRound * 100;
                int brewRatioInt = (int) percent;
                String brewRatio = "" + brewRatioInt + "%";
                return brewRatio;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return "47%";
    }


    public void setDataFromArray(String[] data){

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
