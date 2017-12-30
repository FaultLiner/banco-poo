package General;

import java.util.Random;
import java.security.SecureRandom;


public class Utilidades {


    public float generaAleatorio () {
        float resultado = -1;
        while (resultado == -1) {

            Random generaNumeroAleatorio1 = new Random();
            Random generaNumeroAleatorio2 = new Random();

            float num1 = generaNumeroAleatorio1.nextInt(100);
            float num2 = generaNumeroAleatorio2.nextInt(100);

            resultado = (((num1 + num2) - 100)/100);
        }
        return resultado;
    }
}
