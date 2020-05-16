package Command;


public class CommandeStop implements Commande {
    @Override
    public void execute() {
        System.out.println("STOPPING THE PROGRAM");
        System.exit(1);
    }
}
