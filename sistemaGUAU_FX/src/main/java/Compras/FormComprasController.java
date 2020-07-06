package Compras;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import sistemaguau.SistemaGUAU;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormComprasController implements Initializable {

    public EntityManager em;
    ObservableList<TablaCompras> data;
    
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnEditar;
    @FXML
    private TableView<TablaCompras> tblCompras;
    public TableColumn<TablaCompras, Number> colId;
    public TableColumn<TablaCompras, Number> colProveedor;
    public TableColumn<TablaCompras, Date> colFecha;
    public TableColumn<TablaCompras, Number> colTotal;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = SistemaGUAU.emf.createEntityManager();
        List<TablaCompras> lstCompras = em.createQuery("SELECT f FROM TablaCompras f").getResultList();
        //
        for(TablaCompras lst1 : lstCompras){
            data.add(new TablaCompras(lst1.getIdRecibo(), lst1.getProveedor_id(), lst1.getFecha(), lst1.getTotal()));
        }
        tblCompras.setItems(data);
        colId.setCellValueFactory(cell -> cell.getValue().getIdReciboProperty());
        colProveedor.setCellValueFactory(cell -> cell.getValue().getProveedor_idProperty());
        colFecha.setCellValueFactory(cell -> cell.getValue().getFechaProperty());
        colTotal.setCellValueFactory(cell -> cell.getValue().getTotalProperty());
    }
    
    @FXML
    public void registrarCompra(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrarCompra.fxml"));
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
    public void editarCompra(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModificarCompra.fxml"));
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
