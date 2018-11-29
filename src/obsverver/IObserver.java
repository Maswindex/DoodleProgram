/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the observer methods
 */
package obsverver;

/**
 * Observer Interface
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public interface IObserver
{
    /**
     * updates observables
     *
     * @param observable object being observed
     * @param args info
     */
    //observable is the object we are observing
    void update(Observable observable, Object... args);
}
