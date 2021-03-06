package Ventas;


import Clientes.ClienteDAO;
import Inventario.AgregarProductoController;
import static Inventario.AgregarProductoController.showInformation;
import static Inventario.AgregarProductoController.showWarning;
import static Inventario.InventarioController.showException;
import Inventario.ProductoDAO;
import JPA.Cliente;
import JPA.DetalleFactura;
import JPA.Factura;
import JPA.Producto;
import JPA.Proveedor;
import JPA.Usuario;
import Proveedores.ProveedorDAO;
import Usuarios.UsuarioDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author izasj
 */
public class RegistrarVentaController implements Initializable {

    @FXML
    private JFXTextField txtCantidad, txtDescuento;
    @FXML
    private JFXButton btnBuscar, btnAgregar, btnEliminar, btnCancelar, btnGuardar, btnRegresar;
    @FXML
    private JFXComboBox<String> cmbCliente;
    @FXML
    private JFXComboBox<String> cmbProveedor;
    @FXML
    private JFXComboBox<String> cmbUsuario;
    @FXML
    private Label labelTotal, lblInformacion, lblInformacion2;
    
    private VentasDAO factura_dao = new VentasDAO();
    private DetalleFacturaDAO detallefactura_dao = new DetalleFacturaDAO();
    private ProductoDAO producto_dao = new ProductoDAO();
    private ClienteDAO cliente_dao = new ClienteDAO();
    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    private UsuarioDAO usuario_dao = new UsuarioDAO();
    private TablaVentas tablaventas_dao;
    
    private ObservableList<DetalleFactura> modelo_detallefacturas = FXCollections.observableArrayList(); 
    private ObservableList<Producto> modelo_producto = FXCollections.observableArrayList(); 
    private ObservableList<TablaVentas> productosVenta = FXCollections.observableArrayList(); 
    
    private float finalP, descuentoP;
    
    //Tabla para mostrar los productos
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> colProducto;
    @FXML
    private TableColumn<Producto, Float> colPrecio;
    @FXML
    private TableColumn<Producto, Float> colExistencias;
    
