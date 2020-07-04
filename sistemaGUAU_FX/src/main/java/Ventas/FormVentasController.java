package Ventas;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormVentasController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnEditar;
    @FXML
    private TableView<Factura> tblVentas;
    public TableColumn<Factura, Integer> IdProducto;
    public TableColumn<Factura, String> NombreProducto;
    public TableColumn<Factura, String> FechaVenta;
    public TableColumn<Factura, Float> TotalVenta;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        tblVentas.setItems(getdata());
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
    
    //Inicializacion de cada columna de la tabla
    @FXML
    public void initCol(){
        IdProducto.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        NombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        FechaVenta.setCellValueFactory(new PropertyValueFactory<>(""));
        TotalVenta.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
    
    @FXML
    private ObservableList<Factura> data;
    @FXML
    private DatosVentas dataVentas;
    
    //Llenar la tabla de datos
    @FXML
    public ObservableList<Factura> getdata(){
        data = FXCollections.observableArrayList(dataVentas.getFactura());
        if(data == null){
            return FXCollections.observableArrayList();
        } else
            return data;
    }
    
}
