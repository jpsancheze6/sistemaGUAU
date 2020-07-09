/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import JPA.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private Button btnCrearCliente;
    @FXML
    private Button btnEditarCliente;
    @FXML
    private Button btnDeshabilitarCliente;
    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Cliente, Integer> id;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> telefono;
    @FXML
    private TableColumn<Cliente, String> direccion;

    private ClienteDAO cliente_dao = new ClienteDAO();

    /**
     * Initializes the controller class.
     */
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
        try {
            Cliente cliente_seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            Cliente cliente_enviar = cliente_dao.getClienteByID(cliente_seleccionado.getIdCliente());
            try (FileWriter fileWriter = new FileWriter("cliente.txt")) {
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
            //} catch (Exception e) {
            //    System.out.println(e);
            //}
        } catch (Exception e) {
            showException("Error", "Por favor seleccione un cliente", e);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        List<Cliente> lista_clientes = cliente_dao.getClientes();

        for (Iterator<Cliente> iterator = lista_clientes.iterator(); iterator.hasNext();) {
            Cliente next = iterator.next();
            modelo_clientes.add(next);
        }

        tblClientes.setItems(modelo_clientes);

    }

    private ObservableList<Cliente> modelo_clientes = FXCollections.observableArrayList();

}
