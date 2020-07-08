package Inventario;

import JPA.Producto;
import JPA.Proveedor;
import Proveedores.ProveedorDAO;
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
public class AgregarProductoController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtExistencias;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtAnimal;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtReferencia;

    @FXML
    private ComboBox<String> cmbProveedor;

    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    private ProductoDAO producto_dao = new ProductoDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Agregar proveedores
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Proveedor> a = proveedor_dao.getProveedor();
        for (Iterator<Proveedor> iterator = a.iterator(); iterator.hasNext();) {
            Proveedor next = iterator.next();
            String texto = next.getIdProveedor() + "-" + next.getEmpresa();
            items.add(texto);
        }
        cmbProveedor.setItems(items);
    }

    @FXML
    private void btnGuardarHandle(ActionEvent event) {
        String nombre = txtNombre.getText();
        String existencias = txtExistencias.getText(); //
        String precio = txtPrecio.getText(); //
        String animal = txtAnimal.getText();
        String marca = txtMarca.getText();
        String peso = txtPeso.getText(); //
        String unidad_referencia = txtReferencia.getText();
        int seleccion = cmbProveedor.getSelectionModel().getSelectedIndex(); // -1 && >=0

        if (nombre.length() != 0 && existencias.length() != 0 && precio.length() != 0 && animal.length() != 0 && marca.length() != 0 && peso.length() != 0 && seleccion >= 0 && unidad_referencia.length() != 0) {
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
            } catch (Exception ex) {
                Logger.getLogger(AgregarProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void btnLimpiarHandle(ActionEvent event) {
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
