package Controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by PPNPERERA on 7/16/2016.
 */
public class projectPoolViewController {

    public Button close;
    public ListView<String> listView;

    public void show(ObservableList<String> lefts){
        System.out.println(lefts);
        //listView.setItems(lefts);
    }

    public void close(){
        System.out.println("Close");
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
