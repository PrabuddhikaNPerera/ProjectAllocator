package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by PPNPERERA on 7/16/2016.
 */
public class allocationViewController {
    @FXML Button allocationsave;
    @FXML Button print;
    @FXML Button close;

    public void show(){

    }
    public void save(){

    }

    public void print(){

    }

    public void close(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        System.out.println("Close");
    }
}
