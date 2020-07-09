package Ventas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Clientes.AgregarClienteController;
import Compras.ComprasDAO;
import Compras.DetalleCompraDAO;
import Inventario.ProductoDAO;
import JPA.Cliente;
import JPA.DetalleCompra;
import JPA.DetalleFactura;
import JPA.Factura;
import JPA.Producto;
import java.io.FileWriter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class RegistrarVentaController implements Initializable {

    @FXML
    private TextField txtCantidad, txtDescuento;
    @FXML
    private Button btnAgregar, btnEliminar, btnCancelar, btnGuardar, btnRegresar;
    @FXML
    private ComboBox<?> cmbCliente;
    @FXML
    private Label labelTotal;
    private ProductoDAO inventario_dao = new ProductoDAO();
    
    //Tabla para mostrar los productos
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> colProducto;
    @FXML
    private TableColumn<Producto, Float> colPeso;
    @FXML
    private TableColumn<Producto, Float> colPrecio;
    @FXML
    private TableColumn<Producto, Float> colExistencias;
    
    //Tabla final de la venta
    @FXML
    private TableView<Producto> tblVenta;
    @FXML
    private TableColumn<DetalleFactura, Producto> colProducto2;
    @FXML
    private TableColumn<DetalleFactura, Float> colPrecio2;
    @FXML
    private TableColumn<DetalleFactura, Float> colCantidad2;
    @FXML
    private TableColumn<DetalleFactura, Float> colTotal2;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso")); 
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio")); 
        colExistencias.setCellValueFactory(new PropertyValueFactory<>("existencias")); 
        List<Producto> lista_productos = inventario_dao.getProductos(); 
        for(Iterator<Producto> iterator = lista_productos.iterator(); iterator.hasNext();) { 
            Producto next = iterator.next(); 
            modelo_productos.add(next); 
        } 
        tblProductos.setItems(modelo_productos); 
    }
    private ObservableList<Producto> modelo_productos = FXCollections.observableArrayList();
    
    
    //Obtiene el producto seleccionado y lo agrega en la tabla de factura
    @FXML
    private void agregarProducto(ActionEvent event) throws IOException {
        
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
