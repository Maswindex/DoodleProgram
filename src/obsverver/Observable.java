/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the observable methods
 */
package obsverver;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for observable objects
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class Observable
{
    private Set<IObserver> observers;

    /**
     * Constructor for an observable object
     */
    public Observable()
    {
        observers = new HashSet<>();
    }

    /**
     * creates new observer
     *
     * @param observer thing becoming observer
     */
    public void addObserver(IObserver observer)
    {
        observers.add(observer);
    }

    /**
     * notifies any observer objects
     *
     * @param args info
     */
    public void notifyObservers(Object... args)
    {
        for (IObserver observer : observers)
        {
            observer.update(this, args);
        }
    }

    @Override
    public String toString()
    {
        return "Observable{" +
                "observers=" + observers +
                '}';
    }
}
