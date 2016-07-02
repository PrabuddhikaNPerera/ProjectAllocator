package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.awt.*;
import java.io.File;

public class ViewController {

    public Button chooseFile;
    public TextField filePath;
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
            //Allocation Process
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
}