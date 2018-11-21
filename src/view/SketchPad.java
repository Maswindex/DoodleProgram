package view;

import javafx.scene.canvas.GraphicsContext;
import model.ShapeProperties.ShapeDimensions;
import model.Shapes.AbstractShape;
import model.Shapes.Squiggle;

public class SketchPad
{
    private static double x1, y1, x2, y2, height, width;

    public static void strokeLINE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.strokeLine(x1, y1, x2, y2);
    }

    public static void strokeRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.strokeRect(x1, y1, width, height);
    }

    public static void fillRECTANGLE(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.fillRect(x1, y1, width, height);
    }

    public static void strokeOVAL(AbstractShape shape, GraphicsContext pad)
{
    applyDimension(shape.getDimensions());

    pad.strokeOval(x1, y1, width, height);
}

    public static void fillOVAL(AbstractShape shape, GraphicsContext pad)
    {
        applyDimension(shape.getDimensions());

        pad.fillOval(x1, y1, width, height);
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

    private static void applyDimension(ShapeDimensions dimensions)
    {
        x1 = dimensions.getAnchor().getX();
        y1 = dimensions.getAnchor().getY();
        width = dimensions.getWidth();
        height = dimensions.getHeight();
        x2 = x1 + width;
        y2 = y1 + height;
    }
}
