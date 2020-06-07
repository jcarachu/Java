package com.test;

import java.util.List;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;

/*
    Taxicab Numbers.
    A taxicab number is an integer that can be expressed as the sum of two cubes of
    integers in two different ways: a^3 + b^3.For example: 1729 = 9^3+10^3 = 1^3 + 12^3.
    Design an algorithm to find all taxicab numbers with a, b, c and d less than N.
 */
public class TaxicabNumbers {
    class Taxicab implements Comparable<Taxicab> {
        int n1;
        int n2;
        int cube;
    
        Taxicab(int n1, int n2)
        {
            this.n1 = n1;
            this.n2 = n2;
            this.cube = n1 * n1 * n1 + n2 * n2 * n2;
        }
    
        @Override
        public int compareTo(Taxicab that)
        {
            if (that.cube > this.cube)
                return -1;
            if (that.cube < this.cube)
                return 1;
            return 0;
        }
    
        @Override
        public boolean equals(Object o)
        {
            if (o instanceof Taxicab)
                if (((Taxicab) o).compareTo(this) == 0)
                    return true;
			return false;
        }
    
        @Override
        public String toString()
        {
            return "number: " + cube + "(" + n1 + ", " + n2 + ")";
        }
    }

    public void findTaxiNumber(int N)
    {
        // valid taxi cab numbers
        MinPQ<Taxicab> candidates = new MinPQ<Taxicab>();
        List<Taxicab> list = new ArrayList<Taxicab>();
        for (int i = 1; i < N; i++)
        {
            for (int j = i; j*j*j <= N - i*i*i; j++)
            {
                Taxicab t = new Taxicab(i, j);
                if (candidates.size() < N)
                    candidates.insert(t);
                else {
                    Queue<Taxicab> temp = new Queue<Taxicab>();
                    Taxicab min = candidates.delMin();
                    while (candidates.min().equals(min))
                    {
                        list.add(candidates.min());
                        list.add(min);
                        temp.enqueue(candidates.delMin());
                    }
                        
                    if (!t.equals(min))
                        candidates.insert(t);
                    else
                    {
                        list.add(t);
                        temp.enqueue(t);
                    }
   
                }
            }
        } 
        for (Taxicab taxi: list)
            System.out.println(taxi);
    }
    
    public static void main(String[] args)
    {
        TaxicabNumbers p = new TaxicabNumbers();
        p.findTaxiNumber(1729);
    }
}