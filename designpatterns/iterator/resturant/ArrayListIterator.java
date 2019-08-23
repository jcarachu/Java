import java.util.ArrayList;

public class ArrayListIterator implements Iterator {
    ArrayList<MenuItem> items;
    int postion = 0;

    public ArrayListIterator(ArrayList<MenuItem> items)
    {
        this.items = items;
    }

    public MenuItem next()
    {
        MenuItem item = items.get(postion);
        postion = postion + 1;
        return item;
    }

    public boolean hasNext()
    {
        if (postion >= items.size())
            return false;
        else
            return true;
    }
}