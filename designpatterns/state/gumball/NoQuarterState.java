
public class NoQuarterState implements State {
    GumballMachine gumballmachine;

    public NoQuarterState(GumballMachine gumballmachine)
    {
        this.gumballmachine = gumballmachine;
    }

    public void insertQuarter()
    {
        System.out.println("You inserted a quarter.");
        gumballmachine.setState(gumballmachine.getHasQuarterState());
    }

    public void ejectQuarter()
    {
        System.out.println("You haven't inserted a quarter.");
    }

    public void turnCrank()
    {
        System.out.println("You turned, but there's no quarter.");
    }

    public void dispense()
    {
        System.out.println("You need to pay first.");
    }

    public String toString()
    {
        return "waiting for quarter";
    }
}