package Ventas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormVentasController implements Initializable {

    @FXML
    private TextField txtProducto, txtCantidad, txtDescuento;
    @FXML
    private Button btnBuscar, btnAgregar, btnEliminar, btnCancelar, btnGuardar;
    @FXML
    private TableView<?> tblProductos;
    @FXML
    private DatePicker Fecha;
    @FXML
    private ComboBox<?> cmbCliente;
    @FXML
    private TableView<?> tblVenta;
    @FXML
    private Label labelTotal;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
