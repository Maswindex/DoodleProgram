package view;

import javafx.scene.canvas.GraphicsContext;
import model.Shapes.AbstractShape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class SketchFacade
{
    public static void draw(AbstractShape shape, GraphicsContext pad)
    {
        setDrawingProperties(shape, pad);
        Method drawingMethod = getDrawingMethod(shape);
        invokeDrawingMethod(drawingMethod, shape, pad);
    }

    private static void invokeDrawingMethod(Method drawingMethod, AbstractShape shape, GraphicsContext pad)
    {
        try
        {
            drawingMethod.invoke(null, shape, pad);
        }
        catch (IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    private static Method getDrawingMethod(AbstractShape shape)
    {
        try
        {
            return SketchPad.class.getMethod(
                    (shape.isFilled() ? "fill" : "stroke") + shape.getType(),
                    AbstractShape.class,
                    GraphicsContext.class
            );
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("No Such Method Found: " + e.getMessage());
            return null;
        }
    }

    public static void drawAll(List<AbstractShape> shapes, GraphicsContext pad)
    {
        for (AbstractShape shape : shapes)
        {
            draw(shape, pad);
        }
    }

    private static void setDrawingProperties(AbstractShape shape, GraphicsContext pad)
    {
        pad.setLineWidth(shape.getStrokeWidth());
        pad.setStroke(shape.getStrokeColor());
        pad.setFill(shape.getFillColor());
    }
}
