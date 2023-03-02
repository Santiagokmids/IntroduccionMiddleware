public class PrinterI implements Demo.Printer
{
    public void printString(String s, com.zeroc.Ice.Current current)
    {
        System.out.println(s);
    }

    public void validateGUID(String guid, int n, com.zeroc.Ice.Current current){
        if(guid.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")) {

            while(!isPrime(n)){
                n++;
            }
        } else {
            n=1;
        }
        System.out.println(n+"");
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

}