package tools;

public class newTiket implements Runnable {
    public static boolean ff=true;
    
    @Override
    public void run() {
        ff=true;
        Validation validation=new Validation();
        while (!validation.checkId(646372463)){ }
        while (!validation.checkName());
        while (!validation.creatCoordinates());
        while (!validation.checkPrice());
        while (!validation.checkDiscount());
        while (!validation.setTykeTipe());
        while (!validation.checkNameVenue());
        while (!validation.checkCapacityVenue());
        while (!validation.setVenueType());
        while (!validation.makeLocation());
        while (!validation.makeAdress());
        validation.makeTiket();
        CommandRead.blockingCommandBoolean=false;



    }
}
