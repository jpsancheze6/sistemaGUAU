/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import JPA.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class AgregarClienteController implements Initializable {

    @FXML
    private TextField txtNIT;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private CheckBox cbMayorista;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVaciarCampos;
    @FXML
    private TextField txtTIpoCliente;
    @FXML
    private Button btnCancelar;

    private ClienteDAO cliente_dao = new ClienteDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void guardarCliente(ActionEvent event) {
        String nit = txtNIT.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String tipo_cliente = txtTIpoCliente.getText();
        boolean mayorista = cbMayorista.isSelected();

        if (nit.length() != 0 && nombre.length() != 0 && telefono.length() != 0 && direccion.length() != 0 && tipo_cliente.length() != 0) {
            Cliente a = new Cliente();
            a.setDireccion(direccion);
            a.setMayorista(mayorista);
            a.setNit(nit);
            a.setNombre(nombre);
            a.setTelefono(telefono);
            a.setTipocliente(tipo_cliente);
            try {
                cliente_dao.AgregarCliente(a);
                showInformation("Realizado", "Cliente registrado con éxito.");
                regresar(event);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            showWarning("Campos incompletos", "Por favor llene todos los campos.");
        }

    }

    public static void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Información");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Advertencia");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    public void limpiarCampos(ActionEvent event) {
        this.txtNIT.setText("");
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.txtDireccion.setText("");
        this.txtTIpoCliente.setText("");
        this.cbMayorista.setSelected(false);
    }

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormClientes.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnGuardar.getScene().getWindow();
        actual.close();
    }

}
