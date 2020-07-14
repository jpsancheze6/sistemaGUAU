/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormPrincipalController implements Initializable {

    @FXML
    private JFXButton btnCliente;
    @FXML
    private JFXButton btnProveedores;
    @FXML
    private JFXButton btnCompras;
    @FXML
    private JFXButton btnUsuarios;
    @FXML
    private JFXButton btnVentas;
    @FXML
    private JFXButton btnInventario;
    @FXML
    private JFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCliente.setAlignment(Pos.CENTER_RIGHT);
        btnCompras.setAlignment(Pos.CENTER_RIGHT);
        btnInventario.setAlignment(Pos.CENTER_RIGHT);
        btnProveedores.setAlignment(Pos.CENTER_RIGHT);
        btnUsuarios.setAlignment(Pos.CENTER_RIGHT);
        btnVentas.setAlignment(Pos.CENTER_RIGHT);
    }

    @FXML
    public void cliente(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Clientes/FormClientes.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void compras(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Compras/FormCompras.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void inventario(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Inventario/Inventario.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void proveedores(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Proveedores/FormProveedores.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void usuarios(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/FormUsuarios.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void ventas(ActionEvent e) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Ventas/FormVentas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnSalir.getScene().getWindow();
        actual.close();
    }
    
    @FXML
    public void salir(ActionEvent e){
        System.exit(0);
    }

}
