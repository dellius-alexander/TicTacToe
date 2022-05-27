package com.hyfi.tictactoe;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


@Data
public class FXMLDialogController {
    private static final Logger log = LoggerFactory.getLogger(FXMLDialogController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="aiPrompt"
    private Text aiPrompt; // Value injected by FXMLLoader

    @FXML // fx:id="gridPane"
    private GridPane gridPane; // Value injected by FXMLLoader

    @FXML // fx:id="no"
    private Button no; // Value injected by FXMLLoader

    @FXML // fx:id="userPrompt"
    private Text userPrompt; // Value injected by FXMLLoader

    @FXML // fx:id="yes"
    private Button yes; // Value injected by FXMLLoader

    String greeting = new String(
            "\t\t\t\t\t\t\t\t**** WELCOME TO THE TIC-TAC-TOE GAME ****\n" +
                    "\t\t\t\t\t\t\t\t------ Author: Dellius A. Alexander -----" +
                    "\n\n\t\t\t\t\t\t\t\tWould you like to play?"
    );

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize(Stage stage) throws IOException {
        assert aiPrompt != null : "fx:id=\"aiPrompt\" was not injected: check your FXML file 'dialogue.fxml'.";
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'dialogue.fxml'.";
        assert no != null : "fx:id=\"no\" was not injected: check your FXML file 'dialogue.fxml'.";
        assert userPrompt != null : "fx:id=\"userPrompt\" was not injected: check your FXML file 'dialogue.fxml'.";
        assert yes != null : "fx:id=\"yes\" was not injected: check your FXML file 'dialogue.fxml'.";
        userPrompt.setText(greeting);
        userPrompt = (Text) setConfig(aiPrompt,300,300);
        aiPrompt = (Text) setConfig(aiPrompt,300,300);
        yes = (Button) setConfig(yes,30,30);
        no = (Button) setConfig(no, 30,30);
        gridPane.add(userPrompt,0,0);
        gridPane.setVisible(true);

//        // Provides the animated scrolling behavior for the text
//        TranslateTransition transTransition =
//                new TranslateTransition(
//                        new Duration(7500),userPrompt);
//        transTransition.setToY(-620);
//        transTransition.setInterpolator(Interpolator.LINEAR);
//        transTransition.setCycleCount(Timeline.INDEFINITE);


    }

    Object setConfig(Object node, int X, int Y){

        if (node instanceof Text)
        {
            Text n = (Text)node;
            n.setLayoutX(X-75);
            n.setLayoutY(Y-75);
            n.setTextOrigin(VPos.CENTER);
            n.setTextAlignment(TextAlignment.JUSTIFY);
            n.setWrappingWidth(X-100);
            n.setFill(Color.BLACK);
            n.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            return n;
        } 
        else if (node instanceof Label)
        {
            Label n = (Label) node;
            n.setLayoutX(X);
            n.setLayoutY(Y);
            n.setAlignment(Pos.CENTER);
            n.setWrapText(true);
            n.setTextFill(Color.BLACK);
            n.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            return n;
        }
        else if (node instanceof Button)
        {
            Button b = (Button) node;
//            b.setLayoutX(X);
//            b.setLayoutY(Y);
            b.setAlignment(Pos.CENTER);
            b.setWrapText(true);
            b.setTextFill(Color.rgb(187, 195, 107));
            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            return b;
            
        }
        return null;
    }

    @FXML // mouse click events
    boolean onMouseClicked(MouseEvent event) {
        log.info("Event Source: {}",event.getSource().toString());
        log.info("Event Source: {}",event.getSource());
        if (event.getSource() instanceof Button){
            Button b = (Button) event.getSource();
            log.info("\nButton: {}",b.toString());
            if (b.getId().equalsIgnoreCase("yes")){
                gridPane.setVisible(false);
                return true;
            }
            else {

                return false;
            }
        }
        return false;
    }

}
