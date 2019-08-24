
public class GumballMachineTestDrive {
    public static void main(String[] args)
    {
        GumballMachine gumballmachine = new GumballMachine(5);
        System.out.println(gumballmachine);
        gumballmachine.insertQuarter();
        gumballmachine.turnCrank();
        System.out.println(gumballmachine);
        gumballmachine.insertQuarter();
        gumballmachine.turnCrank();
        gumballmachine.insertQuarter();
        gumballmachine.turnCrank();
        System.out.println(gumballmachine);
    }
}