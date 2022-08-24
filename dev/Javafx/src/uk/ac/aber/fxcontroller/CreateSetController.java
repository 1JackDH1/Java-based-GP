package uk.ac.aber.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.aber.ExerciseApp;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateSetController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    @FXML
    private HBox root;
    @FXML
    private VBox left;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane eGrid;
    @FXML
    private GridPane bGrid;
    @FXML
    private GridPane htGrid;
    @FXML
    private TextField exerciseNum;
    @FXML
    private TextField exerciseTimeMin;
    @FXML
    private TextField exerciseTimeSec;
    @FXML
    private TextField exerciseBreakMin;
    @FXML
    private TextField exerciseBreakSec;
    @FXML
    private TextField halftimeMin;
    @FXML
    private TextField halftimeSec;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button startBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        grid.hgapProperty().bind(root.widthProperty().multiply(15).divide(1280));
        eGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));
        bGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));
        htGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));

        exerciseNum.maxWidthProperty().bind(root.widthProperty().multiply(250).divide(1280));

        cancelBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        addBtn.prefWidthProperty().bind(root.widthProperty().multiply(300).divide(1280));
        startBtn.prefWidthProperty().bind(root.widthProperty().multiply(300).divide(1280));
    }

    public void setScene() {

    }

    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        left.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));

        AnchorPane.setTopAnchor(grid,100 * root.getHeight() / 720);
        AnchorPane.setRightAnchor(grid,150 * root.getWidth() / 1280);
        AnchorPane.setBottomAnchor(grid,100 * root.getHeight() / 720);
        AnchorPane.setLeftAnchor(grid,150 * root.getWidth() / 1280);
    }

    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @FXML
    private void cancel() {
        app.getUserInterface().showViewSets();
    }

    @FXML
    private void createAdd() {
        app.getUserInterface().showViewSets();
    }

    @FXML
    private void createStart() {
        app.getUserInterface().showExercise();
    }
}
