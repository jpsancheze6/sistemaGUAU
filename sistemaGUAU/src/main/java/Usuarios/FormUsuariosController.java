/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import JPA.Usuario;
import com.jfoenix.controls.JFXButton;
import java.awt.Robot;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class FormUsuariosController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario, Integer> Id;
    @FXML
    private TableColumn<Usuario, String> Nombre;
    @FXML
    private TableColumn<Usuario, String> Usuario;

    private UsuarioDAO usuario_dao = new UsuarioDAO();

    private List<Usuario> lista_usuarios = usuario_dao.getUsuarios();

    private ObservableList<Usuario> modelo_Usuarios = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    //---------------------------------------------------------------Mensaje de Confimarcion
    public static String showConfirm(String title, String message, String... options) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle("CONFIRMAR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        //To make enter key press the actual focused button, not the first one. Just like pressing "space".
        alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                try {
                    Robot r = new Robot();
                    r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
                    r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        List<ButtonType> buttons = new ArrayList<>();
        for (String option : options) {
            buttons.add(new ButtonType(option));
        }

        alert.getButtonTypes().setAll(buttons);

        Optional<ButtonType> result = alert.showAndWait();
        String Op = result.get().getText();
        if (result.get().getText().equals("Si")) {
            return "Si";
        } else {
            return "No";
        }
    }

    //---------------------------------------------------------------Mensaje de Error
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("ERROR");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Id.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        Usuario.setCellValueFactory(new PropertyValueFactory<>("nombreusuario"));

        lista_usuarios.forEach((next) -> {
            modelo_Usuarios.add(next);
        });

        tblUsuarios.setItems(modelo_Usuarios);

    }

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

        if (!tblUsuarios.getSelectionModel().isEmpty()) {
            
            String Respuesta = showConfirm("¿Modificar Usuario?", null, "Si", "No");
            
            if (Respuesta.equals("Si")) {
                Usuario usuario_seleccionado = tblUsuarios.getSelectionModel().getSelectedItem();
                Usuario usuario_enviar = usuario_dao.getUsuarioByID(usuario_seleccionado.getIdUsuario());
                try ( FileWriter fileWriter = new FileWriter("usuario.txt")) {
                    fileWriter.write(usuario_enviar.getIdUsuario());
                    fileWriter.close();
                } catch (IOException e) {
                    // Exception handling
                }
                // Llamar a una nueva ventana 
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

        } else {
            showError("Seleccione un Usuario", null);
        }

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
        if (!tblUsuarios.getSelectionModel().isEmpty()) {
            Usuario usuario = tblUsuarios.getSelectionModel().getSelectedItem();

            try {
                String Respuesta = showConfirm("¿Eliminar Usuario?", null, "Si", "No");

                if (Respuesta.equals("Si")) {
                    usuario_dao.EliminarUsuario(usuario.getIdUsuario());
                    modelo_Usuarios.remove(usuario);
                }
            } catch (Exception ex) {
                showError("Error al borrar", "Debido a un error no se puedo borrar el usuario");
            }

        } else {
            showError("Seleccione un Usuario", null);

        }
    }

}
