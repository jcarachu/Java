import javax.swing.undo.StateEditable;

public class SoldOutState implements State {
    GumballMachine gumballmachine;

    public SoldOutState(GumballMachine gumballmachine)
    {
        this.gumballmachine = gumballmachine;
    }

    public void insertQuarter()
    {
        System.out.println("You can't insert a quarter, the machine is sold out.");
    }

    public void ejectQuarter()
    {
        System.out.println("You can't eject you haven't inserted a quarter yet.");
    }

    public void turnCrank()
    {
        System.out.println("You turned, but there are no gumballs.");
    }

    public void dispense()
    {
        System.out.println("No gumball dispensed");
    }

    public String toString()
    {
        return "sold out.";
    }

}