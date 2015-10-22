import org.apache.commons.lang.StringUtils;

public class BerlinTimeTransformer {

    private final int NUMBER_OF_LAMPS_IN_TOP_ROW = 4;
    private final int LAMP_IN_TOP_ROW_HOUR_REPRESENTATION = 5;
    private final int RED_LAMP_TOP_ROW_MINUTE_REPRESENTATION = 15;
    private final int YELLOW_LAMP_TOP_ROW_MINUTE_REPRESENTATION = 5;
    private final int NUMBER_OF_LAMPS_TOP_MINUTE_ROW = 11;
    private final int NUMBER_OF_LAMPS_IN_BOTTOM_MINUTE_ROW = 4;
    private final int YELLOW_LAMP_BOTTOM_ROW_MINUTE_REPRESENTATION = 5;
    private final int YELLOW_LAMP_SECOND_REPRESENTATION = 2;
    private final int REGULAR_TIME_HOUR_INDEX = 0;
    private final int REGULAR_TIME_MINUTE_INDEX = 1;
    private final int REGULAR_TIME_SECOND_INDEX = 2;

    public String getSecondsRepresentation(int seconds) {
        if (seconds % YELLOW_LAMP_SECOND_REPRESENTATION == 0)
            return LampState.YELLOW.toString();
        return LampState.OFF.toString();
    }

    public String getTopHours(int hour) {
        int numberOfOnLamps = Math.floorDiv(hour, LAMP_IN_TOP_ROW_HOUR_REPRESENTATION);
        return createLampString(numberOfOnLamps, NUMBER_OF_LAMPS_IN_TOP_ROW, LampState.RED);
    }

    public String getBottomHours(int hour) {
        int numberOfOnLamps = hour % LAMP_IN_TOP_ROW_HOUR_REPRESENTATION;
        return createLampString(numberOfOnLamps, NUMBER_OF_LAMPS_IN_TOP_ROW, LampState.RED);
    }

    public String getBottomMinutes(int minutes) {
        int numberOfYellowLamps = minutes % YELLOW_LAMP_BOTTOM_ROW_MINUTE_REPRESENTATION;
        return createLampString(numberOfYellowLamps, NUMBER_OF_LAMPS_IN_BOTTOM_MINUTE_ROW, LampState.YELLOW);
    }

    public String getTopMinutes(int minutes) {
        int numberOfRedLamps = Math.floorDiv(minutes, RED_LAMP_TOP_ROW_MINUTE_REPRESENTATION);
        String quarterHourRepresentation = LampState.YELLOW.toString() + LampState.YELLOW.toString() + LampState.RED.toString();
        int numberOfAdditionalYellowLamps = Math.floorDiv(minutes, YELLOW_LAMP_TOP_ROW_MINUTE_REPRESENTATION) - (quarterHourRepresentation.length()*numberOfRedLamps);

        String onLampString = StringUtils.repeat(quarterHourRepresentation, numberOfRedLamps) + StringUtils.repeat(LampState.YELLOW.toString(), numberOfAdditionalYellowLamps);
        return StringUtils.rightPad(onLampString, NUMBER_OF_LAMPS_TOP_MINUTE_ROW, LampState.OFF.toString());
    }

    public BerlinTime convertTimeToBerlinTime(String regularTime) {
        String[] timeBlocks = StringUtils.splitByWholeSeparator(regularTime, ":");
        int hours = Integer.parseInt(timeBlocks[REGULAR_TIME_HOUR_INDEX]);
        int minutes = Integer.parseInt(timeBlocks[REGULAR_TIME_MINUTE_INDEX]);
        int seconds = Integer.parseInt(timeBlocks[REGULAR_TIME_SECOND_INDEX]);

        return new BerlinTime(this.getSecondsRepresentation(seconds),
                this.getBottomMinutes(minutes),
                this.getTopMinutes(minutes),
                this.getBottomHours(hours),
                this.getTopHours(hours));
    }

    private String createLampString(int numberOfOnLamps, int totalNumberOfLamps, LampState onLampState) {
        String onLamps = StringUtils.repeat(onLampState.toString(), numberOfOnLamps);
        return StringUtils.rightPad(onLamps, totalNumberOfLamps, LampState.OFF.toString());
    }
}
