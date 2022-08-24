package uk.ac.aber.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import uk.ac.aber.ExerciseApp;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSetsController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    @FXML
    private HBox root;
    @FXML
    private VBox left;
    @FXML
    private ScrollPane center;
    @FXML
    private GridPane grid;
    @FXML
    private ChoiceBox sortByBox;
    @FXML
    private ToggleButton orderByToggle;
    @FXML
    private Button createSetBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button aboutBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        left.spacingProperty().bind(root.heightProperty().multiply(20).divide(720));
        grid.prefWidthProperty().bind(center.widthProperty());
        grid.hgapProperty().bind(center.widthProperty().add(center.heightProperty()).multiply(0.02));
        grid.vgapProperty().bind(center.widthProperty().add(center.heightProperty()).multiply(0.02));

        sortByBox.prefWidthProperty().bind(root.widthProperty().multiply(150).divide(1280));
        orderByToggle.prefWidthProperty().bind(root.widthProperty().multiply(180).divide(1280));
        createSetBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        historyBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        aboutBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
    }

    public void setScene() {
        grid.getChildren().clear();
        int num = 34;

        int rows = num / 3 + 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 3; j++) {
                if (num >= i * 3 + j + 1) {
                    SetController setController = new SetController();
                    grid.add(setController.getParent(), j + 1, i + 1);
                }
            }
        }
        grid.add(new Pane(), 1, rows + 1);
    }

    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        left.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));
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
    private void createSet() {
        app.getUserInterface().showCreateSet();
    }

    @FXML
    private void history() {
        app.getUserInterface().showHistory();
    }

    @FXML
    private void about() {

    }
}
