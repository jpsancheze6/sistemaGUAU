/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private ComboBox<?> cmbProducto;
    @FXML
    private AnchorPane AgregarProveedor;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmpresa;
    
     private  final ObservableList<Proveedor> ProveedorList =   FXCollections.observableArrayList();
     private final ProveedorJPO ProveedorJPO = new ProveedorJPO();
    @FXML
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarHandle(ActionEvent event) throws Exception
    {
        if (!txtNombre.getText().isEmpty()) 
        {
            Proveedor newProveedor= new Proveedor();
            newProveedor.setNombre(txtNombre.getText());
            newProveedor.setTelefono(txtTelefono.getText());
            newProveedor.setEmpresa(txtEmpresa.getText());
            newProveedor.setEmpresa(cmbProducto.getSelectionModel().getSelectedItem().toString());
            
            
            ProveedorJPO.AgregarProveedor(newProveedor);
            
            ProveedorList.add(newProveedor);
        }
        
     
    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtEmpresa.setText("");
        
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) throws IOException 
    {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Proveedores/Proveedores.fxml"));
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
