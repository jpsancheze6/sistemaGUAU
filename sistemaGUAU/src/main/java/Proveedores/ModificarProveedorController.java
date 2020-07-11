/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import JPA.Proveedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class ModificarProveedorController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtEmpresa;
    @FXML
    private Label labelTitulo;

    private Proveedor proveedor;

    private ProveedorDAO proveedor_dao = new ProveedorDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtEmpresa.setLabelFloat(true);
        txtNombre.setLabelFloat(true);
        txtTelefono.setLabelFloat(true);
        try (FileReader fileReader = new FileReader("proveedor.txt")) {
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

    private Boolean valiSoloLetrasyEspacios(String texto) {
        boolean resp = false;
        char c;
        for (int i = 0; i < texto.length(); i++) {
            c = texto.charAt(i);
            resp = (Character.isLetter(c)) || (Character.isSpaceChar(c));
        }
        return resp;

    }

    private Boolean valiSoloLetrasyNumeros(String texto) {
        boolean resp = false;
        char c;
        for (int i = 0; i < texto.length(); i++) {
            c = texto.charAt(i);
            resp = (Character.isLetter(c)) || (Character.isDigit(c));
        }
        return resp;
    }

    private Boolean valiSoloNumeros(String texto) {
        boolean resp = false;
        char c;
        for (int i = 0; i < texto.length(); i++) {
            c = texto.charAt(i);
            resp = (Character.isDigit(c));
        }
        return resp;
    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {

        if ((!txtNombre.getText().isEmpty()) && (!txtTelefono.getText().isEmpty()) && (!txtEmpresa.getText().isEmpty())) {

            if (valiSoloLetrasyEspacios(txtNombre.getText()).equals(true)) {

                if (valiSoloNumeros(txtTelefono.getText())) {

                    proveedor.setNombre(txtNombre.getText());
                    proveedor.setTelefono(txtTelefono.getText());
                    proveedor.setEmpresa(txtEmpresa.getText());

                    try {
                        proveedor_dao.EditarProveedor(proveedor);
                        btnCancelarHandle(event);
                    } catch (Exception ex) {
                        showError("Error al Actualizar", "Debido a un error no se puedo Actualizar el Proveedor");
                    }
//
                } else {
                    showError("Error de Validacion", "El numero de telefono NO puede contener letras o simbolos");
                }

            } else {
                showError("Error de Validacion", "El nombre NO puede contener espacios o simbolos");
            }

        } else {
            showError("Campos Vacios", "Llenar todos los campos");
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
