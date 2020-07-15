package Compras;


import Inventario.ProductoDAO;
import JPA.DetalleCompra;
import JPA.DetalleFactura;
import JPA.Producto;
import JPA.Proveedor;
import JPA.ReciboCompra;
import Proveedores.ProveedorDAO;
import Ventas.TablaVentas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author izasj
 */
public class RevisarCompraController implements Initializable {

    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXTextField txtProveedor, txtTotal;
    
    //Tabla para mostrar compra seleccionada
    @FXML
    private TableView<TablaVentas> tblCompra;
    @FXML
    private TableColumn<TablaVentas, String> colProducto;
    @FXML
    private TableColumn<TablaVentas, Float> colPrecio;
    @FXML
    private TableColumn<TablaVentas, Float> colCantidad;
    @FXML
    private TableColumn<TablaVentas, Float> colTotal;
    
    private ComprasDAO recibo_dao = new ComprasDAO();
    private DetalleReciboDAO detallerecibo_dao = new DetalleReciboDAO();
    private ProductoDAO producto_dao = new ProductoDAO();
    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    
    private ObservableList<DetalleCompra> modelo_detallerecibos = FXCollections.observableArrayList(); 
    private ObservableList<Producto> modelo_producto = FXCollections.observableArrayList(); 
    private ObservableList<TablaVentas> mostrarProductos = FXCollections.observableArrayList(); 
    
    private ReciboCompra recibo;
    private Proveedor proveedor;
    private Producto producto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtProveedor.setLabelFloat(true);
        txtTotal.setLabelFloat(true);
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto")); 
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto")); 
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total")); 
        int id2 = 0;
        try (FileReader fileReader = new FileReader("recibo.txt")) {
            int id = fileReader.read();
            id2 = id;
            this.recibo = recibo_dao.getReciboByID(id);
            this.proveedor = proveedor_dao.getProveedorByID(this.recibo.getProveedorid().getIdProveedor());
            txtProveedor.setText(this.proveedor.getEmpresa());
            txtTotal.setText(String.valueOf(this.recibo.getTotal()));
            //Obtener cantidad de detalle de facturas con el ID de la factura
            List<DetalleCompra> cantidad = detallerecibo_dao.getDetalleRecibo();
            for (Iterator<DetalleCompra> iterator = cantidad.iterator(); iterator.hasNext();) {
                DetalleCompra next = iterator.next(); 
                if(next.getReciboid().getIdRecibo() == id2){
                    Producto producto_enviar = producto_dao.getProductoByID(next.getProductoid().getIdProducto());
                    mostrarProductos.add(new TablaVentas (next.getProductoid().getIdProducto(), producto_enviar.getNombre(), producto_enviar.getPrecio(), next.getCantidad(), next.getSubtotal()));
                    tblCompra.setItems(mostrarProductos);
                }
            } 
        } catch (FileNotFoundException e) {
            System.out.println("No");
        } catch (IOException e) {
            System.out.println("Noup");
        }
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
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
    
}
