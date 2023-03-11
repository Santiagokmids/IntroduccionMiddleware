import com.zeroc.Ice.Current;

public class PrinterI implements Demo.Printer
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public int validateGUID(String guid, int n, Current current) {
        if(guid.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")) {

            n=generatePrime(n);
            System.out.println(ANSI_GREEN+guid+" VALID_REQUEST"+ANSI_RESET);

        } else {
            n=1;
            System.out.println(ANSI_RED+guid+" INVALID_REQUEST"+ANSI_RESET);
        }
        return n;
    }


    private boolean isPrime(int n){
        boolean prime=true;
        for(int i=2;i<n && prime;i++){
            if(n%i==0) {
                prime=false;
            }
        }
        return prime;
    }

    private int generatePrime(int n){
        n++;
        while(!isPrime(n)){
            n++;
        }
        return n;
    }
}