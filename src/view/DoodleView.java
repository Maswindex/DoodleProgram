/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds all the view methods
 */
package view;

import controller.DoodleController;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Point;
import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeTypes;

import java.util.HashMap;
import java.util.Map;

import static model.shapeproperties.SettingNames.*;

/**
 * This class creates the view for the doodle application
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class DoodleView extends Application
{
    public static final int WIN_WIDTH = 1000;
    public static final int WIN_HEIGHT = 600;
    public static final int SHAPE_ICON_SIZE = 20;
    public static final int MAX_STROKE = 20;
    public static final int MIN_STROKE = 1;

    //drawing on the canvas
    private Canvas canvas;

    //selecting shapes
    private ToggleGroup shapeGroup;

    //shape settings
    private ColorPicker fillColorPicker = new ColorPicker();
    private ColorPicker strokeColorPicker = new ColorPicker();
    private Slider strokeSlider;
    private CheckBox filledCheckbox;
    private DoodleController controller;

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Doodle Program");
        stage.setScene(getPrimaryScene());
        stage.show();
        controller.updateSettings();
    }

    private Scene getPrimaryScene()
    {
        BorderPane mainPanel = new BorderPane();
        VBox top = new VBox();
        Node toolbar = getToolbar();
        top.getChildren().addAll(buildMenu(), toolbar);

        //set the primary regions
        mainPanel.setTop(top);
        mainPanel.setCenter(getCanvas());

        Scene scene = new Scene(mainPanel, WIN_WIDTH, WIN_HEIGHT);
        scene.getStylesheets().add("styles.css");

        return scene;
    }

    private Parent getToolbar()
    {
        HBox panel = new HBox();
        panel.setId("toolbar-main");
        panel.getChildren().addAll(buildShapeSection(), buildSettings(), buildEdit());

        return panel;
    }

    private HBox buildShapeSection()
    {
        HBox shapesPanel = new HBox();
        shapesPanel.setId("toolbar-shapes");

        String[] shapes = {"Line", "Oval", "Rectangle", "Squiggle"};
        ToggleButton[] buttons = new ToggleButton[shapes.length];
        shapeGroup = new ToggleGroup();

        for (int i = 0; i < shapes.length; i++) {
            buttons[i] = getImageToggleButton(shapes[i]);
            buttons[i].setOnAction(event -> controller.updateSettings());
        }

        buttons[0].setSelected(true);
        shapeGroup.getToggles().addAll(buttons);
        shapesPanel.getChildren().addAll(buttons);

        return shapesPanel;
    }

    private HBox buildSettings()
    {
        HBox settingsPanel = new HBox();
        settingsPanel.setId("toolbar-settings");

        styleColorPicker(fillColorPicker);
        styleColorPicker(strokeColorPicker);
        fillColorPicker.valueProperty().addListener((observable, oldV, newV) -> controller.updateSettings());
        strokeColorPicker.valueProperty().addListener((observable, oldV, newV) -> controller.updateSettings());

        VBox strokeBox = new VBox();
        Label strokeLabel = new Label("Stroke: 1");
        strokeSlider = new Slider();
        strokeBox.getChildren().addAll(strokeSlider, strokeLabel);

        strokeSlider.setMin(MIN_STROKE);
        strokeSlider.setMax(MAX_STROKE);
        strokeSlider.valueProperty().addListener((observable, oldV, newV) ->
                {
                    strokeLabel.setText("Stroke: " + numToInt(newV));
                    controller.updateSettings();
                }
        );

        filledCheckbox = new CheckBox("Filled");
        filledCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> controller.updateSettings());

        settingsPanel.getChildren().addAll(new Label("Fill:"), fillColorPicker,
                new Label("Stroke:"), strokeColorPicker, strokeBox, filledCheckbox);

        return settingsPanel;
    }

    private void styleColorPicker(ColorPicker picker)
    {
        picker.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
        picker.setValue(Color.BLACK);
    }

    private int numToInt(Number value)
    {
        return (int) Math.floor(value.doubleValue());
    }

    private HBox buildEdit()
    {
        HBox editPanel = new HBox();
        editPanel.setId("toolbar-edits");

        String[] edits = {"undo", "redo"};
        Button[] buttons = new Button[edits.length];

        for (int i = 0; i < edits.length; i++) {
            buttons[i] = getImageButton(edits[i]);
        }

        buttons[0].setOnAction(event -> controller.undo());
        buttons[1].setOnAction(event -> controller.redo());

        editPanel.getChildren().addAll(buttons);
        for (Node edit : editPanel.getChildren())
        {
            System.out.println(edit.getId());
        }

        return editPanel;
    }

    private ImageView getButtonIcon(String text)
    {
        ImageView image = new ImageView(text + ".png");
        image.setFitHeight(SHAPE_ICON_SIZE);
        image.setFitWidth(SHAPE_ICON_SIZE);
        return image;
    }

    private ToggleButton getImageToggleButton(String text)
    {
        ToggleButton result = new ToggleButton();
        result.setUserData(text);
        result.setGraphic(getButtonIcon(text));
        return result;
    }

    private Button getImageButton(String text)
    {
        Button result = new Button();
        result.setGraphic(getButtonIcon(text));
        return result;
    }

    private Parent getCanvas()
    {
        VBox box = new VBox();

        canvas = new Canvas();
        controller = new DoodleController(canvas.getGraphicsContext2D(), this);
        addCanvasEventHandlers();
        canvas.setStyle("-fx-background-color: black");
        canvas.widthProperty().bind(box.widthProperty());
        canvas.heightProperty().bind(box.heightProperty());

        box.getChildren().add(canvas);

        return box;
    }

    private void addCanvasEventHandlers()
    {
        canvas.setOnMousePressed(event ->
                controller.recordAnchor(new Point((int)event.getX(), (int)event.getY()))
        );

        canvas.setOnMouseDragged(event ->
                controller.recordEnd(new Point((int)event.getX(), (int)event.getY()))
        );

        canvas.setOnMouseReleased(event ->
                controller.addNewShape()
        );
    }

    private MenuBar buildMenu()
    {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu draw = new Menu("Draw");
        Menu help = new Menu("Help");

        fileMenu(file);
        editMenu(edit);
        drawMenu(draw);
        help(help);

        menuBar.getMenus().addAll(file, edit, draw, help);
        return menuBar;
    }

    private void fileMenu(Menu file)
    {
        MenuItem[] items = {new MenuItem("Quit")};
        items[0].setOnAction(event -> System.exit(0));
        file.getItems().addAll(items);
    }

    private void editMenu(Menu edit)
    {
        MenuItem[] items = {new MenuItem("Undo"), new MenuItem("Redo")};
        items[0].setOnAction(event -> controller.undo());
        items[1].setOnAction(event -> controller.redo());
        edit.getItems().addAll(items);
    }

    private void drawMenu(Menu draw)
    {
        String[] shapeTypes = {"Line", "Oval", "Rectangle", "Squiggle"};
        Menu shapesMenu = new Menu("Shape Tools");
        MenuItem[] shapes = new MenuItem[4];

        for (int i = 0; i < shapeTypes.length; i++)
        {
            shapes[i] = new MenuItem(shapeTypes[i]);
            for (Toggle button : shapeGroup.getToggles())
            {
                if (button.getUserData().equals(shapeTypes[i]))
                {
                    shapes[i].setOnAction(event->((ToggleButton)button).fire());
                }
            }
        }
        
        shapesMenu.getItems().addAll(shapes);
        draw.getItems().add(shapesMenu);

        MenuItem clear = new MenuItem("Clear shapes");
        clear.setOnAction(event->controller.clear());
        draw.getItems().add(clear);
    }

    private void help(Menu about)
    {
        MenuItem[] items = {new MenuItem("About")};
        items[0].setOnAction(event->showAlertWithHeaderText());
        about.getItems().addAll(items);
    }

    /**
     * Returns the settings map determined by the toolbar
     *
     * @return Map object for settings
     */
    public Map<SettingNames, Object> getSettings()
    {
        Map<SettingNames, Object> settings = new HashMap<>();
        fillSettingsMap(settings);
        return settings;
    }

    private void fillSettingsMap(Map<SettingNames, Object> settings)
    {
        settings.put(STROKECOLOR, strokeColorPicker.getValue());
        settings.put(FILLCOLOR, fillColorPicker.getValue());
        settings.put(STROKEWIDTH, strokeSlider.getValue());
        settings.put(FILLED, filledCheckbox.isSelected());
        settings.put(SHAPE, ShapeTypes.valueOf(shapeGroup.getSelectedToggle().getUserData().toString().toUpperCase()));
    }

    private void showAlertWithHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Doodle Program");
        alert.setHeaderText("Version 1.0");
        alert.setContentText("Authors: Zachary Rosenlund and Mason Hernandez");
        alert.showAndWait();
    }

    @Override
    public String toString()
    {
        return "DoodleView{" +
                "canvas=" + canvas +
                ", shapeGroup=" + shapeGroup +
                ", fillColorPicker=" + fillColorPicker +
                ", strokeColorPicker=" + strokeColorPicker +
                ", strokeSlider=" + strokeSlider +
                ", filledCheckbox=" + filledCheckbox +
                ", controller=" + controller +
                '}';
    }
}