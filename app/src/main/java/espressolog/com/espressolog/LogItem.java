package espressolog.com.espressolog;

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
        return brewRatio;
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

    public void setDataFromArray(String[] data){

        if(data.length == 6){
            shotTime = data[0];
            shotWeight = data[1];
            temperature = data[2];
            date = data[3];
            brewRatio = data[4];
            rating = data[5];
        }
    }
}
