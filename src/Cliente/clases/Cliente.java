package Cliente.clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public void enviar(String nombre) throws Exception {
        Socket cliente = new Socket("172.31.116.69", 2025);

        InputStream in = cliente.getInputStream();
        OutputStream out = cliente.getOutputStream();


        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(nombre);

        DataInputStream dis = new DataInputStream(in);
        String fecha = dis.readUTF();
        System.out.println("Fecha de ingreso: " + fecha+", el empleado: "+nombre);
        cliente.close();
    }
}
