package md.usarb.cinema.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class is meant to transform {@link Date} to {@link LocalDate}
 *
 * @author Natalia Balan
 */
public class LocalDateTransformer  {

    /**
     * This method receives {@link Date} as parameter and transform it to  {@link LocalDate}
     *
     * @param date the date to transform
     * @return the local date
     */
    public static LocalDate getLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}