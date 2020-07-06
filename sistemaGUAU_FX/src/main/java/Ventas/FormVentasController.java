package Ventas;

import Transaccion.Factura;
import Transaccion.FacturaJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import sistemaguau.SistemaGUAU;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormVentasController implements Initializable {

    public EntityManager em;
    ObservableList<TablaVentas> data;
    
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnEditar;
    @FXML
    private TableView<TablaVentas> tblVentas;
    public TableColumn<TablaVentas, Number> colId;
    public TableColumn<TablaVentas, Number> colCliente;
    public TableColumn<TablaVentas, Date> colFecha;
    public TableColumn<TablaVentas, Number> colTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = SistemaGUAU.emf.createEntityManager();
        List<TablaVentas> lstFactura = em.createQuery("SELECT f FROM TablaVentas f").getResultList();
        //
        for(TablaVentas lst1 : lstFactura){
            data.add(new TablaVentas(lst1.getIdFactura(), lst1.getCliente_id(), lst1.getFecha(), lst1.getTotal()));
        }
        tblVentas.setItems(data);
        colId.setCellValueFactory(cell -> cell.getValue().getIdFacturaProperty());
        colCliente.setCellValueFactory(cell -> cell.getValue().getCliente_idProperty());
        colFecha.setCellValueFactory(cell -> cell.getValue().getFechaProperty());
        colTotal.setCellValueFactory(cell -> cell.getValue().getTotalProperty());
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
    private void modificarVenta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModificarVenta.fxml"));
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
    
    @FXML
    public void regresar(ActionEvent event) throws IOException {
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
