package Servidor.clases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroDiario {
    private boolean ingreso = false;
    private boolean salidaAlmuerzo = false;
    private boolean entradaAlmuerzo = false;
    private boolean salida = false;

    public String registrarTimbre(String tipo) {
        String fecha = getFecha();
        switch (tipo) {
            case "INGRESO":
                if (ingreso) return "Error: Ya ha registrado su ingreso hoy.";
                ingreso = true;
                return "Ingreso registrado con éxito a las " + fecha;

            case "SALIDA_ALMUERZO":
                if (!ingreso) return "Error: Debe registrar su ingreso primero.";
                if (salidaAlmuerzo) return "Error: Ya ha registrado su salida al almuerzo hoy.";
                salidaAlmuerzo = true;
                return "Salida al almuerzo registrada con éxito a las " + fecha;

            case "ENTRADA_ALMUERZO":
                if (!salidaAlmuerzo) return "Error: Debe registrar su salida al almuerzo primero.";
                if (entradaAlmuerzo) return "Error: Ya ha registrado su entrada del almuerzo hoy.";
                entradaAlmuerzo = true;
                return "Entrada del almuerzo registrada con éxito a las " + fecha;

            case "SALIDA":
                if (!ingreso) return "Error: Debe registrar su ingreso primero.";
                // Opcional: Requerir los pasos del almuerzo
                if (salidaAlmuerzo && !entradaAlmuerzo) return "Error: Registró salida al almuerzo, debe registrar su entrada del almuerzo primero.";
                if (salida) return "Error: Ya ha registrado su salida hoy.";
                salida = true;
                return "Salida registrada con éxito a las " + fecha;

            default:
                return "Error: Tipo de timbre no válido.";
        }
    }

    private String getFecha() {
        Date fecha = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(fecha);
    }
}
