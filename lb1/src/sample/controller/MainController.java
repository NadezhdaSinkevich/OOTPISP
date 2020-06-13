package sample.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static sample.model.Engineer.Quality.*;
import static sample.model.Manager.ManageType.*;
import static sample.model.Programmer.ProgType.*;
import static sample.model.Tester.TestType.*;
import static sample.model.Designer.DesignType.*;

public class MainController {
    public static final String DATE = "startDate";
    private ObservableList<Employee> editionData = FXCollections.observableArrayList();

    @FXML
    private TableView<Employee> tableEditions;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, Boolean> remoteWorkColumn;
    @FXML
    private TableColumn<Employee, Engineer.Quality> qualityColumn;
    @FXML
    private TableColumn<Employee, LocalDate> dateColumn;
    @FXML
    Label startWorkDateLabel;

    @FXML
    Label manageLabel;

    @FXML
    Label typeLabel;

    @FXML
    Label educationLabel;

    @FXML
    Label authorLabel;

    @FXML
    Label pageNumLabel;

    @FXML
    Label officeLabel;

    @FXML
    Label qualityLabel;

    @FXML
    Label sphereLabel;

    @FXML
    DatePicker startWorkDatePicker;

    @FXML
    ComboBox<Manager.ManageType> manageComboBox;

    @FXML
    ComboBox<Programmer.ProgType> typeComboBox;

    @FXML
    ComboBox<Designer.Quality> qualityComboBox;

    @FXML
    ComboBox<Tester.TestType> sphereComboBox;

    @FXML
    ComboBox<Designer.DesignType> DesignerComboBox;


    @FXML
    TextField officeTextField;

    @FXML
    TextField pageNumTextField;

    @FXML
    CheckBox educationCheckBox;

    @FXML
    TextField authorTextField;

    @FXML
    ComboBox<String> classComboBox;

    @FXML
    TextField nameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    CheckBox remoteWorkCheckBox;

    @FXML
    private ObservableList<String> classes = FXCollections.observableArrayList("Programmer", "Manager", "Designer", "Tester");

    @FXML
    private ObservableList<Engineer.Quality> qualities = FXCollections.observableArrayList(Senior, Middle, Junior);

    @FXML
    private ObservableList<Manager.ManageType> manages = FXCollections.observableArrayList(AccountManager, SalesManager, FinanceManager, AdvertasingManager, ProjectManager);

    @FXML
    private ObservableList<Programmer.ProgType> types = FXCollections.observableArrayList(Backend, Frontend, FullStack, Programmer.ProgType.Web, Game_Developer, Android, iOS);

    @FXML
    private ObservableList<Designer.DesignType> des_types = FXCollections.observableArrayList(Game, Designer.DesignType.Web, Graphic);


    @FXML
    private ObservableList<Tester.TestType> spheres = FXCollections.observableArrayList(Automated, Manual);


    public void CheckStr(String oldValue, String newValue, TextField d) {
        String regDate = "[a-zA-Z]*";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else
            d.setText(newValue);
    }

    public void CheckEmail(String oldValue, String newValue, TextField d) {
        String regDate = "[a-zA-Z0-9@-_\\.]*";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else
            d.setText(newValue);
    }

    public void CheckInt(String oldValue, String newValue, TextField d) {
        String regDate = "([1-9]+[0-9]*)|(^)";
        Pattern pattern = Pattern.compile(regDate);
        if (!pattern.matcher(newValue).matches())
            d.setText(oldValue);
        else
            d.setText(newValue);
    }

