package gui;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.DepartmentService;
import model.services.SellerService;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SellerFormController implements Initializable {

    private Seller entity;

    private SellerService sellerService;

    private DepartmentService departmentService;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dpBirthDate;
    @FXML private TextField txtBaseSalary;
    @FXML private Label labelErrorName;
    @FXML private ComboBox<Department> comboDepartment;
    @FXML private Label labelErrorEmail;
    @FXML private Label labelErrorBirthDate;
    @FXML private Label labelErrorBaseSalary;

    @FXML private Button btSave;
    @FXML private Button btCancel;

    private ObservableList<Department> observableList;

    public void setSeller(Seller entity) {
        this.entity = entity;
    }

    public void setService(SellerService sellerService, DepartmentService departmentService) {
        this.sellerService = sellerService;
        this.departmentService = departmentService;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        if (sellerService == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            entity = getFormData();
            sellerService.saveOrUpdate(entity);

            notifyDataChangeListeners();

            Utils.currentStage(event).close();
        } catch (ValidationException e) {
            setErrorMessages(e.getError());
        } catch (DbException e) {
            Alerts.showAlert("Erro ao adicionar o departamento", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 40);
        Constraints.setTextFieldMaxLength(txtEmail, 55);
        Constraints.setTextFieldDouble(txtBaseSalary);
        Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
        initializeComboBoxDepartment();
    }

    private void initializeComboBoxDepartment() {
        Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        comboDepartment.setCellFactory(factory);
        comboDepartment.setButtonCell(factory.call(null));
    }

    private Seller getFormData() {
        Seller obj = new Seller();
        ValidationException exception = new ValidationException("Validation error");
        obj.setId(Utils.tryParseToInt(txtId.getText()));

        if (txtName.getText() == null ||  txtName.getText().trim().equals("")) {
            exception.addError("name", "O campo não pode ser vazio!");
        }
        obj.setName(txtName.getText());

        if (txtEmail.getText() == null ||  txtName.getText().trim().equals("")) {
            exception.addError("email", "O campo não pode ser vazio!");
        }
        obj.setEmail(txtEmail.getText());

        if (dpBirthDate.getValue() == null) {
            exception.addError("birthDate", "O campo não pode ser vazio!");
        } else {
            Instant instant = Instant.from(dpBirthDate.getValue().atStartOfDay(ZoneId.systemDefault()));
            obj.setBirthDate(Date.from(instant));
        }

        if (txtBaseSalary.getText() == null ||  txtBaseSalary.getText().trim().equals("")) {
            exception.addError("baseSalary", "O campo não pode ser vazio!");
        }
        obj.setBaseSalary(Utils.tryParseToDouble(txtBaseSalary.getText()));

        obj.setDepartment(comboDepartment.getValue());

        if (exception.getError().size() > 0) {
            throw exception;
        }

        return obj;
    }

    public void updateFormData() {
        Locale.setDefault(Locale.US);
        if (entity == null) {
            throw new IllegalStateException("Entity was null.");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
        txtEmail.setText(entity.getEmail());
        if (entity.getBirthDate() != null) {
            dpBirthDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
        }
        txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));

        if (entity.getDepartment() == null) {
            comboDepartment.getSelectionModel().selectFirst();
        } else {
            comboDepartment.setValue(entity.getDepartment());
        }
    }

    public void loadAssociatedObjects() {
        if (departmentService == null) {
            throw new IllegalStateException("DepartmentService was null");
        }
        List<Department> list = departmentService.findAll();
        observableList = FXCollections.observableList(list);
        comboDepartment.setItems(observableList);
    }

    private void setErrorMessages(Map<String, String> error) {
        Set<String> fields = error.keySet();

        labelErrorName.setText((fields.contains("name") ? error.get("name") : ""));
        labelErrorEmail.setText((fields.contains("email") ? error.get("email") : ""));
        labelErrorBirthDate.setText((fields.contains("birthDate") ? error.get("birthDate") : ""));
        labelErrorBaseSalary.setText((fields.contains("baseSalary") ? error.get("baseSalary") : ""));
    }
}
