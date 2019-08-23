
public class MenuTestDrive {
    public static void main (String args[])
    {
        PancakeHouseMenu pancakehousemenu = new PancakeHouseMenu();
        DinerMenu dinermenu = new DinerMenu();
        Waitress waitress = new Waitress(pancakehousemenu, dinermenu);
        waitress.printMenu();
    }
    
}