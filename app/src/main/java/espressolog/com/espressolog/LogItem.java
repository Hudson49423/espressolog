package espressolog.com.espressolog;

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
    private String volume;

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

    public String getVolume(){ return volume;}

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

    public void setVolume(String volume) { this.volume = volume; }

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
        return null;
    }

    public ArrayList<String[]> getDataInArray() {
        ArrayList<String[]> returnArray = new ArrayList<>();

        if (date != null) {
            String[] a = {"Date", date};
            returnArray.add(a);

        }

        if (shotTime != null) {
            String[] a = {"Shot time", shotTime};
            returnArray.add(a);

        }
        if (shotWeight != null) {
            String[] a = {"Shot weight", shotWeight};
            returnArray.add(a);

        }
        if (temperature != null) {
            String[] a = {"Temperature", temperature};
            returnArray.add(a);

        }
        if (rating != null) {
            String[] a = {"Rating", rating};
            returnArray.add(a);

        }
        if (dose != null) {
            String[] a = {"Dose", dose};
            returnArray.add(a);

        }
        if (grind != null) {
            String[] a = {"Grind Setting", grind};
            returnArray.add(a);

        }
        if (notes != null) {
            String[] a = {"Notes", notes};
            returnArray.add(a);

        }
        if (volume != null) {
            String[] a = {"Volume", volume + "ml"};
            returnArray.add(a);

        }
        return returnArray;
    }
}