package Ventas;

import Clientes.ClienteDAO;
import Inventario.ProductoDAO;
import JPA.Cliente;
import JPA.DetalleFactura;
import JPA.Factura;
import JPA.Producto;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author izasj
 */
public class RevisarVentaController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtCliente, txtTotal;
    
    private VentasDAO factura_dao = new VentasDAO();
    private DetalleFacturaDAO detallefactura_dao = new DetalleFacturaDAO();
    private ProductoDAO producto_dao = new ProductoDAO();
    private ClienteDAO cliente_dao = new ClienteDAO();
    
    private ObservableList<DetalleFactura> modelo_detallefacturas = FXCollections.observableArrayList(); 
    private ObservableList<Producto> modelo_producto = FXCollections.observableArrayList(); 
    
    private Factura factura;
    private Cliente cliente;
    private Producto producto;
    
    //Tabla de la venta seleccionada
    @FXML
    private TableView<DetalleFactura> tblVenta;
    @FXML
    private TableColumn<DetalleFactura, Producto> colProducto;
    @FXML
    private TableColumn<DetalleFactura, Float> colPrecio;
    @FXML
    private TableColumn<DetalleFactura, Float> colCantidad;
    @FXML
    private TableColumn<DetalleFactura, Float> colTotal;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colProducto.setCellValueFactory(new PropertyValueFactory<>("productoid")); 
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("subtotal")); 
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal")); 
        int id2 = 0;
        try (FileReader fileReader = new FileReader("factura.txt")) {
            int id = fileReader.read();
            id2 = id;
            this.factura = factura_dao.getFacturaByID(id);
            this.cliente = cliente_dao.getClienteByID(this.factura.getClienteid().getIdCliente());
            txtCliente.setText(this.cliente.getNombre());
            txtTotal.setText(String.valueOf(this.factura.getTotal()));
        } catch (FileNotFoundException e) {
            System.out.println("No");
        } catch (IOException e) {
            System.out.println("Noup");
        }

        List<DetalleFactura> lista_detallefactura = detallefactura_dao.getDetalleFacturas();
        for (Iterator<DetalleFactura> iterator = lista_detallefactura.iterator(); iterator.hasNext();) {
            DetalleFactura next = iterator.next(); 
            if(next.getFacturaid().getIdFactura() == id2)
                modelo_detallefacturas.add(next);
        }
        tblVenta.setItems(modelo_detallefacturas);
    }    
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Ventas/FormVentas.fxml"));
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
    
}
