/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import JPA.Proveedor;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ModificarProveedorController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmpresa;
    @FXML
    private Label labelTitulo;

    private Proveedor proveedor;

    private ProveedorDAO proveedor_dao = new ProveedorDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try ( FileReader fileReader = new FileReader("proveedor.txt")) {
            int id = fileReader.read();
            proveedor = proveedor_dao.getProveedorByID(id);
            txtNombre.setText(this.proveedor.getNombre());
            txtTelefono.setText(this.proveedor.getTelefono());
            txtEmpresa.setText(this.proveedor.getEmpresa());
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }
    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
        if ((!txtNombre.getText().isEmpty()) && (!txtTelefono.getText().isEmpty()) && (!txtEmpresa.getText().isEmpty())) {

            proveedor.setNombre(txtNombre.getText());
            proveedor.setTelefono(txtTelefono.getText());
            proveedor.setEmpresa(txtEmpresa.getText());

            try {
                proveedor_dao.EditarProveedor(proveedor);
                btnCancelarHandle(event);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al Actualizar" + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmpresa.setText("");
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Proveedores/FormProveedores.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCancelar.getScene().getWindow();
        actual.close();
    }

}
