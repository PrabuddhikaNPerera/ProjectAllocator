package Controller;

import Model.PreferenceTable;
import Model.StudentEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

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

    public void loadTable() {
        try {
            StringTokenizer st;
            BufferedReader TSVFile = new BufferedReader(new FileReader(file));
            //List<String> dataArray = new ArrayList<String>();
            ObservableList<String> dataArray = FXCollections.observableArrayList();
            String dataRow = TSVFile.readLine(); // Read first line.

            while (dataRow != null) {
                st = new StringTokenizer(dataRow, "\t");
                while (st.hasMoreElements()) {
                    dataArray.add(st.nextElement().toString());
                }
                for (String item : dataArray) {
                    System.out.print(item + "  ");
                    //tableView.setItems(dataArray);
                    //tableView.getColumns().addAll(dataArray);
                }
                dataArray.clear();
                System.out.println(); // Print the data line.
                dataRow = TSVFile.readLine(); // Read next line of data.
            }
            // Close the file once all data has been read.
            TSVFile.close();
        } catch (Exception e){}

    }

    public void reset(){
        System.out.println("reset");
    }

    public void allocateProjects() throws IOException {
        System.out.println("Allocate Projects");
        viewAllocations();
    }

    public void emailSettings() throws IOException {
        System.out.println("Email Settings");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EmailSettings.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage emailconf = new Stage();
        emailconf.setTitle("Email Configurations");
        emailconf.setScene(new Scene(root2));
        emailconf.show();
    }

    public void viewAllocations() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AllocationWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage allocationwindow = new Stage();
        allocationwindow.setTitle("Project Allocations");
        allocationwindow.setScene(new Scene(root1));
        allocationwindow.show();
    }
}


