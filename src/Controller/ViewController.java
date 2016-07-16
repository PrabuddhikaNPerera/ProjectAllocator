package Controller;

import Model.PreferenceTable;
import Model.StudentEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
    @FXML public TableView<ObservableList<String>> tableView;
    @FXML public TableColumn<ObservableList,String> stdName;
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
            ObservableList<ObservableList<String>> tablelist = FXCollections.observableArrayList();
            byte i = 0;
            String dataRow = TSVFile.readLine(); // Read first line.

            while (dataRow != null) {
                ObservableList<String> dataArray = FXCollections.observableArrayList();
                st = new StringTokenizer(dataRow, "\t");
                while (st.hasMoreElements()) {
                    dataArray.add(st.nextElement().toString());
                }
                tablelist.add(dataArray);

                System.out.println(dataArray); // Print the data line.
                dataRow = TSVFile.readLine(); // Read next line of data.
            }

            tableView.setItems(tablelist);

            try{
            stdName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(0).toString());
                }
            });
            preass.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(1).toString());
                }
            });
                p1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(2).toString());
                    }
                });
                p2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(3).toString());
                    }
                });
                p3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(4).toString());
                    }
                });
                p4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(5).toString());
                    }
                });
                p5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(6).toString());
                    }
                });
                p6.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty( param.getValue().size()  < 8  ? "": param.getValue().get(7).toString());


                    }
                });
                p7.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().size()  < 9  ? "":param.getValue().get(8).toString());
                    }
                });
                p8.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().size()  < 10  ? "":param.getValue().get(9).toString());
                    }
                });
                p9.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().size()  < 11  ? "":param.getValue().get(10).toString());
                    }
                });
                p10.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().size()  < 12  ? "":param.getValue().get(11).toString());
                    }
                });

        } catch (IndexOutOfBoundsException e){
               e.getMessage();

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


