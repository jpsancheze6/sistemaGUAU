/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import JPA.Cliente;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class ModificarClienteController implements Initializable {

    @FXML
    private TextField txtNIT;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtTIpoCliente;
    @FXML
    private CheckBox cbMayorista;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnVaciarCampos;
    @FXML
    private Button btnCancelar;

    private Cliente cliente;

    private ClienteDAO cliente_dao = new ClienteDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try (FileReader fileReader = new FileReader("cliente.txt")) {
            int id = fileReader.read();
            this.cliente = cliente_dao.getClienteByID(id);
            txtNIT.setText(this.cliente.getNit());
            txtNombre.setText(this.cliente.getNombre());
            txtDireccion.setText(this.cliente.getDireccion());
            txtTelefono.setText(this.cliente.getTelefono());
            txtTIpoCliente.setText(this.cliente.getTipocliente());
            if (this.cliente.getMayorista()) {
                cbMayorista.setSelected(true);
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
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
    public void actualizarCliente() {
        String nit = txtNIT.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String tipo_cliente = txtTIpoCliente.getText();
        boolean mayorista = cbMayorista.isSelected();

        if (nit.length() != 0 && nombre.length() != 0 && telefono.length() != 0 && direccion.length() != 0 && tipo_cliente.length() != 0) {
            cliente.setDireccion(direccion);
            cliente.setMayorista(mayorista);
            cliente.setNit(nit);
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setTipocliente(tipo_cliente);
            try {
                cliente_dao.EditarCliente(cliente);
                showInformation("Correcto", "Cliente actualizado con éxito.");
                regresar(new ActionEvent());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else{
            showWarning("Error", "Por favor llene todos los campos.");
        }
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
        Stage actual = (Stage) btnActualizar.getScene().getWindow();
        actual.close();
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

}
