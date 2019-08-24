
public class HasQuarterState implements State {
    GumballMachine gumballmachine;

    public HasQuarterState(GumballMachine gumballmachine)
    {
        this.gumballmachine = gumballmachine;
    }

    public void insertQuarter()
    {
        System.out.println("You can't insert another quarter.");
    }

    public void ejectQuarter()
    {
        System.out.println("Quarter returned.");
        gumballmachine.setState(gumballmachine.getNoQuarterState());
    }

    public void turnCrank()
    {
        System.out.println("You turned...");
        gumballmachine.setState(gumballmachine.getSoldState());
    }

    public void dispense()
    {
        System.out.println("No gumball dispensed.");
    }

    public String toString()
    {
        return "waiting for turn of crank.";
    }
}