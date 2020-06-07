import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;

public class TaxicabNumbers {
    /*  
        Four nested loops, brute-force implementation by
        looping though all combinations to find where 
        a³ + b³ = c³ + d³.
    */
    public List<long[]> methodA(long max)
    {
        List<long[]> list = new ArrayList<long[]>();
        long a3 = 0;
        for (long a = 1; (a3 = a * a * a) < max; a++)
        {
            long b3 = 0;
            for (long b = a; (b3 = b * b * b) <= max - a3; b++)
            {
                long ab3 = a3 + b3;
                long c3 = 0;
                for (long c = a + 1; (c3 = c * c * c) < ab3; c++)
                {
                    long d3 = 0;
                    for (long d = c; (d3 = d * d * d) <= ab3 - c3; d++)
                        if (ab3 == c3 + d3)
                            list.add(new long[]{a, b, c, d});
                }
            }
        }
        return list;
    }
    /*
        This is same as MethodA implementation, 
        but calculates d³ using Math.cbrt method, 
        instead of looping though all posibble numbers.
    */
    public List<long[]> methodB(long max)
    {
        List<long[]> list = new ArrayList<long[]>();
        long a3 = 0;
        for (long a = 1; (a3 = a * a * a) < max; a++)
        {
            long b3 = 0;
            for (long b = a; (b3 = b * b * b) <= max - a3; b++)
            {
                long ab3 = a3 + b3;
                long c3 = 0;
                for (long c = a + 1; (c3 = c * c * c) <= ab3; c++)
                {
                    double d3 = ab3 - c3;
                    long d = (long) Math.cbrt(d3);
                    if (d >= c && d != a && d != b && d3 == d * d * d)
                        list.add(new long[] {a, b, c, d});

                }
            }
        }
        return list;
    }
    
    /*
        This is the same as MethodB implementation,
        but calculates d³ using Math.pow method.
    */
    public List<long[]> methodC(long max)
    {
        List<long[]> list = new ArrayList<long[]>();
        long a3 = 0;
        for (long a = 1; (a3 = a * a * a) < max; a++)
        {
            long b3 = 0;
            for (long b = a; (b3 = b * b * b) <= max - a3; b++)
            {
                long ab3 = a3 + b3;
                long c3 = 0;
                for (long c = a + 1; (c3 = c * c *c) < ab3; c++)
                {
                    double d3 = ab3 - c3;
                    long d = (long) Math.round(Math.pow(d3, 1.0 / 3.0));
                    if (d >= c && d != a && d != b && d3 == d * d * d)
                        list.add(new long[] {a, b, c, d});
                }
            }
        }
        return list;
    }

    /*
        This implementation stores every combination of the
        the sum of a³ + b³ (key) and [a,b] pairs (value) in
        a hash table, and checks it the sum (key) already in
        the table.
    */
    public List<long[]> methodD(long max)
    {
        List<long[]> list = new ArrayList<long[]>();
        Hashtable<Object, long[]> table = new Hashtable<Object, long[]>();
        long a3 = 0;
        for (long a = 1; (a3 = a * a * a) < max; a++)
        {
            long b3 = 0;
            for (long b = a; (b3 = b * b * b) <= max - a3; b++)
            {
                long ab3 = a3 + b3;
                long[] previous = (long[]) table.get(ab3);
                if (previous == null)
                    table.put(ab3, new long[] {a, b});
                else if (previous[0] != 0)
                {
                    list.add(new long[] { previous[0], previous[1], a, b});
                    table.put(ab3, new long[] {0, 0});
                }

            }
        }
        return list;
    }

    public List<long[]> methodE(long max)
    {
        List<long[]> list = new ArrayList<long[]>();
        Map<Object, long[]> map = new HashMap<Object, long[]>();
        long a3 = 0;
        for(long a = 1; (a3 = a * a * a) < max; a++)
        {
            long b3 = 0;
            for (long b = a; (b3 = b * b * b) <= max - a3; b++)
            {
                long ab3 = a3 + b3;
                long[] previous = (long[]) map.get(ab3);
                if (previous == null)
                    map.put(ab3, new long[] {a, b});
                else if (previous[0] != 0)
                {
                    list.add(new long[] {previous[0], previous[1], a, b});
                    map.put(ab3, new long[] {0, 0});
                }
            }
        }
        return list;
    }

    public static void main(String[] args)
    {
        TaxicabNumbers taxicabnumbers = new TaxicabNumbers();
        System.out.println("MedthodA: "+Arrays.toString((taxicabnumbers.methodA(1729).get(0))));
        System.out.println("MedthodB: "+Arrays.toString((taxicabnumbers.methodB(1729).get(0))));
        System.out.println("MedthodC: "+Arrays.toString((taxicabnumbers.methodC(1729).get(0))));
        System.out.println("MedthodD: "+Arrays.toString((taxicabnumbers.methodD(1729).get(0))));
        System.out.println("MedthodE: "+Arrays.toString((taxicabnumbers.methodE(1729).get(0))));
    }
}