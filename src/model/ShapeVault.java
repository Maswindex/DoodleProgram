package model;

import model.Shapes.AbstractShape;
import obsverver.Observable;

import java.util.List;
import java.util.Stack;

public class ShapeVault extends Observable
{
    private Stack<AbstractShape> shapes, undo;

    public ShapeVault()
    {
        shapes = new Stack<>();
        undo = new Stack<>();
    }

    public void undo()
    {
        if (!shapes.empty())
        {
            undo.push(shapes.pop());
        }

        notifyObservers();
    }

    public void redo()
    {
        if (!undo.empty())
        {
            shapes.push(undo.pop());
        }

        notifyObservers();
    }

    public void addShape(AbstractShape shape)
    {
        shapes.push(shape);

        notifyObservers();
    }

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

    public void clear()
    {
        shapes = new Stack<>();
        undo = new Stack<>();
        notifyObservers();
    }
}
