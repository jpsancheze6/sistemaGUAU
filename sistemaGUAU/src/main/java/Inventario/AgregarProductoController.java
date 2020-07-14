package Inventario;

import JPA.Producto;
import JPA.Proveedor;
import Proveedores.ProveedorDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class AgregarProductoController implements Initializable {

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtExistencias;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private JFXTextField txtAnimal;
    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtPeso;
    @FXML
    private JFXComboBox cmbUnidad;
    @FXML
    private JFXComboBox<String> cmbProveedor;

    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    private ProductoDAO producto_dao = new ProductoDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAnimal.setLabelFloat(true);
        txtExistencias.setLabelFloat(true);
        txtMarca.setLabelFloat(true);
        txtNombre.setLabelFloat(true);
        txtPeso.setLabelFloat(true);
        txtPrecio.setLabelFloat(true);
        cmbUnidad.setLabelFloat(true);
        // Agregar proveedores
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Proveedor> a = proveedor_dao.getProveedor();
        for (Iterator<Proveedor> iterator = a.iterator(); iterator.hasNext();) {
            Proveedor next = iterator.next();
            String texto = next.getIdProveedor() + "-" + next.getEmpresa();
            items.add(texto);
        }
        cmbProveedor.setItems(items);
        ObservableList<String> unidades = FXCollections.observableArrayList();
        unidades.add("Seleccione unidad");
        unidades.add("Libras");
        unidades.add("Kilogramos");
        unidades.add("Quintales");
        unidades.add("Bolsas");
        cmbUnidad.setItems(unidades);
    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
        String nombre = txtNombre.getText();
        String existencias = txtExistencias.getText(); //
        String precio = txtPrecio.getText(); //
        String animal = txtAnimal.getText();
        String marca = txtMarca.getText();
        String peso = txtPeso.getText(); //
        String unidad_referencia = cmbUnidad.getSelectionModel().getSelectedItem().toString();
        int seleccion = cmbProveedor.getSelectionModel().getSelectedIndex(); // -1 && >=0

        if (nombre.length() != 0 && existencias.length() != 0 && precio.length() != 0 && animal.length() != 0 && marca.length() != 0 && peso.length() != 0 && seleccion >= 0 && cmbUnidad.getSelectionModel().getSelectedIndex() != 0) {
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setExistencias(Float.parseFloat(existencias));
            producto.setPrecio(Float.parseFloat(precio));
            producto.setTipoanimal(animal);
            producto.setMarca(marca);
            producto.setPeso(Float.parseFloat(peso));
            producto.setUnidadreferencia(unidad_referencia);
            String[] a = cmbProveedor.getSelectionModel().getSelectedItem().split("-");
            int id_proveedor = Integer.parseInt(a[0]);
            producto.setProveedorid(proveedor_dao.getProveedorByID(id_proveedor));
            producto.setDisponibilidad(true);
            try {
                producto_dao.AgregarProducto(producto);
                btnCancelarHandle(new ActionEvent());
                showInformation("Terminado", "Producto registrado con éxito");
            } catch (Exception ex) {
                Logger.getLogger(AgregarProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            showWarning("Datos incompletos", "Por favor llene todos los campos");
        }

    }

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Advertencia");
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

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
        txtNombre.setText("");
        txtExistencias.setText(""); //
        txtPrecio.setText(""); //
        txtAnimal.setText("");
        txtMarca.setText("");
        txtPeso.setText(""); //
        cmbUnidad.getSelectionModel().select(0);
        cmbProveedor.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnCancelarHandle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Inventario/Inventario.fxml"));
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

    @FXML
    private void cmbProveedor(ActionEvent event) {
    }

}
