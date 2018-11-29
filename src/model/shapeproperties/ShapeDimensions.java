/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the shape dimensions
 */

package model.shapeproperties;

import model.Point;

/**
 * sets coordinates and dimensions for shape objects
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class ShapeDimensions
{
    private double height, width;
    private Point anchor;

    /**
     * dimension constructor
     *
     * @param anchor start point
     * @param end end point
     */
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

    /**
     * @return height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * @return width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * @return anchor point
     */
    public Point getAnchor()
    {
        return anchor;
    }

    @Override
    public String toString()
    {
        return "ShapeDimensions{" +
                "height=" + height +
                ", width=" + width +
                ", anchor=" + anchor +
                '}';
    }
}