    @FXML
    private void initialize() {
        initData();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdditionalInfo().getPublishingCompany()));
        remoteWorkColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getAdditionalInfo().is_remote_work()));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>(DATE));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));
        tableEditions.setItems(editionData);
        classComboBox.setItems(classes);
        qualityComboBox.setItems(qualities);
        sphereComboBox.setItems(spheres);
        typeComboBox.setItems(types);
        DesignerComboBox.setItems(des_types);
        manageComboBox.setItems(manages);
        classComboBox.getSelectionModel().select("Programmer");
        manageLabel.setVisible(false);
        manageComboBox.setVisible(false);
        typeLabel.setVisible(true);
        typeComboBox.setVisible(true);
        educationLabel.setVisible(true);
        educationCheckBox.setVisible(true);
        authorLabel.setVisible(false);
        authorTextField.setVisible(false);
        pageNumLabel.setVisible(false);
        pageNumTextField.setVisible(false);
        officeLabel.setVisible(true);
        officeTextField.setVisible(true);
        qualityLabel.setVisible(true);
        qualityComboBox.setVisible(true);
        sphereLabel.setVisible(false);
        DesignerComboBox.setVisible(false);
        sphereComboBox.setVisible(false);
        {
            nameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, nameTextField);
            });
            emailTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckEmail(oldValue, newValue, emailTextField);
            });
            authorTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckStr(oldValue, newValue, authorTextField);
            });
            pageNumTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, pageNumTextField);
            });
            officeTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                CheckInt(oldValue, newValue, officeTextField);
            });
        }
    }

    private void initData()
    {
        editionData.add(new Programmer("Ivan", LocalDate.of(1945, 4, 15),"ivanov@gmail.com", true, Backend,true, 123, Junior));
    }

    @FXML
    private void showRow()
    {
        Employee edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition.getClass() == Programmer.class){
            classComboBox.setValue("Programmer");
            nameTextField.setText(edition.getName());
            emailTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            remoteWorkCheckBox.setSelected(edition.getAdditionalInfo().is_remote_work());
            startWorkDatePicker.setValue(edition.getStartDate());
            manageLabel.setVisible(false);
            manageComboBox.setVisible(false);
            typeLabel.setVisible(true);
            typeComboBox.setVisible(true);
            educationLabel.setVisible(true);
            educationCheckBox.setVisible(true);
            educationCheckBox.setSelected(((Programmer) edition).isHigher_education());
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            officeLabel.setVisible(true);
            officeTextField.setVisible(true);
            qualityLabel.setVisible(true);
            qualityComboBox.setVisible(true);
            sphereLabel.setVisible(false);
            DesignerComboBox.setVisible(false);
            sphereComboBox.setVisible(false);
            officeTextField.setText(Integer.toString(((Programmer)edition).getOffice(), 10));
            qualityComboBox.setValue(((Programmer)edition).getQuality());
            typeComboBox.setValue(((Programmer)edition).getType());
        }
        if (edition.getClass() == Manager.class){
            classComboBox.setValue("Manager");
            nameTextField.setText(edition.getName());
            emailTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            remoteWorkCheckBox.setSelected(edition.getAdditionalInfo().is_remote_work());
            startWorkDatePicker.setValue(edition.getStartDate());
            manageLabel.setVisible(false);
            manageComboBox.setVisible(true);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            educationLabel.setVisible(false);
            educationCheckBox.setVisible(false);
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            officeLabel.setVisible(true);
            officeTextField.setVisible(true);
            qualityLabel.setVisible(false);
            qualityComboBox.setVisible(false);
            sphereLabel.setVisible(true);
            sphereComboBox.setVisible(false);
            manageComboBox.setValue(((Manager)edition).getManageType());
            officeTextField.setText(Integer.toString(((Manager)edition).getOffice(), 10));
            DesignerComboBox.setVisible(false);
        }
        if (edition.getClass() == Designer.class){
            classComboBox.setValue("Designer");
            nameTextField.setText(edition.getName());
            emailTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            remoteWorkCheckBox.setSelected(edition.getAdditionalInfo().is_remote_work());
            startWorkDatePicker.setValue(edition.getStartDate());
            manageLabel.setVisible(false);
            manageComboBox.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            educationLabel.setVisible(false);
            educationCheckBox.setVisible(false);
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            officeLabel.setVisible(true);
            officeTextField.setVisible(true);
            qualityLabel.setVisible(true);
            qualityComboBox.setVisible(true);
            sphereLabel.setVisible(true);
            sphereComboBox.setVisible(false);
            DesignerComboBox.setVisible(true);
            DesignerComboBox.setValue(((Designer)edition).getType());
            officeTextField.setText(Integer.toString(((Designer)edition).getOffice(), 10));
            qualityComboBox.setValue(((Designer)edition).getQuality());
        }
        if (edition.getClass() == Tester.class){
            classComboBox.setValue("Tester");
            nameTextField.setText(edition.getName());
            emailTextField.setText(edition.getAdditionalInfo().getPublishingCompany());
            remoteWorkCheckBox.setSelected(edition.getAdditionalInfo().is_remote_work());
            startWorkDatePicker.setValue(edition.getStartDate());
            manageLabel.setVisible(false);
            manageComboBox.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
            educationLabel.setVisible(false);
            educationCheckBox.setVisible(false);
            authorLabel.setVisible(false);
            authorTextField.setVisible(false);
            pageNumLabel.setVisible(false);
            pageNumTextField.setVisible(false);
            officeLabel.setVisible(true);
            officeTextField.setVisible(true);
            qualityLabel.setVisible(true);
            qualityComboBox.setVisible(true);
            sphereLabel.setVisible(true);
            sphereComboBox.setVisible(true);
            sphereComboBox.setValue(((Tester)edition).getType());
            DesignerComboBox.setVisible(false);
            officeTextField.setText(Integer.toString(((Tester)edition).getOffice(), 10));
            qualityComboBox.setValue(((Tester)edition).getQuality());
        }
    }

    @FXML
    private void deleteRow()
    {
        Employee edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nothing selected!");
            alert.showAndWait();
        }
        else{
            int selectedIndex = tableEditions.getSelectionModel().getSelectedIndex();
            editionData.remove(selectedIndex);
            tableEditions.refresh();
        }
    }

    @FXML
    private void generateFields() {
        switch (classComboBox.getValue()) {
            case "Programmer": {
                nameTextField.setText("");
                emailTextField.setText("");
                remoteWorkCheckBox.setSelected(false);
                startWorkDatePicker.setValue(null);
                manageLabel.setVisible(false);
                manageComboBox.setVisible(false);
                typeLabel.setVisible(true);
                typeComboBox.setVisible(true);
                educationLabel.setVisible(true);
                educationCheckBox.setVisible(true);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                officeLabel.setVisible(true);
                officeTextField.setVisible(true);
                qualityLabel.setVisible(true);
                qualityComboBox.setVisible(true);
                sphereLabel.setVisible(false);
                sphereComboBox.setVisible(false);
                DesignerComboBox.setVisible(false);
                authorTextField.setText("");
                pageNumTextField.setText("");
                officeTextField.setText("");
                qualityComboBox.setValue(null);
                break;
            }
            case "Designer": {
                nameTextField.setText("");
                emailTextField.setText("");
                remoteWorkCheckBox.setSelected(false);
                startWorkDatePicker.setValue(null);
                manageLabel.setVisible(false);
                manageComboBox.setVisible(false);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                educationLabel.setVisible(false);
                educationCheckBox.setVisible(false);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                officeLabel.setVisible(true);
                officeTextField.setVisible(true);
                qualityLabel.setVisible(true);
                qualityComboBox.setVisible(true);
                sphereLabel.setVisible(true);
                sphereComboBox.setVisible(false);
                DesignerComboBox.setVisible(true);
                DesignerComboBox.setValue(null);
                authorTextField.setText("");
                pageNumTextField.setText("");
                officeTextField.setText("");
                qualityComboBox.setValue(null);
                break;
            }
            case "Manager": {
                nameTextField.setText("");
                emailTextField.setText("");
                remoteWorkCheckBox.setSelected(false);
                startWorkDatePicker.setValue(null);
                manageLabel.setVisible(false);
                manageComboBox.setVisible(true);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                educationLabel.setVisible(false);
                educationCheckBox.setVisible(false);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                officeTextField.setVisible(true);
                officeTextField.setText("");
                officeLabel.setVisible(true);
                qualityLabel.setVisible(false);
                qualityComboBox.setVisible(false);
                sphereLabel.setVisible(true);
                sphereComboBox.setVisible(false);
                manageComboBox.setValue(null);
                DesignerComboBox.setVisible(false);
                break;
            }
            case "Tester": {
                nameTextField.setText("");
                emailTextField.setText("");
                remoteWorkCheckBox.setSelected(false);
                startWorkDatePicker.setValue(null);
                manageLabel.setVisible(false);
                manageComboBox.setVisible(false);
                typeLabel.setVisible(false);
                typeComboBox.setVisible(false);
                educationLabel.setVisible(false);
                educationCheckBox.setVisible(false);
                authorLabel.setVisible(false);
                authorTextField.setVisible(false);
                pageNumLabel.setVisible(false);
                pageNumTextField.setVisible(false);
                officeLabel.setVisible(true);
                officeTextField.setVisible(true);
                qualityLabel.setVisible(true);
                qualityComboBox.setVisible(true);
                sphereLabel.setVisible(true);
                sphereComboBox.setVisible(true);
                sphereComboBox.setValue(null);
                DesignerComboBox.setVisible(false);
                authorTextField.setText("");
                pageNumTextField.setText("");
                officeTextField.setText("");
                qualityComboBox.setValue(null);
                break;
            }
        }
    }

    @FXML
    public void saveNewRec()
    {
        Employee edition = tableEditions.getSelectionModel().getSelectedItem();
        if (edition == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nothing selected!");
            alert.showAndWait();
        }
        else {
            String name = nameTextField.getText();
            String publishingCompany = emailTextField.getText();
            boolean takeawayPermission = remoteWorkCheckBox.isSelected();
            LocalDate publishingDate = startWorkDatePicker.getValue();
            switch (classComboBox.getValue()) {
                case "Programmer": {
                    Programmer.ProgType type = typeComboBox.getValue();
                    boolean isColorful = educationCheckBox.isSelected();
                    Programmer.Quality quality = qualityComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (type == null) || (officeTextField.getText().equals(""))){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int rage = Integer.parseInt(officeTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.setStartDate(publishingDate);
                        ((Programmer) edition).setType(type);
                        ((Programmer) edition).setColorful(isColorful);

                        ((Programmer) edition).setQuality(quality);
                        ((Programmer) edition).setOffice(rage);
                    }
                    break;
                }


                case "Manager": {
                    Manager.ManageType manageType = manageComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (publishingDate == null) || (manageType == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int rage = Integer.parseInt(officeTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.setStartDate(publishingDate);
                        ((Manager) edition).setManageType(manageType);
                        ((Manager) edition).setOffice(rage);
                    }
                    break;
                }
                case "Designer": {
                    Designer.DesignType type = DesignerComboBox.getValue();
                    Designer.Quality quality = qualityComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (quality == null) || (publishingDate == null) || (type == null) || (officeTextField.getText().equals(""))){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int rage = Integer.parseInt(officeTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.setStartDate(publishingDate);
                        ((Designer) edition).setType(type);

                        ((Designer) edition).setQuality(quality);
                        ((Designer) edition).setOffice(rage);
                    }
                    break;
                }
                case "Tester": {
                    Tester.TestType type = sphereComboBox.getValue();
                    if ((name.equals("")) || (publishingCompany.equals("")) || (publishingDate == null) || (officeTextField.getText().equals("")) || (type == null)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Please, enter all information!!!");
                        alert.showAndWait();
                    }
                    else {
                        int rage = Integer.parseInt(officeTextField.getText());
                        edition.setName(name);
                        edition.getAdditionalInfo().setPublishingCompany(publishingCompany);
                        edition.getAdditionalInfo().setTakeawayPermission(takeawayPermission);
                        edition.setStartDate(publishingDate);
                        ((Tester) edition).setType(type);
                        ((Tester) edition).setOffice(rage);
                    }
                    break;
                }
            }
        }
        tableEditions.refresh();
    }

    @FXML
    public void createNewRec()
    {
        String name = nameTextField.getText();
        String publishingCompany = emailTextField.getText();
        boolean takeawayPermission = remoteWorkCheckBox.isSelected();
        LocalDate startDate = startWorkDatePicker.getValue();
        switch (classComboBox.getValue()) {
            case "Programmer": {
                Programmer.ProgType type = typeComboBox.getValue();
                Designer.Quality quality = qualityComboBox.getValue();
                boolean isColorful = educationCheckBox.isSelected();
                if ((name.equals("")) || (publishingCompany.equals("")) || (startDate == null) || (type == null) || (officeTextField.getText().equals("")) || (quality == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    int rage = Integer.parseInt(officeTextField.getText());
                    Programmer newspaper = new Programmer(name, startDate, publishingCompany, takeawayPermission, type, isColorful, rage, quality);
                    editionData.add(newspaper);
                }
                break;
            }

            case "Manager":{
                Manager.ManageType manageType = manageComboBox.getValue();
                int rage = Integer.parseInt(officeTextField.getText());
                if ((name.equals("")) || (publishingCompany.equals("")) || (startDate == null) || (manageType == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    Manager manager = new Manager(name, startDate, publishingCompany, takeawayPermission, manageType, rage);
                    editionData.add(manager);
                }
                break;
            }
            case "Designer":{
                Designer.Quality quality = qualityComboBox.getValue();
                Designer.DesignType type = DesignerComboBox.getValue();
                if ((name.equals("")) || (publishingCompany.equals("")) || (startDate == null) || (officeTextField.getText().equals("")) || (quality == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    int rage = Integer.parseInt(officeTextField.getText());
                    Designer fictionBook = new Designer(name, startDate, publishingCompany, takeawayPermission, rage, quality, type);
                    editionData.add(fictionBook);
                }
                break;
            }
            case "Tester":{
                Tester.Quality quality = qualityComboBox.getValue();
                Tester.TestType type = sphereComboBox.getValue();
                if ((name.equals("")) || (publishingCompany.equals("")) || (startDate == null) || (officeTextField.getText().equals("")) || (type == null) || (quality == null)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please, enter all information!!!");
                    alert.showAndWait();
                }
                else {
                    int rage = Integer.parseInt(officeTextField.getText());
                    Tester tester = new Tester(name, startDate, publishingCompany, takeawayPermission, rage, quality, type);
                    editionData.add(tester);
                }
                break;
            }
        }
        tableEditions.refresh();
    }
}