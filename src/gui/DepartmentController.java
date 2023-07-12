package gui;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.entities.Department;
import model.services.DepartmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    private DepartmentService service;

    @FXML private TableView<Department> tableViewDepartment;

    @FXML private TableColumn<Department, Integer> tableColumnId;

    @FXML private TableColumn<Department, String> tableColumnName;

    @FXML private Button btNew;

    private ObservableList<Department> observableList;

    @FXML
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        createDialogForm("/gui/DepartmentForm.fxml", parentStage);
    }

    public void setDepartmentService(DepartmentService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Stage stage = (Stage) Main.getScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }

    public void createDialogForm(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(pane));

            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            dialogStage.showAndWait();
        } catch (IOException e) {
            Alerts.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null!");
        }
        List<Department> list = service.findAll();
        observableList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(observableList);
    }
}
