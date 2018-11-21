package model;

import model.Shapes.AbstractShape;

import java.util.List;
import java.util.Stack;

public class ShapeVault
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
    }

    public void redo()
    {
        if (!undo.empty())
        {
            shapes.push(undo.pop());
        }
    }

    public void addShape(AbstractShape shape)
    {
        shapes.push(shape);
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
}
