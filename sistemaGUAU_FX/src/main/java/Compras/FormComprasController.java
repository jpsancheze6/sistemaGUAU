package Compras;

import Ventas.DatosVentas;
import Ventas.Factura;
import java.io.IOException;
import java.net.URL;
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
 * @author José Sánchez
 */
public class FormComprasController implements Initializable {

    @FXML
    Button btnRegistrar;
    @FXML
    private TableView<ReciboCompra> tblCompras;
    public TableColumn<ReciboCompra, Integer> IdCompra;
    public TableColumn<ReciboCompra, String> NombreProducto;
    public TableColumn<ReciboCompra, String> FechaVenta;
    public TableColumn<ReciboCompra, Float> TotalVenta;

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
        Stage actual = (Stage) btnRegistrar.getScene().getWindow();
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
        Stage actual = (Stage) btnRegistrar.getScene().getWindow();
        actual.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        tblCompras.setItems(getdata());
    }

    //Inicializacion de cada columna de la tabla
    @FXML
    public void initCol(){
        IdCompra.setCellValueFactory(new PropertyValueFactory<>("idRecibo"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        FechaVenta.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        TotalVenta.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
    
    @FXML
    private ObservableList<ReciboCompra> data;
    @FXML
    private DatosCompras dataCompras;
    
    //Llenar la tabla de datos
    @FXML
    public ObservableList<ReciboCompra> getdata(){
        data = FXCollections.observableArrayList(dataCompras.getCompra());
        if(data == null){
            return FXCollections.observableArrayList();
        } else
            return data;
    }
}
