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
    public String getFecha(String nombre){
        Date fecha = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return formato.format(fecha);
    }


    public void servicio(int puerto) throws Exception{
        ServerSocket servidor = new ServerSocket(2025);
        System.out.println("Servidor iniciado en el puerto "+puerto);

        while (true){
            System.out.println("Esperando conexiones...");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");
            InputStream in = cliente.getInputStream();
            OutputStream out = cliente.getOutputStream();

            //leer el nombre del empleado
            DataInputStream dis = new DataInputStream(in);
            String nombre = dis.readUTF();
            if(nombre.equals("Edwin")) break;
            String resultado = getFecha(nombre);
            System.out.println("Mensaje recibido exitosamente");

            //Devolver la respuesta al cliente
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(resultado);
            //      dos.write(resultado.getBytes());
            System.out.println("Respuesta enviada al cliente");
            cliente.close();
        }
    }
}
