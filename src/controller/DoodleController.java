/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the controller methods
 */

package controller;

import javafx.scene.canvas.GraphicsContext;
import model.Point;
import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeDimensions;
import model.shapeproperties.ShapeTypes;
import model.ShapeVault;
import model.shapes.*;
import obsverver.IObserver;
import view.DoodleView;
import view.SketchFacade;

import java.util.ArrayList;
import java.util.Map;

import static model.shapeproperties.SettingNames.*;
import static model.shapeproperties.ShapeTypes.*;

/**
 * This class is the controller in the MVC structure
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class DoodleController implements IObserver
{
    public static final int WIN_WIDTH = 1000;
    public static final int WIN_HEIGHT = 600;
    private final GraphicsContext drawPane;
    private final DoodleView doodleView;
    private ShapeTypes shapeType;
    private Point anchor;
    private ShapeVault shapes;
    private ArrayList<Double> xCoordinates = new ArrayList<>();
    private ArrayList<Double> yCoordinates = new ArrayList<>();
    private Map<SettingNames, Object> settings;
    private AbstractShape currentShape;

    /**
     * Controller constructor
     *
     * @param graphicsContext2D the drawable pane object
     * @param doodleView the program view
     */
    public DoodleController(GraphicsContext graphicsContext2D, DoodleView doodleView)
    {
        this.drawPane = graphicsContext2D;
        this.doodleView = doodleView;
        shapes = new ShapeVault();
        shapes.addObserver(this);
        shapeType = ShapeTypes.LINE;
        anchor = new Point(0,0);
    }

    /**
     * updates the settings from the toolbar
     */
    public void updateSettings()
    {
        settings = doodleView.getSettings();
        shapeType = (ShapeTypes)settings.get(SHAPE);
    }

    /**
     * undo the last shape drawn
     */
    public void undo()
    {
        shapes.undo();
    }

    /**
     * redo the last shape removed
     */
    public void redo()
    {
        shapes.redo();
    }

    /**
     * sets the anchor point position
     *
     * @param point anchor point on the graphics context
     */
    public void recordAnchor(Point point)
    {
        anchor = new Point(point.getX(), point.getY());
    }

    /**
     * sets the end point position
     *
     * @param end end point on the graphics context
     */
    public void recordEnd(Point end)
    {
        if (shapeType.equals(SQUIGGLE))
        {
            xCoordinates.add(end.getX());
            yCoordinates.add(end.getY());
        }

        displayCurrentState(end);
    }

    private void displayCurrentState(Point end)
    {
        drawAllShapes();
        drawCurrentShape(end);
    }

    private void drawCurrentShape(Point end)
    {
        currentShape = getCurrentShape(end);
        SketchFacade.draw(currentShape, drawPane);
    }


    /**
     * adds a new shape to the drawPane
     */
    public void addNewShape()
    {
        shapes.addShape(currentShape);
        xCoordinates.clear();
        yCoordinates.clear();
    }

    private AbstractShape getCurrentShape(Point end)
    {
        ShapeDimensions dimensions = new ShapeDimensions(anchor, end);

        switch (shapeType)
        {
            case LINE:
                return new Line(dimensions, settings);
            case OVAL:
                return new Oval(dimensions, settings);
            case RECTANGLE:
                return new Rectangle(dimensions, settings);
            case SQUIGGLE:
                return new Squiggle(xCoordinates, yCoordinates, settings);
            default:
                return new Line(dimensions, settings);
        }
    }

    @Override
    public void update(obsverver.Observable observable, Object... args)
    {
        drawAllShapes();
    }

    private void drawAllShapes()
    {
        drawPane.clearRect(0,0, WIN_WIDTH, WIN_HEIGHT);
        SketchFacade.drawAll(shapes.getShapes(), drawPane);
    }

    /**
     * clears the drawPane of all shapes
     */
    public void clear()
    {
        shapes.clear();
    }

    @Override
    public String toString()
    {
        return "DoodleController{" +
                "drawPane=" + drawPane +
                ", doodleView=" + doodleView +
                ", shapeType=" + shapeType +
                ", anchor=" + anchor +
                ", shapes=" + shapes +
                ", xCoordinates=" + xCoordinates +
                ", yCoordinates=" + yCoordinates +
                ", settings=" + settings +
                ", currentShape=" + currentShape +
                '}';
    }
}
