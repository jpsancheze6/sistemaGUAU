/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import JPA.Usuario;
import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class FormUsuariosController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<Usuario, Integer> Id;
    @FXML
    private TableColumn<Usuario, String> Nombre;
    @FXML
    private TableColumn<Usuario, String> Usuario;

    private UsuarioDAO usuario_dao = new UsuarioDAO();
    @FXML
    private TableView<Usuario> tblUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Id.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Usuario.setCellValueFactory(new PropertyValueFactory<>("nombreusuario"));

        List<Usuario> lista_usuarios = usuario_dao.getUsuarios();

        for (Iterator<Usuario> iterator = lista_usuarios.iterator(); iterator.hasNext();) {
            Usuario next = iterator.next();
            modelo_Usuarios.add(next);
        }

        tblUsuarios.setItems(modelo_Usuarios);

    }

    private ObservableList<Usuario> modelo_Usuarios = FXCollections.observableArrayList();

    @FXML
    private void btnCrearHandle(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/AgregarUsuario.fxml"));
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
    private void btnModificarHandle(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Usuarios/ModificarUsuario.fxml"));
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
    private void btnRegresarHandle(ActionEvent event) throws IOException {
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
