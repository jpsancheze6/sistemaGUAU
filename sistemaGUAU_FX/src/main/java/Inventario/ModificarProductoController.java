/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ModificarProductoController implements Initializable {

    @FXML
    private AnchorPane APaneModificarProducto;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNit;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtMarca1;
    @FXML
    private ComboBox<?> cmbProveedor;
    @FXML
    private RadioButton rbtnHabilitar;
    @FXML
    private RadioButton rbtnDeshabilitar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) {
    }

    @FXML
    private void btnRegresarHandle(ActionEvent event) throws IOException 
    {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Inventario/Inventario.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnRegresar.getScene().getWindow();
        actual.close();
    }

    @FXML
    private void cmbProveedor(ActionEvent event) {
    }
    
}
