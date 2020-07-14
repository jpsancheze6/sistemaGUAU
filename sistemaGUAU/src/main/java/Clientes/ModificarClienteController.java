/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import JPA.Cliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class ModificarClienteController implements Initializable {

    @FXML
    private JFXTextField txtNIT;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtTIpoCliente;
    @FXML
    private JFXCheckBox cbMayorista;
    @FXML
    private JFXButton btnActualizar;
    @FXML
    private JFXButton btnVaciarCampos;
    @FXML
    private JFXButton btnCancelar;

    private Cliente cliente;

    private ClienteDAO cliente_dao = new ClienteDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNIT.setLabelFloat(true);
        txtNombre.setLabelFloat(true);
        txtDireccion.setLabelFloat(true);
        txtTelefono.setLabelFloat(true);
        txtTIpoCliente.setLabelFloat(true);
        try ( FileReader fileReader = new FileReader("cliente.txt")) {
            int id = fileReader.read();
            this.cliente = cliente_dao.getClienteByID(id);
            txtNIT.setText(this.cliente.getNit());
            txtNombre.setText(this.cliente.getNombre());
            txtDireccion.setText(this.cliente.getDireccion());
            txtTelefono.setText(this.cliente.getTelefono());
            txtTIpoCliente.setText(this.cliente.getTipocliente());
            if (this.cliente.getMayorista()) {
                cbMayorista.setSelected(true);
            }
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

    public static void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Información");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Advertencia");
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
    public void actualizarCliente() {
        String nit = txtNIT.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String tipo_cliente = txtTIpoCliente.getText();
        boolean mayorista = cbMayorista.isSelected();

        if (nit.length() != 0 && nombre.length() != 0 && telefono.length() != 0 && direccion.length() != 0 && tipo_cliente.length() != 0) {

            if (valiSoloLetrasyEspacios(txtNombre.getText()).equals(true)) {

                if (valiSoloNumeros(txtTelefono.getText())) {

                    cliente.setDireccion(direccion);
                    cliente.setMayorista(mayorista);
                    cliente.setNit(nit);
                    cliente.setNombre(nombre);
                    cliente.setTelefono(telefono);
                    cliente.setTipocliente(tipo_cliente);
                    try {
                        cliente_dao.EditarCliente(cliente);
                        showInformation("Correcto", "Cliente actualizado con éxito.");
                        regresar(new ActionEvent());
                    } catch (Exception ex) {
                        showError("Error al Actualizar", "Debido a un error no se puedo Actualizar el Cliente");
                    }
                    
                } else {
                    showError("Error de Validacion", "El numero de telefono NO puede contener letras o simbolos");
                }

            } else {
                showError("Error de Validacion", "El nombre NO puede contener espacios o simbolos");
            }
        } else {
            showWarning("Error", "Por favor llene todos los campos.");
        }

    }

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormClientes.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnActualizar.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void limpiarCampos(ActionEvent event) {
        this.txtNIT.setText("");
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.txtDireccion.setText("");
        this.txtTIpoCliente.setText("");
        this.cbMayorista.setSelected(false);
    }

}
