package Compras;


import Inventario.ProductoDAO;
import JPA.DetalleCompra;
import JPA.Producto;
import JPA.Proveedor;
import JPA.ReciboCompra;
import Proveedores.ProveedorDAO;
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
public class RevisarCompraController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtProveedor, txtTotal;
    
    private ComprasDAO recibo_dao = new ComprasDAO();
    private DetalleReciboDAO detallerecibo_dao = new DetalleReciboDAO();
    private ProductoDAO producto_dao = new ProductoDAO();
    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    
    private ObservableList<DetalleCompra> modelo_detallerecibos = FXCollections.observableArrayList(); 
    private ObservableList<Producto> modelo_producto = FXCollections.observableArrayList(); 
    
    private ReciboCompra recibo;
    private Proveedor proveedor;
    private Producto producto;
    
    //Tabla para mostrar compra seleccionada
    @FXML
    private TableView<DetalleCompra> tblCompra;
    @FXML
    private TableColumn<DetalleCompra, String> colProducto;
    @FXML
    private TableColumn<DetalleCompra, Float> colPrecio;
    @FXML
    private TableColumn<DetalleCompra, Float> colCantidad;
    @FXML
    private TableColumn<DetalleCompra, Float> colTotal;
    

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
        try (FileReader fileReader = new FileReader("recibo.txt")) {
            int id = fileReader.read();
            id2 = id;
            this.recibo = recibo_dao.getReciboByID(id);
            this.proveedor = proveedor_dao.getProveedorByID(this.recibo.getProveedorid().getIdProveedor());
            txtProveedor.setText(this.proveedor.getNombre());
            txtTotal.setText(String.valueOf(this.recibo.getTotal()));
        } catch (FileNotFoundException e) {
            System.out.println("No");
        } catch (IOException e) {
            System.out.println("Noup");
        }

        List<DetalleCompra> lista_detallerecibo = detallerecibo_dao.getDetalleRecibo();
        for (Iterator<DetalleCompra> iterator = lista_detallerecibo.iterator(); iterator.hasNext();) {
            DetalleCompra next = iterator.next(); 
            if(next.getReciboid().getIdRecibo() == id2){
                modelo_detallerecibos.add(next);
            } 
        }
        tblCompra.setItems(modelo_detallerecibos);
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
