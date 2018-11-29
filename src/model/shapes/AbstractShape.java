/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds the abstract shape parent
 */

package model.shapes;

import javafx.scene.paint.Color;
import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeDimensions;
import model.shapeproperties.ShapeTypes;

import java.util.Map;

import static model.shapeproperties.SettingNames.*;

/**
 * Abstract parent for defining shape object
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public abstract class AbstractShape
{
    private Color fillColor, strokeColor;
    private double strokeWidth;
    private boolean filled;
    private ShapeTypes type;

    /**
     * @return dimensions for shape
     */
    public abstract ShapeDimensions getDimensions();

    /**
     * @return boolean true if filled
     */
    public boolean isFilled()
    {
        return filled;
    }

    /**
     * @return fillColor
     */
    public Color getFillColor()
    {
        return fillColor;
    }

    /**
     * @return strokeColor
     */
    public Color getStrokeColor()
    {
        return strokeColor;
    }

    /**
     * @return strokeWidth
     */
    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    /**
     * @return type
     */
    public ShapeTypes getType()
    {
        return type;
    }

    protected void initializeSettings(Map<SettingNames, Object> settings)
    {
        fillColor = (Color)settings.get(FILLCOLOR);
        strokeColor = (Color)settings.get(STROKECOLOR);
        strokeWidth = (double)settings.get(STROKEWIDTH);
        filled = (boolean)settings.get(FILLED);
        type = (ShapeTypes)settings.get(SHAPE);
    }

    @Override
    public String toString()
    {
        return "AbstractShape{" +
                "fillColor=" + fillColor +
                ", strokeColor=" + strokeColor +
                ", strokeWidth=" + strokeWidth +
                ", filled=" + filled +
                '}';
    }
}
