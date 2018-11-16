import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilsTest {

    @Test
    public void getNextWorkingDayTest(){
        Calendar nextWorkingDay = DateUtils.getNextWorkingDay(new GregorianCalendar(2018, 3,28));
        Assert.assertEquals(2018-3-30, nextWorkingDay);
    }
}
