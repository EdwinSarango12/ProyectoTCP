package Cliente.clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    // Se añade un parámetro para la IP en caso de ser necesario, usando "127.0.0.1" por defecto
    private String ipServidor = "127.0.0.1";

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public String enviar(String nombre, String tipoTimbre) throws Exception {
        Socket cliente = new Socket(ipServidor, 2025);

        InputStream in = cliente.getInputStream();
        OutputStream out = cliente.getOutputStream();

        DataOutputStream dos = new DataOutputStream(out);
        // Enviamos el mensaje con el formato esperado por el servidor
        dos.writeUTF(nombre + "|" + tipoTimbre);

        DataInputStream dis = new DataInputStream(in);
        String respuesta = dis.readUTF();
        
        cliente.close();
        
        return respuesta;
    }
}
