package Controller;

import Model.PreferenceTable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Vector;

public class ViewController {

    @FXML public Button chooseFile;
    @FXML public TextField filePath;
    @FXML public TableView tableView;
    @FXML public TableColumn stdName;
    @FXML public TableColumn preass;
    @FXML public TableColumn p1;
    @FXML public TableColumn p2;
    @FXML public TableColumn p3;
    @FXML public TableColumn p4;
    @FXML public TableColumn p5;
    @FXML public TableColumn p6;
    @FXML public TableColumn p7;
    @FXML public TableColumn p8;
    @FXML public TableColumn p9;
    @FXML public TableColumn p10;


    public File file;

    public void getFilePath() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Project Preferences File");
            FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TSV File", "*.tsv");
            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Text File", "*.txt");
            FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("CSV File", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter1);
            fileChooser.getExtensionFilters().add(extFilter2);
            fileChooser.getExtensionFilters().add(extFilter3);
            file = fileChooser.showOpenDialog(null);
            filePath.setText(file.getPath());
        } catch(NullPointerException e){}
    }

    public void submit(){
        if (!fileValidator()){
            System.exit(1);
        } else {
            System.out.println("File Accepted");
            loadTable();
        }

    }

    public boolean fileValidator(){
        if(file.exists()){
            return true;
        } else{
            System.out.println("File is Invalid");
            return false;
        }
    }

    public void loadTable(){
        PreferenceTable pref = new PreferenceTable();
        Vector<Vector<String>> allData = pref.loadContentFromFile(file);
        for (Vector v: allData) {
            for (Object y: v) {
                tableView.getItems().addAll(v);
            }
        }
    }
}