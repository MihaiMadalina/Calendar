import java.util.*;

public class DateUtils {

    public static void main(String[] args) {

        Scanner time = new Scanner(System.in);

        System.out.print("Type day: ");
        int day = time.nextInt();
        System.out.print("Type month: ");
        int month = time.nextInt();
        System.out.print("Type year: ");
        int year = time.nextInt();

        Calendar x = new GregorianCalendar(year, month - 1, day);

        boolean isBusinessDay = isBusinessDay(x);
        System.out.println(isBusinessDay);
        Calendar y = getNextWorkingDay(x);
        System.out.println("Next working day is: " + y.get(Calendar.DAY_OF_MONTH) + "/" + y.get(Calendar.MONTH) + "/" + y.get(Calendar.YEAR));
    }

    public static boolean isBusinessDay(Calendar calendar) {

        // check if Easter Sunday
        if (isEasterSunday(calendar)) {
            return false;
        }

        // check if Easter Monday
        if (isEasterMonday(calendar)) {
            return false;
        }

        // check if Whitsuntide
        if (isWhitsuntide(calendar)) {
            return false;
        }
        // check if weekend
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }

        // check if 1st, 2nd or 24th of January
        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY
                && calendar.get(Calendar.DAY_OF_MONTH) == 1 || calendar.get(Calendar.DAY_OF_MONTH) == 2
                || calendar.get(Calendar.DAY_OF_MONTH) == 24) {
            return false;
        }

        // check if 1st of May
        if (calendar.get(Calendar.MONTH) == Calendar.MAY
                && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return false;
        }
        // check if 1st of July
        if (calendar.get(Calendar.MONTH) == Calendar.JULY
                && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return false;
        }

        // check if 15th of August
        if (calendar.get(Calendar.MONTH) == Calendar.AUGUST
                && calendar.get(Calendar.DAY_OF_MONTH) == 15) {
            return false;
        }

        // check if 30th of November
        if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER
                && calendar.get(Calendar.DAY_OF_MONTH) == 30) {
            return false;
        }

        //  check if 1st, 25th or 26th of December
        if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER
                && calendar.get(Calendar.DAY_OF_MONTH) == 1 || calendar.get(Calendar.DAY_OF_MONTH) == 25
                || calendar.get(Calendar.DAY_OF_MONTH) == 26) {
            return false;
        }

        // check if Christmas
        if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER
                && calendar.get(Calendar.DAY_OF_MONTH) == 25) {
            return false;
        }

        // check if 4th of July
        if (calendar.get(Calendar.MONTH) == Calendar.JULY
                && calendar.get(Calendar.DAY_OF_MONTH) == 4) {
            return false;
        }

        // IF NOTHING ELSE, IT'S A BUSINESS DAY
        return true;
    }

    // check if Orthodox Easter
    public static Calendar getOrthodoxEaster(int year) {
        Calendar dof = Calendar.getInstance();

        int r1 = year % 4;
        int r2 = year % 7;
        int r3 = year % 19;
        int r4 = (19 * r3 + 15) % 30;
        int r5 = (2 * r1 + 4 * r2 + 6 * r4 + 6) % 7;
        int days = r5 + r4 + 13;

        if (days > 39) {
            days = days - 39;
            dof.set(year, 4, days);
        } else if (days > 9) {
            days = days - 9;
            dof.set(year, 3, days);
        } else {
            days = days + 22;
            dof.set(year, 2, days);
        }
        return dof;
    }

    public static boolean isEasterSunday(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        Calendar orthodoxEaster = getOrthodoxEaster(year);
        if (calendar.equals(orthodoxEaster)) {
            return true;
        }
        return false;
    }

    public static boolean isEasterMonday(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        Calendar orthodoxEaster = getOrthodoxEaster(year);
        orthodoxEaster.add(Calendar.DAY_OF_MONTH, 1);
        if (calendar.equals(orthodoxEaster)) {
            return true;
        }
        return false;
    }

    public static boolean isWhitsuntide(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        Calendar orthodoxEaster = getOrthodoxEaster(year);
        orthodoxEaster.add(Calendar.DAY_OF_MONTH, 50);
        if (calendar.equals(orthodoxEaster)) {
            return true;
        }
        return false;
    }

    public static Calendar getNextWorkingDay(Calendar calendar) {

        while (!isBusinessDay(calendar)) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return calendar;
    }
}