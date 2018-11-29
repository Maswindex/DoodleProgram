/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds the rectangle shape object
 */

package model.shapes;

import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeDimensions;
import model.shapeproperties.ShapeTypes;
import static model.shapeproperties.ShapeTypes.RECTANGLE;
import java.util.Map;

/**
 * defines the rectangle shape
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class Rectangle extends AbstractShape
{
    private static final ShapeTypes shapeType = RECTANGLE;
    private ShapeDimensions dimensions;

    /**
     * Rectangle constructor
     *
     * @param dimensions shape dimensions
     * @param settings settings from toolbar
     */
    public Rectangle(ShapeDimensions dimensions, Map<SettingNames, Object> settings)
    {
        initializeSettings(settings);
        this.dimensions = dimensions;
    }

    @Override
    public ShapeDimensions getDimensions()
    {
        return dimensions;
    }

    public static ShapeTypes getShapeType()
    {
        return shapeType;
    }

    @Override
    public String toString()
    {
        return "Rectangle{" +
                "dimensions=" + dimensions +
                super.toString() +
                '}';
    }
}