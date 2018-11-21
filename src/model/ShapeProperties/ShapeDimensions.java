package model.ShapeProperties;

import model.Point;

public class ShapeDimensions
{
    private double height, width;
    private Point anchor;

    public ShapeDimensions(Point anchor, Point end)
    {
        renderCoordinates(anchor, end);
    }

    private void renderCoordinates(Point anchor, Point end)
    {
        double x = anchor.getX(),
               y = anchor.getY(),
                width = x - end.getX(),
                height = y - end.getY();

        this.anchor = new Point(
          x + (width < 0 ? 0: width),
          y + (height < 0 ? 0: height)
        );

        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    public double getHeight()
    {
        return height;
    }

    public double getWidth()
    {
        return width;
    }

    public Point getAnchor()
    {
        return anchor;
    }
}
