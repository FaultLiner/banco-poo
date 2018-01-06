package Mensajes;

import Bolsa.Empresa;

import java.util.HashSet;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean efectuada;

    public MensajeRespuestaActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<Empresa> empresasActualizadas, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion, empresasActualizadas);
        this.efectuada = efectuada;
    }



/*import java.util.GregorianCalendar;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean operacionRealizada;

    public MensajeRespuestaActualizacion(int identificador, GregorianCalendar fechaSolicitud, boolean operacionRealizada) {
        super(identificador, fechaSolicitud);
        this.operacionRealizada = operacionRealizada;
    }

    private String cadenaOperacionRealizada() {
        return String.valueOf(this.operacionRealizada);
    }

    public String codificar() {
        return cadenaIdentificador() + "|" + cadenaFechaSolicitud() + "|" + cadenaOperacionRealizada();
    }
*/
}
