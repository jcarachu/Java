
public class Example {
    public static void main (String[] args)
    {
        SimpleSubject simplesubject = new SimpleSubject();
        SimpleObserver simpleobserver = new SimpleObserver(simplesubject);
        simplesubject.setValue(80);
    }
}