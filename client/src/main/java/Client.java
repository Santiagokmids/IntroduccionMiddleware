import Demo.PrinterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Inet4Address;
import java.util.UUID;

public class Client {
    public static void main(String[] args) {

        try (Communicator communicator = Util.initialize(args, "client.cfg")) {
            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            PrinterPrx printer = PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invalid proxy");
            }
            try {

                String hostname = Inet4Address.getLocalHost().getHostName();

                if(args.length == 0){
                    System.out.println("Debe ingresar al menos un numero entero positivo y opcionalmente un nombre de archivo de texto local \n" +
                            "Este software ha sido creado por los siguientes autores:\n" +
                            "Angelica Corrales Quevedo - A00367954\n" +
                            "Santiago Trochez Velasco - A00369326 \n");

                }else {

                    //System.out.println("\u001B[32m"+guid); --> Coloca el texto verde

                    int number =  Integer.parseInt(args[0]);

                    if(number > 1){
                        String guid="";
                        if(args.length == 1){
                            guid = UUID.randomUUID().toString();
                           // printer.printString(hostname + " says: " + "Hello World");

                        }else{

                            FileReader fr = new FileReader("./"+args[1]);
                            BufferedReader br = new BufferedReader(fr);
                            guid = br.readLine();


                        }

                        if(!guid.isEmpty()){

                            int prime=printer.validateGUID(guid,number);
                            System.out.println(prime+"");
                        }

                    } else {
                        System.out.println("El numero debe ser mayor a 1");

                    }

                }

            }catch (FileNotFoundException fne){
                System.out.println("El archivo no fue encontrado");
            }
            catch(NumberFormatException nfe){
                System.out.println("Debe ingresar un numero como primer parametro");
            }
            catch (Exception e) {
                // TODO: handle exception

            }
        }

    }


}