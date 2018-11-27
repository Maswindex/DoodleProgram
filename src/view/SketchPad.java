package view;

import javafx.scene.canvas.GraphicsContext;
import model.ShapeProperties.ShapeDimensions;
import model.Shapes.AbstractShape;
import model.Shapes.Squiggle;

public class SketchPad
{
    private static double x, y, height, width;

    public static void strokeLINE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.strokeLine(x, y, x+width, y+height);
    }

    public static void fillLINE(AbstractShape shape, GraphicsContext pad)
    {
        strokeLINE(shape, pad);
    }

    public static void strokeRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.strokeRect(x, y, Math.abs(width), Math.abs(height));
    }

    public static void fillRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        strokeRECTANGLE(shape, pad);
        pad.fillRect(x, y, Math.abs(width), Math.abs(height));
    }

    public static void strokeOVAL(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        pad.strokeOval(x, y, Math.abs(width), Math.abs(height));
    }

    public static void fillOVAL(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        correctFor2D();

        strokeOVAL(shape, pad);
        pad.fillOval(x, y, Math.abs(width), Math.abs(height));
    }

    public static void strokeSQUIGGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        Squiggle squiggle = (Squiggle)shape;

        pad.strokePolyline(squiggle.getxCoordinates(), squiggle.getyCoordinates(), squiggle.getxCoordinates().length);
    }

    public static void fillSQUIGGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());
        Squiggle squiggle = (Squiggle)shape;

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
