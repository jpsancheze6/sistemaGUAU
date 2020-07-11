package Ventas;

import static Inventario.InventarioController.showException;
import JPA.Factura;
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
import javafx.scene.control.Button;
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
    private Button btnRegistrar, btnEditar, btnRegresar;
    
    private VentasDAO factura_dao = new VentasDAO();
    private DetalleFacturaDAO detalleFactura_dao = new DetalleFacturaDAO();
    private ObservableList<Factura> modelo_facturas = FXCollections.observableArrayList(); 
    private Factura factura;
    
    //Tabla para mostra las ventas
    @FXML
    private TableView<Factura> tblVentas;
    @FXML
    private TableColumn<Factura, Integer> colID;
    @FXML
    private TableColumn<Factura, String> colCliente;
    @FXML
    private TableColumn<Factura, Date> colFecha;
    @FXML
    private TableColumn<Factura, Float> colTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarElementos();
    }    
   
    public void agregarElementos(){
        colID.setCellValueFactory(new PropertyValueFactory<>("idFactura")); 
        colCliente.setCellValueFactory(new PropertyValueFactory<>("clienteid")); 
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total")); 
 
        List<Factura> lista_recibos = factura_dao.getFacturas(); 
 
        for(Iterator<Factura> iterator = lista_recibos.iterator(); iterator.hasNext();) { 
            Factura next = iterator.next(); 
            modelo_facturas.add(next); 
        } 
        tblVentas.setItems(modelo_facturas); 
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
            Factura factura_seleccionada = (Factura) tblVentas.getSelectionModel().getSelectedItem();
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
        Stage actual = (Stage) btnRegresar.getScene().getWindow();
        actual.close();
    }
    
}
