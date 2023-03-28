package pol.edu.py.primerparcialbackend.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author heokim
 */
public class DateUtils {

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

}
