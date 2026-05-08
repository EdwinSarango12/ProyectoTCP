package Servidor.clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {
    // Mapa para mantener el estado de cada empleado por nombre
    private java.util.Map<String, RegistroDiario> registros = new java.util.HashMap<>();

    public void servicio(int puerto) throws Exception{
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor iniciado en el puerto "+puerto);

        while (true){
            System.out.println("Esperando conexiones...");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");
            InputStream in = cliente.getInputStream();
            OutputStream out = cliente.getOutputStream();

            //leer el nombre del empleado y el tipo de timbre
            DataInputStream dis = new DataInputStream(in);
            String mensaje = dis.readUTF();
            
            String resultado = "";
            if (mensaje.equals("SALIR_SERVIDOR")) {
                System.out.println("Apagando servidor...");
                cliente.close();
                break;
            }

            // El formato esperado es "nombre|TIPO_TIMBRE"
            String[] partes = mensaje.split("\\|");
            if (partes.length == 2) {
                String nombre = partes[0];
                String tipoTimbre = partes[1];
                
                // Obtener o crear el registro diario para este empleado
                registros.putIfAbsent(nombre, new RegistroDiario());
                RegistroDiario registro = registros.get(nombre);
                
                // Validar y registrar
                resultado = registro.registrarTimbre(tipoTimbre);
                System.out.println("Empleado: " + nombre + " - Acción: " + tipoTimbre + " - Resultado: " + resultado);
            } else {
                resultado = "Error: Formato de mensaje incorrecto.";
                System.out.println(resultado);
            }

            //Devolver la respuesta al cliente
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(resultado);
            System.out.println("Respuesta enviada al cliente");
            cliente.close();
        }
        servidor.close();
    }
}
