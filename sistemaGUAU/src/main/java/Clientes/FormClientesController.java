/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import JPA.Cliente;
import com.jfoenix.controls.JFXButton;
import java.awt.Robot;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private JFXButton btnCrearCliente;
    @FXML
    private JFXButton btnEditarCliente;
    @FXML
    private JFXButton btnDeshabilitarCliente;
    @FXML
    private JFXButton btnRegresar;

    @FXML
    private TableColumn<Cliente, Integer> id;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> telefono;
    @FXML
    private TableColumn<Cliente, String> direccion;

    private ClienteDAO cliente_dao = new ClienteDAO();

    private ObservableList<Cliente> modelo_clientes = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public static String showConfirm(String title, String message, String... options) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("CONFIRMAR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        //To make enter key press the actual focused button, not the first one. Just like pressing "space".
        alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                try {
                    Robot r = new Robot();
                    r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
                    r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        List<ButtonType> buttons = new ArrayList<>();
        for (String option : options) {
            buttons.add(new ButtonType(option));
        }

        alert.getButtonTypes().setAll(buttons);

        Optional<ButtonType> result = alert.showAndWait();
        String Op = result.get().getText();
        if (result.get().getText().equals("Si")) {
            return "Si";
        } else {
            return "No";
        }
    }

    //---------------------------------------------------------------Mensaje de Error
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("ERROR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showException(String title, String message, Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Excepción");
        alert.setHeaderText(title);
        alert.setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Detalles:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        List<Cliente> lista_clientes = cliente_dao.getClientes();

        lista_clientes.forEach((next) -> {
            modelo_clientes.add(next);
        });
        tblClientes.setItems(modelo_clientes);

    }

    @FXML
    public void crearCliente(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void editarCliente(ActionEvent event) throws IOException {
        if (!tblClientes.getSelectionModel().isEmpty()) {

            String Respuesta = showConfirm("¿Modificar Cliente?", null, "Si", "No");

            if (Respuesta.equals("Si")) {
                Cliente cliente_seleccionado = tblClientes.getSelectionModel().getSelectedItem();
                Cliente cliente_enviar = cliente_dao.getClienteByID(cliente_seleccionado.getIdCliente());
                try ( FileWriter fileWriter = new FileWriter("cliente.txt")) {
                    fileWriter.write(cliente_enviar.getIdCliente());
                    fileWriter.close();
                } catch (IOException e) {
                    // Exception handling
                }
                //Llamar a una nueva ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModificarCliente.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("MEVECOM <>");
                stage.setResizable(false);
                stage.show();
                //Cerrar ventana actual
                Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
                actual.close();

            }
        } else {
            showError("Seleccione un Cliente", null);
        }

    }

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principal/FormPrincipal.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
        actual.close();
    }

}
