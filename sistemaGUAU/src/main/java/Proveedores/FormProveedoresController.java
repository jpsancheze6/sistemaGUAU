/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import JPA.Proveedor;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private TableView<Proveedor> tblProveedores;
    @FXML
    private TableColumn<Proveedor, Integer> Id;
    @FXML
    private TableColumn<Proveedor, String> Nombre;
    @FXML
    private TableColumn<Proveedor, String> Telefono;
    @FXML
    private TableColumn<Proveedor, String> Empresa;

    private ProveedorDAO proveedor_dao = new ProveedorDAO();

    private List<Proveedor> lista_proveedores = proveedor_dao.getProveedor();
    
    private ObservableList<Proveedor> modelo_Usuarios = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //-------------------------------------------------- Llenado de  Columnas
        Id.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        Empresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        
        //Llenado de tabla
        lista_proveedores.forEach((next) -> {
            modelo_Usuarios.add(next);
        });

        tblProveedores.setItems(modelo_Usuarios);
    }
   
   //----------------------------------------------------------------------------------------Agregar

    @FXML
    private void btnCrearHandle(ActionEvent event) throws IOException {
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
//----------------------------------------------------------------------------------------Modificar

    @FXML
    private void btnModificarHandle(ActionEvent event) throws IOException {

        if (!tblProveedores.getSelectionModel().isEmpty()) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Modificar Proveedor?", "Alerta!", JOptionPane.YES_OPTION);
            if (resp == 0) {               
                Proveedor proveedor_seleccionado = tblProveedores.getSelectionModel().getSelectedItem();
                Proveedor proveedor_enviar = proveedor_dao.getProveedorByID(proveedor_seleccionado.getIdProveedor());
                try ( FileWriter fileWriter = new FileWriter("proveedor.txt")) {
                    fileWriter.write(proveedor_enviar.getIdProveedor());
                    fileWriter.close();
                } catch (IOException e) {
                    // Exception handling
                }
                //LLamar a la nueva ventana
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

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    //----------------------------------------------------------------------------------------Regresar
    @FXML
    private void btnRegresarHandle(ActionEvent event) throws IOException {
        //Llamar una nueva ventana
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

    //----------------------------------------------------------------------------------------Eliminar
    @FXML
    private void btnEliminarHandle(ActionEvent event) {
        if (!tblProveedores.getSelectionModel().isEmpty()) {
            Proveedor proveedor = tblProveedores.getSelectionModel().getSelectedItem();

            try {
                int resp = JOptionPane.showConfirmDialog(null, "¿Borrar Proveedor?", "Alerta!", JOptionPane.YES_NO_OPTION);

                if (resp == 0) {
                    proveedor_dao.EliminarProveedor(proveedor.getIdProveedor());
                    modelo_Usuarios.remove(proveedor);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al Borrar" + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
             JOptionPane.showMessageDialog(null, "Seleccione un proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
