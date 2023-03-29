package pol.edu.py.primerparcialbackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author heokim
 */
public class DateUtils {

    private static final Logger LOG = Logger.getLogger(DateUtils.class.getName());

    /**
     * Sumar dias a una fecha
     *
     * @param fecha Fecha
     * @param dias Cantidad de dias que se quiere sumar(acepta negativos)
     * @return Nueva fecha con los dias sumado
     */
    public static Date sumarFechaYDias(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de dias a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    /**
     * Casteas de String a Date segun el formato mencionado
     *
     * @param fechaString La fecha en formato de texto
     * @param formato El formato que tiene la fechaString
     * @return Fecha como objeto java.Util.Date;
     */
    public static Date stringToDate(String fechaString, String formato) {
        SimpleDateFormat f = new SimpleDateFormat(formato);
        try {
            return f.parse(fechaString);
        } catch (ParseException e) {
            LOG.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

}
