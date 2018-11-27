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
        this.anchor = new Point(
                anchor.getX(),
                anchor.getY()
        );

        this.width = end.getX() - anchor.getX();
        this.height = end.getY() - anchor.getY();
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