    //Tabla final de la venta
    @FXML
    private TableView<TablaVentas> tblVenta;
    @FXML
    private TableColumn<TablaVentas, String> colProducto2;
    @FXML
    private TableColumn<TablaVentas, Float> colPrecio2;
    @FXML
    private TableColumn<TablaVentas, Float> colCantidad2;
    @FXML
    private TableColumn<TablaVentas, Float> colTotal2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblInformacion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                String title = "Instrucciones";
                String message = "Primero tienes que seleccionar el proveedor y dale en buscar\n"
                               + "Después seleccionas el producto, ingresas la cantidad y lo agregas.\n"
                               + "Cuando hayas terminado, seleccionas el usuario, el cliente y le das en Guardar.";
                showInformation(title, message);
            }
        });
        lblInformacion2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                String title = "Eliminar producto";
                String message = "Para eliminar el producto, tienes que seleccionarlo, y después le das en Eliminar Producto";
                showInformation(title, message);
            }
        });
        txtCantidad.setLabelFloat(true);
        txtDescuento.setLabelFloat(true);
        //Agregar proveedores al comboBox
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Proveedor> a = proveedor_dao.getProveedor();
        for (Iterator<Proveedor> iterator = a.iterator(); iterator.hasNext();) {
            Proveedor next = iterator.next();
            String texto = next.getIdProveedor() + "-" + next.getEmpresa();
            items.add(texto);
        }
        cmbProveedor.setItems(items);
        
        //Agregar clientes al combo Box
        ObservableList<String> items2 = FXCollections.observableArrayList();
        List<Cliente> a2 = cliente_dao.getClientes();
        for (Iterator<Cliente> iterator = a2.iterator(); iterator.hasNext();) {
            Cliente next = iterator.next();
            String texto = next.getIdCliente()+ "-" + next.getNombre();
            items2.add(texto);
        }
        cmbCliente.setItems(items2);
        
        //Agregar usuarios al combo Box
        ObservableList<String> items3 = FXCollections.observableArrayList();
        List<Usuario> a3 = usuario_dao.getUsuarios();
        for (Iterator<Usuario> iterator = a3.iterator(); iterator.hasNext();) {
            Usuario next = iterator.next();
            String texto = next.getIdUsuario()+ "-" + next.getNombreusuario();
            items3.add(texto);
        }
        cmbUsuario.setItems(items3);
    }    
    
    //Muestran los productos del proveedor
    @FXML
    private void mostrarProductos(ActionEvent event){
        mostrarProducto2();
    }
    
    public void mostrarProducto2(){
        tblProductos.getItems().clear();
        colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio")); 
        colExistencias.setCellValueFactory(new PropertyValueFactory<>("existencias")); 
        int seleccion = cmbProveedor.getSelectionModel().getSelectedIndex() + 1; // -1 && >=0
        List<Producto> lista_producto = producto_dao.getProductos();
        for (Iterator<Producto> iterator = lista_producto.iterator(); iterator.hasNext();) {
            Producto next = iterator.next(); 
            if(next.getProveedorid().getIdProveedor() == seleccion)
                modelo_producto.add(next);
        }
        tblProductos.setItems(modelo_producto);
    }
    
    //Agregan los productos a la factura
    @FXML
    private void agregarProductos(ActionEvent event){
        colProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto")); 
        colPrecio2.setCellValueFactory(new PropertyValueFactory<>("precioProducto")); 
        colCantidad2.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        colTotal2.setCellValueFactory(new PropertyValueFactory<>("total")); 
        try {
            Producto producto_seleccionado = (Producto) tblProductos.getSelectionModel().getSelectedItem();
            Producto producto_enviar = producto_dao.getProductoByID(producto_seleccionado.getIdProducto());
            int idP = producto_enviar.getIdProducto();
            String nombreP = producto_enviar.getNombre();
            float precioP = producto_enviar.getPrecio();
            float cantidadP = Integer.valueOf(txtCantidad.getText());
            float existencias = producto_enviar.getExistencias();
            if(cantidadP < existencias){
                float totalP = precioP*cantidadP;
                finalP = finalP + totalP;
                productosVenta.add(new TablaVentas(idP, nombreP, precioP, cantidadP, totalP));
                tblVenta.setItems(productosVenta);
                txtCantidad.setText("");   
                labelTotal.setText("Total: " + String.valueOf(finalP));
                //Actualizar existencias
                float existenciasP = existencias-cantidadP;
                producto_enviar.setExistencias(existenciasP);
                producto_dao.EditarProducto(producto_enviar);
                mostrarProducto2();
            } else
                showWarning("ERROR", "La cantidad es mayor que las existencias disponibles");
            } catch (Exception e) {
                showException("Error", "Por favor seleccione un producto.", e);
            }
    }
    
    //Eliminar productos de la factura
    @FXML
    private void eliminarProductos(ActionEvent event) throws Exception{
        colProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto")); 
        colPrecio2.setCellValueFactory(new PropertyValueFactory<>("precioProducto")); 
        colCantidad2.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        colTotal2.setCellValueFactory(new PropertyValueFactory<>("total")); 
        TablaVentas producto_seleccionado = (TablaVentas) tblVenta.getSelectionModel().getSelectedItem();
        Producto producto_enviar = producto_dao.getProductoByID(producto_seleccionado.getIdProducto());
        float precio = producto_enviar.getPrecio();
        float cantidad = producto_seleccionado.getCantidad();
        finalP = finalP - (precio*cantidad);
        System.out.println(finalP);
        labelTotal.setText("");
        labelTotal.setText("Total: " + String.valueOf(finalP));
        tblVenta.getItems().removeAll(tblVenta.getSelectionModel().getSelectedItem());
        //Actualizar Existencias
        float existencias = producto_enviar.getExistencias();
        float existenciasP = existencias+cantidad;
        producto_enviar.setExistencias(existenciasP);
        producto_dao.EditarProducto(producto_enviar);
        mostrarProducto2();
    }
    
    //Guarda los datos en la factura
    @FXML
    private void guardar(ActionEvent event) throws IOException {
        int seleccionUsuario = cmbUsuario.getSelectionModel().getSelectedIndex() + 1; // -1 && >=0
        int seleccionCliente = cmbCliente.getSelectionModel().getSelectedIndex() + 1;
        descuentoP = Integer.valueOf(txtDescuento.getText());
        DetalleFactura nuevoDetalle = new DetalleFactura();
        if(descuentoP < finalP)
            finalP = finalP - descuentoP;
        Date fechaP = new Date();
        if (seleccionUsuario != 0 && seleccionCliente != 0 && finalP != 0) {
            Factura nuevaFactura = new Factura();
            String[] a = cmbCliente.getSelectionModel().getSelectedItem().split("-");
            int id_cliente = Integer.parseInt(a[0]);
            nuevaFactura.setClienteid(cliente_dao.getClienteByID(id_cliente));
            nuevaFactura.setDescuento(descuentoP);
            nuevaFactura.setTotal(finalP);
            nuevaFactura.setConcepto("");
            nuevaFactura.setFecha(fechaP);
            String[] a2 = cmbUsuario.getSelectionModel().getSelectedItem().split("-");
            int id_usuario = Integer.parseInt(a2[0]);
            nuevaFactura.setUsuarioidUsuario(usuario_dao.getUsuarioByID(id_usuario));
            try {
                factura_dao.AgregarFactura(nuevaFactura);
                for(int i = 0; i < tblVenta.getItems().size(); i++) {
                    tablaventas_dao = tblVenta.getItems().get(i);
                    nuevoDetalle.setFacturaid(factura_dao.getFacturaByID(nuevaFactura.getIdFactura()));
                    nuevoDetalle.setProductoid(producto_dao.getProductoByID(tablaventas_dao.getIdProducto()));
                    nuevoDetalle.setCantidad(tablaventas_dao.getCantidad());
                    nuevoDetalle.setSubtotal(tablaventas_dao.getTotal());
                    detallefactura_dao.AgregarDetalleFactura(nuevoDetalle);
                }
                cancelar(new ActionEvent());
                showInformation("Terminado", "Factura registrada con éxito");
            } catch (Exception ex) {
                Logger.getLogger(AgregarProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            showWarning("Datos incompletos", "Por favor llene todos los campos");
        }
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
        Stage actual = (Stage) labelTotal.getScene().getWindow();
        actual.close();
    }
    
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Ventas/FormVentas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) labelTotal.getScene().getWindow();
        actual.close();
    }
    
}
