package controller;

import javafx.scene.canvas.GraphicsContext;
import model.Point;
import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;
import model.ShapeProperties.ShapeTypes;
import model.ShapeVault;
import model.Shapes.*;
import view.DoodleView;
import view.SketchFacade;

import java.util.ArrayList;
import java.util.Map;

import static model.ShapeProperties.SettingNames.*;
import static model.ShapeProperties.ShapeTypes.*;

public class DoodleController
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

    public DoodleController(GraphicsContext graphicsContext2D, DoodleView doodleView)
    {
        this.drawPane = graphicsContext2D;
        this.doodleView = doodleView;
        shapes = new ShapeVault();
        shapeType = ShapeTypes.LINE;
        anchor = new Point(0,0);
    }

    public void updateSettings()
    {
        settings = doodleView.getSettings();
        shapeType = (ShapeTypes)settings.get(SHAPE);
    }

    public void undo()
    {

    }

    public void redo()
    {

    }

    public void recordAnchor(Point point)
    {
        anchor = new Point(point.getX(), point.getY());
    }

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


    private void drawAllShapes()
    {
        drawPane.clearRect(0,0, WIN_WIDTH, WIN_HEIGHT);
        SketchFacade.drawAll(shapes.getShapes(), drawPane);
    }

    public void addNewShape()
    {
        shapes.addShape(currentShape);
        xCoordinates.clear();
        yCoordinates.clear();
        drawAllShapes();
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
}
