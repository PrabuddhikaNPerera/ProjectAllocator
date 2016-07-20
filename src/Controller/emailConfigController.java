package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by PPNPERERA on 7/16/2016.
 */
public class emailConfigController {
    @FXML Button save;
    @FXML Button close;
    @FXML TextField smtpserver;
    @FXML TextField port;
    @FXML TextField uname;
    @FXML TextField password;
    @FXML TextField emailFrom;
    @FXML TextField emailTo;

    public void save(){

    }

    public void close(){
        System.out.println("Close");
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
