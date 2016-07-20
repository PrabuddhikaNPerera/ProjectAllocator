package Controller;

/**
 * Created by ppnperera on 7/2/2016.
 */

import Model.CandidateAssignment;
import Model.MailServer;
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
import javafx.stage.*;
import javafx.util.Callback;

import javax.mail.Session;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class mainViewController {

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
    @FXML public TableView<ObservableList<String>> AllocationTable;
    @FXML public TableColumn<ObservableList,String> stname;
    @FXML public TableColumn project;
    @FXML public Label TD;
    @FXML public Button projectPool;
    @FXML public Button close;
    @FXML public CheckBox emailreport;
    //Email settings window
    @FXML public TextField smtpserver;
    @FXML public TextField emailTo;
    @FXML public TextField emailFrom;


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
        file = null;
        filePath.clear();
        tableView.setItems(null);
    }

    public void allocateProjects() throws IOException {
        System.out.println("Allocate Projects");
        viewAllocations();
    }

    public void emailSettings() throws IOException {
        System.out.println("Email Settings");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/EmailSettings.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage emailconf = new Stage();
        emailconf.setTitle("Email Configurations");
        emailconf.setScene(new Scene(root2));
        emailconf.show();
    }

    public void viewAllocations() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/AllocationWindow.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage allocationwindow = new Stage();
        allocationwindow.setTitle("Project Allocations");
        allocationwindow.setScene(new Scene(root1));
        allocationwindow.show();

        ObservableList<ObservableList<String>> allocation = FXCollections.observableArrayList();
        PreferenceTable p = new PreferenceTable(file);

//       Coded by Fiction
//        get all the projects without duplicates
        ArrayList ProjectList = new ArrayList();
        ArrayList StudentList = new ArrayList();
        ArrayList StudentListPreArange = new ArrayList();

        int[][][] ProjectAsinList =  new int [51][10][2];

        Genetic gn = new Genetic();
        int studentIndex = 0;

//      Get project list
        for (StudentEntry student : p.getAllStudentEntries()) {
            //            loop all the project that current student has
            int numProjects = student.getOrderedPreferences().size();//get how many project has current student
            for(int sprjt = 0; numProjects>sprjt; sprjt++){

                if(!gn.isInArray(ProjectList, student.getOrderedPreferences().get(sprjt)))//remove duplicates
                    ProjectList.add(student.getOrderedPreferences().get(sprjt));
            }
        }
        for (StudentEntry student : p.getAllStudentEntries()) {

            ObservableList<String> nameProject = FXCollections.observableArrayList();
            CandidateAssignment c1 = new CandidateAssignment(student);
            nameProject.add(student.getStudentName());
            nameProject.add(c1.getPreference());
            allocation.add(nameProject);

//            set student list
            StudentList.add(studentIndex,student.getStudentName());
            StudentListPreArange.add(studentIndex, student.hasPreassignedProject());//set pr arrage list

//            set every student project
            int numProjects = student.getOrderedPreferences().size();//get how many project has current student

            // System.out.print(gn.getStudentNameByID(StudentList, studentIndex));
            //System.out.println(numProjects);

            for(int projectOrder = 0; 10>projectOrder; projectOrder++){

                ProjectAsinList[studentIndex][projectOrder][0] = projectOrder;

                if(numProjects<=projectOrder)
                    ProjectAsinList[studentIndex][projectOrder][1] = -1;
                else
                    ProjectAsinList[studentIndex][projectOrder][1] = gn.getProjectIndex(ProjectList, student.getOrderedPreferences().get(projectOrder));
            }
            //System.out.println(ProjectAsinList[studentIndex][9][1]);
            //System.out.println();
            studentIndex++;
        }

        Float TotalDisapoints = gn.getTotalDisapointments( ProjectList,  StudentList ,  StudentListPreArange, ProjectAsinList);

        //AllocationTable.setItems(allocation);
//        try{
//        stname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                return new SimpleStringProperty(param.getValue().get(0).toString());
//            }
//        });
//        } catch (IndexOutOfBoundsException e){
//            e.getMessage();
//
//        }
        System.out.println("Total Disapoints is :" + TotalDisapoints);
        //TD.setText(TotalDisapoints.toString());

        if (emailreport.isSelected()){
            email();
        }
    }

    public void email(){
        String to = emailTo.toString();
        String[] tos = to.split(";");

        String from = emailFrom.toString();
        String subject = "Project Allocation Data";
        String body = "Allocation Results";
        String authUsername = "test@gmail.com";
        String AuthPassword = "abc123@@@";
        String domain = "smtp.google.com";
        String port = "465";


        Session session = MailServer.setSMTPConfig(authUsername, AuthPassword, domain, port);

        MailServer.sendMail(tos, from, subject, body, session);
    }

    public void viewProjectPool() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("../View/projectPool.fxml"));
        Parent root2 = fxmlLoader2.load();
        Stage projectList = new Stage();
        projectList.initModality(Modality.WINDOW_MODAL);
        projectList.setTitle("Project Allocations");
        projectList.setScene(new Scene(root2));
        projectList.show();
        final ObservableList<String> lefts = FXCollections.observableArrayList("A","B","C");
        projectPoolViewController pvc = new projectPoolViewController();
        pvc.show(lefts);
    }

}


