/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import JPA.Proveedor;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class AgregarProveedorController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
        if ((!txtNombre.getText().isEmpty()) && (!txtTelefono.getText().isEmpty()) && (!txtEmpresa.getText().isEmpty())) {

            ProveedorDAO proveedor_dao = new ProveedorDAO();
            Proveedor newProveedor = new Proveedor();
            newProveedor.setNombre(txtNombre.getText());
            newProveedor.setTelefono(txtTelefono.getText());
            newProveedor.setEmpresa(txtEmpresa.getText());

            try {
                proveedor_dao.AgregarProveedor(newProveedor);
                btnCancelarHandle(event);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al ingresar" + ex, "Error", JOptionPane.ERROR_MESSAGE);
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
//         //Llamar newProveedor una nueva ventana
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

    @FXML
    private void txtNombreTyped(KeyEvent event) {
        char[] string = event.getCharacter().toCharArray();

        for (char c : string) {
            if ((!Character.isLetter(c))&& (!Character.isSpaceChar(c)) ) {
                event.consume();
                JOptionPane.showMessageDialog(null, "Solo se permiten usar letras", "Error", JOptionPane.ERROR_MESSAGE);
               // txtNombre.setText("");
            }
        }
    }

    @FXML
    private void txtTelefonoTyped(KeyEvent event) {
        char[] string = event.getCharacter().toCharArray();

        for (char c : string) {
            if ((!Character.isDigit(c))) {
                event.consume();
                JOptionPane.showMessageDialog(null, "Solo se permiten usar numeros", "Error", JOptionPane.ERROR_MESSAGE);
                //txtTelefono.getText().charAt(c)='s';
            }

        }
    }

    @FXML
    private void txtEmpresaTyped(KeyEvent event) {
        char[] string = event.getCharacter().toCharArray();

        for (char c : string) {
            if ((!Character.isLetter(c)) && (!Character.isDigit(c))) {
                event.consume();
                 JOptionPane.showMessageDialog(null, "Solo se permiten usar letras y numeros", "Error", JOptionPane.ERROR_MESSAGE);
               // txtEmpresa.setText("");
            } 

        }
    }

}
