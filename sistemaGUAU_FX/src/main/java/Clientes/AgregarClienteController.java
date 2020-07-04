package Clientes;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José Sánchez
 */
public class AgregarClienteController implements Initializable {

    @FXML
    Button btnGuardar;
    @FXML
    private TextField txtNIT;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private CheckBox cbMayorista;
    @FXML
    private Button btnVaciarCampos;
    @FXML
    private Button btnCancelar;
    
    
    private  final ObservableList<Cliente> ClienteList =   FXCollections.observableArrayList();
    private final ClienteJPO ClienteJPO = new ClienteJPO();
    
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClienteList.addAll(ClienteJPO.getClientes());
    }    
    
    
    @FXML
    public void regresar(ActionEvent event) throws IOException{
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormClientes.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("MEVECOM <>");
        stage.setResizable(false);
        stage.show();
        //Cerrar ventana actual
        Stage actual = (Stage) btnGuardar.getScene().getWindow();
        actual.close();
    }
    
     @FXML
    private void btnGuardarHandle(ActionEvent event) 
    {
        if (!txtNombre.getText().isEmpty()) 
        {
            Cliente newCliente = new Cliente();
            newCliente.setNombre(txtNombre.getText());
            newCliente.setNit(txtNIT.getText());
            newCliente.setTelefono(txtTelefono.getText());
            newCliente.setDireccion(txtDireccion.getText());
            newCliente.setMayorista(true);
            
            try 
            {
                ClienteJPO.AgregarCliente(newCliente);
            } 
            catch (Exception ex) {
                Logger.getLogger(AgregarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ClienteList.add(newCliente);
        }
        
    }

    @FXML
    private void btnVaciarCamposHandle(ActionEvent event) {
    }
    
}
