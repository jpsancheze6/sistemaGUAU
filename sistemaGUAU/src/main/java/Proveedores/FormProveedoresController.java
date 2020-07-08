/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

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
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class FormProveedoresController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<?> tblProveedores;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
        
    }    

    @FXML
    private void btnCrearHandle(ActionEvent event) throws IOException 
    {
       // Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Proveedores/AgregarProveedor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCrear.getScene().getWindow();
        actual.close();
    }

    @FXML
    private void btnModificarHandle(ActionEvent event) throws IOException 
    {
          //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Proveedores/ModificarProveedor.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnModificar.getScene().getWindow();
        actual.close();
    }


    @FXML
    private void btnRegresarHandle(ActionEvent event) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Principal/FormPrincipal.fxml"));
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
    private void btnEliminarHandle(ActionEvent event) {
    }
    
}