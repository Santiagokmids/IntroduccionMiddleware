import Demo.PrinterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {

        try (Communicator communicator = Util.initialize(args, "client.cfg")) {
            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            PrinterPrx printer = PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invalid proxy");
            }
            validateArgs(args, printer);

        }catch (ConnectionRefusedException e){
            System.out.println("No se pudo establecer la conexion con el servidor");
        }

    }
    public static void validateArgs(String[] args, PrinterPrx printer){
        try {
            if(args.length == 0){
                noArgs();
            }else {

                int number =  Integer.parseInt(args[0]);

                if(number > 1){
                    String guid=getGUID(args);
                    if(!guid.isEmpty()) {
                        int prime = printer.validateGUID(guid, number);
                        System.out.println(prime + "");
                    }

                } else {
                    System.out.println("El numero debe ser mayor a 1");
                }
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("Debe ingresar un numero como primer parametro");
        }
    }

    public static void noArgs(){
        System.out.println("Debe ingresar al menos un numero entero positivo y opcionalmente un nombre de archivo de texto local \n" +
                "Este software ha sido creado por los siguientes autores:\n" +
                "Angelica Corrales Quevedo - A00367954\n" +
                "Santiago Trochez Velasco - A00369326 \n");
    }

    public static String getGUID(String[] args){
        String guid="";
        try {
            if (args.length == 1) {
                guid = UUID.randomUUID().toString();

            } else {

                FileReader fr = new FileReader("./" + args[1]);
                BufferedReader br = new BufferedReader(fr);
                guid = br.readLine();

            }
        }catch (FileNotFoundException fne){
            System.out.println("El archivo no fue encontrado");
        }catch (Exception e) {
            // TODO: handle exception

        }
        return guid;
    }

}