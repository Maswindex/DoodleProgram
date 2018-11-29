/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the facade draw methods
 */

package view;

import javafx.scene.canvas.GraphicsContext;
import model.shapes.AbstractShape;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * This class uses the facade design pattern to draw shapes
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class SketchFacade
{
    /**
     * draws a shape to the graphics context
     *
     * @param shape the shape being drawing
     * @param pad graphics context
     */
    public static void draw(AbstractShape shape, GraphicsContext pad)
    {
        setDrawingProperties(shape, pad);
        Method drawingMethod = getDrawingMethod(shape);
        if (drawingMethod != null)
        {
            invokeDrawingMethod(drawingMethod, shape, pad);
        }
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

    /**
     * draws all shapes from the stack
     *
     * @param shapes drawable objects
     * @param pad graphics context
     */
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
