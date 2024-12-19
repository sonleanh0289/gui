package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML 
    private Pane drawingAreaPane;
    
    private boolean isEraserMode;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
    @FXML
    void penButtonPressed(ActionEvent event) {
    	isEraserMode = false;
    }
    @FXML
    void eraserButtonPressed(ActionEvent event) {
    	isEraserMode = true;
    }
    	
    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	if(isEraserMode == false) {
    		Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            drawingAreaPane.getChildren().add(newCircle);
    	}
    	else {
    		Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.WHITE);
    		drawingAreaPane.getChildren().add(newCircle);
    	}
    }
}
