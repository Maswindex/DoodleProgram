/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds the shape model objects
 */

package model;

import model.shapes.AbstractShape;
import obsverver.Observable;

import java.util.List;
import java.util.Stack;

/**
 * Defines shapes for the model in MVC
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class ShapeVault extends Observable
{
    private Stack<AbstractShape> shapes, undo;

    /**
     * ShapeVault constructor
     */
    public ShapeVault()
    {
        shapes = new Stack<>();
        undo = new Stack<>();
    }

    /**
     * removes last shape on stack
     */
    public void undo()
    {
        if (!shapes.empty())
        {
            undo.push(shapes.pop());
        }

        notifyObservers();
    }

    /**
     * re-applies recent shape to stack
     */
    public void redo()
    {
        if (!undo.empty())
        {
            shapes.push(undo.pop());
        }

        notifyObservers();
    }

    /**
     * adds shape to drawPane
     *
     * @param shape shape object
     */
    public void addShape(AbstractShape shape)
    {
        shapes.push(shape);

        notifyObservers();
    }

    /**
     * @return shapes
     */
    public List<AbstractShape> getShapes()
    {
        return shapes;
    }

    @Override
    public String toString()
    {
        return "ShapeVault{" +
                "shapes=" + shapes +
                ", undo=" + undo +
                '}';
    }

    /**
     * clears all shapes
     */
    public void clear()
    {
        shapes = new Stack<>();
        undo = new Stack<>();
        notifyObservers();
    }
}
