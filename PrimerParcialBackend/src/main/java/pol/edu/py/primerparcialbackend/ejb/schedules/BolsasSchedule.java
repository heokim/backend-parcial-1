package pol.edu.py.primerparcialbackend.ejb.schedules;

import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import pol.edu.py.primerparcialbackend.ejb.BolsasDAO;
import pol.edu.py.primerparcialbackend.model.Bolsas;

@Singleton
public class BolsasSchedule {

    @Inject
    BolsasDAO bolsasDAO;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void actualizarEstadoBolsas() {
        Date hoy = new Date();

        // aquí puedes incluir el código que quieres que se ejecute cada 15 minutos
        List<Bolsas> bolsas = bolsasDAO.findOnlyActive();

        int n = 0;

        for (Bolsas bolsa : bolsas) {
            if (hoy.after(bolsa.getFechaDeCaducidadDePuntaje())) { // vencio la bolsa
                bolsa.setEstado(false);
                bolsasDAO.edit(bolsa);
                System.out.println("La bolsa con id: " + bolsa.getBolsaId() + " a vencido");
                n++;
            }
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Correindo Rutina \"actualizarEstadoBolsas()\"");
        System.out.println("----------------------------------------------");
        System.out.println("Cantidad de Bolsas Activas: " + bolsas.size());
        System.out.println("Cantidad de bolsas vencidas: " + n);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
