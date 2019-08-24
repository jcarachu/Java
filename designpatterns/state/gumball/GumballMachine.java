
public class GumballMachine {
    State soldoutstate;
    State noquarterstate;
    State hasquarterstate;
    State soldstate;
    State state = soldoutstate;
    int count = 0;

    public GumballMachine(int numbergumballs)
    {
        soldoutstate = new SoldOutState(this);
        noquarterstate = new NoQuarterState(this);
        hasquarterstate = new HasQuarterState(this);
        soldstate = new SoldState(this);
        this.count = numbergumballs;
        if (numbergumballs > 0)
            state = noquarterstate;
    }

    public void insertQuarter()
    {
        state.insertQuarter();
    }

    public void ejectQuarter()
    {
        state.ejectQuarter();
    }

    public void turnCrank()
    {
        state.turnCrank();
        state.dispense();
    }

    void releaseBall()
    {
        System.out.println("A gumball comes rolling out the slot ...");
        if (count != 0)
            count = count - 1;
    }

    int getCount()
    {
        return count;
    }

    void refill(int count)
    {
        this.count = count;
        state = noquarterstate;
    }

    void setState(State state)
    {
        this.state = state;
    }

    public State getState()
    {
        return state;
    }

    public State getSoldOutState()
    {
        return soldoutstate;
    }

    public State getNoQuarterState()
    {
        return noquarterstate;
    }

    public State getHasQuarterState()
    {
        return hasquarterstate;
    }

    public State getSoldState()
    {
        return soldstate;
    }

    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append("\nJulian's Mighty Gumball, Inc");
        result.append("\nJava-enabled Stading Gumball Model #2019");
        result.append("\nInventory: " + count + " gumball");
        if (count != 1)
            result.append("s");
        result.append("\n");
        result.append("Machine is " + state + "\n");
        return result.toString();

    }
}