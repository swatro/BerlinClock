
public class BerlinTime {
    private String second;
    private String bottomMinutes;
    private String topMinutes;
    private String bottomHours;
    private String topHours;

    public BerlinTime(String seconds, String bottomMinutes, String topMinutes, String bottomHours, String topHours) {
        this.second = seconds;
        this.bottomMinutes = bottomMinutes;
        this.topMinutes = topMinutes;
        this.bottomHours = bottomHours;
        this.topHours = topHours;
    }

    public String getSecond() {
        return second;
    }

    public String getBottomMinutes() {
        return bottomMinutes;
    }

    public String getTopMinutes() {
        return topMinutes;
    }

    public String getBottomHours() {
        return bottomHours;
    }

    public String getTopHours() {
        return topHours;
    }

}
