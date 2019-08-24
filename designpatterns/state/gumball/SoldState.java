
public class SoldState implements State {
    GumballMachine gumballmachine;

    public SoldState(GumballMachine gumballmachine)
    {
        this.gumballmachine = gumballmachine;
    }

    public void insertQuarter()
    {
        System.out.println("Please wait, we're already giving you a gumball.");
    }

    public void ejectQuarter()
    {
        System.out.println("Sorry, you already turned the crank.");
    }

    public void turnCrank()
    {
        System.out.println("Turning twice doesn't get you another gumball.");
    }

    public void dispense()
    {
        gumballmachine.releaseBall();
        if (gumballmachine.getCount() > 0)
        {
            gumballmachine.setState(gumballmachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs.");
            gumballmachine.setState(gumballmachine.getSoldOutState());
        }
    }

    public String toString()
    {
        return "dispensing a gumball.";
    }
}