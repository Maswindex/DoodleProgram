/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the draw methods
 */
package view;

import javafx.scene.canvas.GraphicsContext;
import model.shapeproperties.ShapeDimensions;
import model.shapes.AbstractShape;
import model.shapes.Squiggle;

/**
 * This class creates methods for drawing shapes
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class SketchPad
{
    private static double x, y, height, width;

    /**
     * creates a line shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void strokeLINE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.strokeLine(x, y, x+width, y+height);
    }

    /**
     * creates a line shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void fillLINE(AbstractShape shape, GraphicsContext pad)
    {
        strokeLINE(shape, pad);
    }

    /**
     * creates a rectangle shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void strokeRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.strokeRect(x, y, Math.abs(width), Math.abs(height));
    }

    /**
     * creates a filled rectangle shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void fillRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.fillRect(x, y, Math.abs(width), Math.abs(height));
        strokeRECTANGLE(shape, pad);
    }

    /**
     * creates an oval shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void strokeOVAL(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.strokeOval(x, y, Math.abs(width), Math.abs(height));
    }

    /**
     * creates a filled oval shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void fillOVAL(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.fillOval(x, y, Math.abs(width), Math.abs(height));
        strokeOVAL(shape, pad);
    }

    /**
     * creates a squiggle line shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void strokeSQUIGGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        Squiggle squiggle = (Squiggle)shape;

        pad.strokePolyline(squiggle.getxCoordinates(), squiggle.getyCoordinates(), squiggle.getxCoordinates().length);
    }


    /**
     * creates a filled squiggle shape
     *
     * @param shape drawable object
     * @param pad graphics context
     */
    public static void fillSQUIGGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        Squiggle squiggle = (Squiggle)shape;

        strokeSQUIGGLE(shape, pad);
        pad.fillPolygon(squiggle.getxCoordinates(), squiggle.getyCoordinates(), squiggle.getxCoordinates().length);
    }

    private static void correctFor2D()
    {
        x += (width < 0 ? width: 0);
        y += (height < 0 ? height: 0);
    }

    private static void applyDimension(ShapeDimensions dimensions)
    {
        width = dimensions.getWidth();
        height = dimensions.getHeight();

        x = dimensions.getAnchor().getX();
        y = dimensions.getAnchor().getY();
    }
}
