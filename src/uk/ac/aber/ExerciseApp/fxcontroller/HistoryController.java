package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import uk.ac.aber.ExerciseApp.Exercise;
import uk.ac.aber.ExerciseApp.ExerciseApp;
import uk.ac.aber.ExerciseApp.Session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * This class handles the history fxml file, the history page's
 * graphical components and the line chart of completed workouts.
 *
 * @authors REDACTED 

 * @version 1.0
 */
public class HistoryController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    private XYChart.Series<String, Number> dataSeries;

    /**
     * Formatter to convert LocalDateTime variables into usable strings for
     * the line chart.
     */
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @FXML
    private HBox root;
    @FXML
    private GridPane grid, tileGrid;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button graphBtn;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    private Image[] images;

    /**
     * Method to initialize the controller after its root element
     * has been completely processed. Also scales the window and
     * its components. Sets the data series to the line chart.
     *
     * @param location  pathname location used to solve relative paths for the root object,
     *                  or null if the location is not known
     * @param resources resources used to localize the root object, or null if the root object
     *                  was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        grid.vgapProperty().bind(root.heightProperty().multiply(0.02));

        tileGrid.prefWidthProperty().bind(scrollPane.widthProperty());
        tileGrid.hgapProperty().bind(scrollPane.widthProperty().add(scrollPane.heightProperty()).multiply(0.02));
        tileGrid.vgapProperty().bind(scrollPane.widthProperty().add(scrollPane.heightProperty()).multiply(0.02));

        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        chart.setLegendVisible(false);
        dataSeries = new XYChart.Series<>();
        chart.getData().add(dataSeries);
    }

    /**
     * Method to load the exercise animations onto the history page.
     */
    public void loadImages() {
        images = new Image[app.getExercises().length];
        for (int i = 0; i < app.getExercises().length; i++) {
            try {
                images[i] = new Image(new FileInputStream(app.getExercises()[i].getAnimation(0)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to return the history page to its default state,
     * where the line chart is visible and the other components
     * are not.
     */
    public void resetToDefault() {
        scrollPane.setVisible(false);
        graphBtn.setVisible(false);
        chart.setVisible(true);
    }

    /**
     * Method to set the scene of the page. For each completed session in
     * the data manager, it's shown onto the page and a temporary session
     * controller is created for it. If a session is clicked upon, the line chart
     * is hidden and other components are shown, each exercise in the session
     * is shown - its name and animation. This method will also set the data points
     * for each completed session onto the line chart.
     */
    public void setScene() {
        // Clear data from the history grid and line chart
        grid.getChildren().clear();
        dataSeries.getData().clear();
        if (app.getDataManager().getHistory().size() != 0) {
            int index = 0;
            String date = app.getDataManager().getHistory().get(0).getDateTime().format(TIME_FORMATTER);
            int timeValue = 0;
            for (Session session : app.getDataManager().getHistory()) {
                // Gather line chart data
                if (date.equals(session.getDateTime().format(TIME_FORMATTER))) {
                    timeValue += session.getWorkout().getDuration() / 60;
                } else {
                    dataSeries.getData().add(new XYChart.Data<>(date, timeValue));
                    date = session.getDateTime().format(TIME_FORMATTER);
                    timeValue = session.getWorkout().getDuration() / 60;
                }

                // Get the session root object by using the session controller
                SessionController sessionController = new SessionController(session);
                grid.add(sessionController.getParent(), 0, index++);

                //Add event to display the exercises per session
                sessionController.getParent().setOnMouseClicked(mouseEvent -> {
                    //Hide the chart and clear the exercise grid
                    chart.setVisible(false);
                    graphBtn.setVisible(true);
                    scrollPane.setVisible(true);
                    tileGrid.getChildren().clear();

                    int row = 0;
                    int col = 0;
                    for (Exercise exercise : session.getExercises()) {
                        // Add exercises to the grid pane consisting of 3 columns
                        ImageView imageView = new ImageView(getExerciseImageFromName(exercise.getName()));
                        imageView.fitWidthProperty().bind(scrollPane.widthProperty().divide(4));
                        imageView.setPreserveRatio(true);
                        Label label = new Label(exercise.getName());
                        label.setWrapText(true);
                        label.setAlignment(Pos.CENTER);
                        label.setTextAlignment(TextAlignment.CENTER);
                        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        VBox vBox = new VBox(imageView, label);
                        vBox.setAlignment(Pos.TOP_CENTER);
                        tileGrid.add(vBox, col++ + 1, row + 1);
                        if (col == 3) {
                            row++;
                        }
                        col %= 3;
                    }
                });
            }
            // Add data to line chart
            dataSeries.getData().add(new XYChart.Data<>(date, timeValue));
        }
    }

    /**
     * Method to apply scaling to the page's root component.
     */
    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        root.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));
    }

    /**
     * Method to set the exercise application in the history controller.
     *
     * @param app exercise application - ExerciseApp
     */
    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    /**
     * Method to get the parent node from the history controller
     *
     * @return parent node
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Method to set the parent node in the history controller.
     *
     * @param parent parent node
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * Method to obtain specific animations by the name of
     * the exercise.
     *
     * @param name name of exercise
     * @return Image array containing animations
     */
    private Image getExerciseImageFromName(String name) {
        for (int i = 0; i < app.getExercises().length; i++) {
            if (app.getExercises()[i].getName().equals(name)) {
                return images[i];
            }
        }
        return null;
    }

    /**
     * Method to change the program's user interface to the
     * ViewWorkoutController - the main page showing all
     * workouts.
     */
    @FXML
    private void back() {
        app.getUserInterface().showViewWorkouts();
    }

    /**
     * Method to show the line chart and set other
     * components invisible.
     */
    @FXML
    private void viewGraph() {
        scrollPane.setVisible(false);
        graphBtn.setVisible(false);
        chart.setVisible(true);
    }
}
