/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import JPA.Proveedor;
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
public class FormProveedoresController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnEliminar;
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

    private ObservableList<Proveedor> modelo_Proveedores = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
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
        //-------------------------------------------------- Llenado de  Columnas
        Id.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        Empresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));

        //Llenado de tabla
        lista_proveedores.forEach((next) -> {
            modelo_Proveedores.add(next);
        });

        tblProveedores.setItems(modelo_Proveedores);
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
            
            String Respuesta = showConfirm("¿Modificar Proveedor?", null, "Si", "No");
            
            if (Respuesta.equals("Si")) {
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
           showError("Seleccione un Proveedor", null);
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
                 String Respuesta = showConfirm("¿Eliminar Proveedor?", null, "Si", "No");

                if (Respuesta.equals("Si"))  {
                    proveedor_dao.EliminarProveedor(proveedor.getIdProveedor());
                    modelo_Proveedores.remove(proveedor);
                }
            } catch (Exception ex) {
               showError("Error al borrar", "Debido a un error no se puedo borrar el Proveedor");
            }

        } else {
            showError("Seleccione un Proveedor", null);
        }
    }

}
