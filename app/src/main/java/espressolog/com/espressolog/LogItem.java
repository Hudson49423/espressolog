package espressolog.com.espressolog;

import android.util.Log;

import java.util.ArrayList;

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
    private String grind;
    private String notes;

    public LogItem() {
    }

    public String getShotTime(){
        return shotTime;
    }

    public String getShotWeight(){
        return shotWeight;
    }

    public String getBrewRatio(){
        return calculateBrewRatio();
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

    public String getGrind(){
        return grind;
    }

    public String getNotes() {
        return notes;
    }

    public String getDose() { return dose; }

    public String toString(){
        return "Id: " + id;
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

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setGrind(String grind) {
        this.grind = grind;
    }

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

    public ArrayList<String[]> getDataInArray() {
        ArrayList<String[]> returnArray = new ArrayList<>();

        String[] a = {"Shot time", shotTime};
        String[] a1 = {"Shot weight", shotWeight};
        String[] a2 = {"Temperature", temperature};
        String[] a3 = {"Rating", rating};
        String[] a4 = {"Dose", dose};
        String[] a5 = {"Grind Setting", grind};
        String[] a6 = {"Notes", notes};

        returnArray.add(a);
        returnArray.add(a1);
        returnArray.add(a2);
        returnArray.add(a3);
        returnArray.add(a4);
        returnArray.add(a5);
        returnArray.add(a6);


        return returnArray;
    }
}
