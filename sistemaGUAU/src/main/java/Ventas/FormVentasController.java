package Ventas;

import Clientes.ClienteDAO;
import static Inventario.InventarioController.showException;
import JPA.Cliente;
import JPA.Factura;
import com.jfoenix.controls.JFXButton;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
public class FormVentasController implements Initializable {

    @FXML
    private JFXButton btnRegistrar, btnEditar, btnRegresar;
    
    private VentasDAO factura_dao = new VentasDAO();
    private ClienteDAO cliente_dao = new ClienteDAO();
    private DetalleFacturaDAO detalleFactura_dao = new DetalleFacturaDAO();
    private ObservableList<Factura> modelo_facturas = FXCollections.observableArrayList(); 
    private ObservableList<TablaMostrar> mostrarFacturas = FXCollections.observableArrayList(); 
    
    //Tabla para mostra las ventas
    @FXML
    private TableView<TablaMostrar> tblVentas;
    @FXML
    private TableColumn<TablaMostrar, Integer> colID;
    @FXML
    private TableColumn<TablaMostrar, String> colCliente;
    @FXML
    private TableColumn<TablaMostrar, Date> colFecha;
    @FXML
    private TableColumn<TablaMostrar, Float> colTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarElementos();
    }    
   
    public void agregarElementos(){
        colID.setCellValueFactory(new PropertyValueFactory<>("idFactura")); 
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente")); 
        colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        List<Factura> f = factura_dao.getFacturas();
        int tamF = f.size();
        int i = 1;
        while(i <= tamF) {
            Factura factura_enviar = factura_dao.getFacturaByID(i);
            Cliente cliente_enviar = cliente_dao.getClienteByID(factura_enviar.getClienteid().getIdCliente());
            mostrarFacturas.add(new TablaMostrar(factura_enviar.getIdFactura(), cliente_enviar.getNombre(),factura_enviar.getFecha(), factura_enviar.getTotal()));
            tblVentas.setItems(mostrarFacturas);
            i++;
        }
    }
    
    @FXML
    private void registrarVenta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrarVenta.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnRegistrar.getScene().getWindow();
        actual.close();
    }

    @FXML
    private void revisarVenta(ActionEvent event) throws IOException {
        try {
            TablaMostrar factura_seleccionada = (TablaMostrar) tblVentas.getSelectionModel().getSelectedItem();
            Factura factura_enviar = factura_dao.getFacturaByID(factura_seleccionada.getIdFactura());
            try (FileWriter fileWriter = new FileWriter("factura.txt")) {
                System.out.println(factura_enviar.getIdFactura());
                fileWriter.write(factura_enviar.getIdFactura());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se pudo guardar");
            }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevisarVenta.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnEditar.getScene().getWindow();
        actual.close();
        } catch (Exception e) {
            showException("Error", "Por favor seleccione una factura.", e);
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
        Stage actual = (Stage) btnEditar.getScene().getWindow();
        actual.close();
    }
    
}
