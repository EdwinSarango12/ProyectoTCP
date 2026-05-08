package Cliente.test;
import Cliente.clases.Cliente;

import java.util.Scanner;

public class TestCliente {
    static void main(String[] args)throws Exception {
        String nombre = "";
        System.out.println("REGISTRO");
        System.out.println("Ingresa el nombre del empleado: ");
        Scanner scanner = new Scanner(System.in);
        nombre = scanner.nextLine();


        Cliente cliente = new Cliente();
        String respuesta = cliente.enviar(nombre, "INGRESO");
        System.out.println("Respuesta del servidor: " + respuesta);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

