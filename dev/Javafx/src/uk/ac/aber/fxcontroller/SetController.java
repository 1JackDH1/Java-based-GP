package uk.ac.aber.fxcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SetController {
    private Parent parent;

    public SetController() {
        try {
            this.parent = FXMLLoader.load(getClass().getResource("/uk/ac/aber/fxml/set.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parent getParent() {
        return parent;
    }
}
