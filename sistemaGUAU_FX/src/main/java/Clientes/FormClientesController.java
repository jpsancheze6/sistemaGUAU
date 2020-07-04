package Clientes;

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
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class FormClientesController implements Initializable {

    @FXML
    Button btnCrearCliente;
    @FXML
    private TableView<?> tblClientes;
    @FXML
    private Button btnEditarCliente;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;
    
    private  final ObservableList<Cliente> ClienteList =   FXCollections.observableArrayList();
    private final ClienteJPO ClienteJPO = new ClienteJPO();
    


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // ClienteList.addAll(ClienteJPO.getClientes());
    }

    
    
    @FXML
    public void crearCliente(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
        actual.close();
    }

    @FXML
    public void editarCliente(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModificarCliente.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
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
        Stage actual = (Stage) btnCrearCliente.getScene().getWindow();
        actual.close();
    }

    
    @FXML
    private void btnEliminarHandle(ActionEvent event) throws Exception 
    {
       Cliente selecteditem = (Cliente) tblClientes.getSelectionModel().getSelectedItem();
       
        if (selecteditem!=null) 
        {
            ClienteJPO.EliminarCliente(selecteditem.getIdCliente());
            ClienteList.remove(selecteditem);
        }
    }

}
