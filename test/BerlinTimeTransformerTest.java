import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BerlinTimeTransformerTest {

    private final BerlinTimeTransformer transformer = new BerlinTimeTransformer();


    @Test
    public void shouldGetClockRepresentationForOneSecond() throws Exception {

        assertThat(transformer.getSecondsRepresentation(0), is("Y"));
        assertThat(transformer.getSecondsRepresentation(1), is("O"));
        assertThat(transformer.getSecondsRepresentation(59), is("O"));

    }

    @Test
    public void shouldHaveFourLampsOnTheTopShelve() throws Exception {
        assertThat(transformer.getTopHours(0).length(), is(4));
    }

    @Test
    public void shouldLightALampEveryFiveHours() throws Exception {
        assertThat(transformer.getTopHours(0) , is("OOOO"));
        assertThat(transformer.getTopHours(7) , is("ROOO"));
        assertThat(transformer.getTopHours(13), is("RROO"));
        assertThat(transformer.getTopHours(23), is("RRRR"));

    }

    @Test
    public void shouldHaveFourLampsOnBottomRow() throws Exception {
        assertThat(transformer.getBottomHours(0).length(), is(4));
    }

    @Test
    public void shouldLightALampForEveryHourAfter5Hours() throws Exception {
        assertThat(transformer.getBottomHours(0), is("OOOO"));
        assertThat(transformer.getBottomHours(1), is("ROOO"));
        assertThat(transformer.getBottomHours(2), is("RROO"));
        assertThat(transformer.getBottomHours(3), is("RRRO"));
        assertThat(transformer.getBottomHours(13), is("RRRO"));
        assertThat(transformer.getBottomHours(20), is("OOOO"));
        assertThat(transformer.getBottomHours(21), is("ROOO"));

    }

    @Test
    public void shouldHaveElevenTopHourMinuteLamps() throws Exception {
        assertThat(transformer.getTopMinutes(0).length(), is(11));

    }

    @Test
    public void shouldLightRedLampsOnTheQuarterHours() throws Exception {
        assertThat(transformer.getTopMinutes(15).charAt(2), is('R'));
        assertThat(transformer.getTopMinutes(15).charAt(5), is('O'));

        assertThat(transformer.getTopMinutes(32).charAt(5), is('R'));
        assertThat(transformer.getTopMinutes(32).charAt(8), is('O'));
    }

    @Test
    public void shouldLightYellowLampsOnEveryFiveMinutesAfterTheQuarterHour() throws Exception {
        assertThat(transformer.getTopMinutes(5),  is("YOOOOOOOOOO"));
        assertThat(transformer.getTopMinutes(20), is("YYRYOOOOOOO"));
        assertThat(transformer.getTopMinutes(34), is("YYRYYROOOOO"));
        assertThat(transformer.getTopMinutes(51), is("YYRYYRYYRYO"));
        assertThat(transformer.getTopMinutes(56), is("YYRYYRYYRYY"));

    }

    @Test
    public void shouldHaveFourLampsInBottomMinuteRow() throws Exception {
        assertThat(transformer.getBottomMinutes(4).length(), is(4));
    }

    @Test
    public void shouldLightAYellowLampEveryMinuteOnAFiveMinuteRotation() throws Exception {
        assertThat(transformer.getBottomMinutes(10), is("OOOO"));
        assertThat(transformer.getBottomMinutes(16), is("YOOO"));
        assertThat(transformer.getBottomMinutes(27), is("YYOO"));
        assertThat(transformer.getBottomMinutes(3) , is("YYYO"));
        assertThat(transformer.getBottomMinutes(59) , is("YYYY"));
    }

    @Test
    public void shouldConvertTimeToBerlinClockTime() throws Exception {
        BerlinTime berlinTime = transformer.convertTimeToBerlinTime("16:37:16");
        assertThat(berlinTime.getSecond(), is("Y"));
        assertThat(berlinTime.getBottomMinutes(), is("YYOO"));
        assertThat(berlinTime.getTopMinutes(), is("YYRYYRYOOOO"));
        assertThat(berlinTime.getBottomHours(), is("ROOO"));
        assertThat(berlinTime.getTopHours(), is("RRRO"));

    }
}
