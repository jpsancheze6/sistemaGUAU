/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import static Inventario.InventarioController.showException;
import JPA.Cliente;
import JPA.Factura;
import JPA.Proveedor;
import JPA.ReciboCompra;
import Proveedores.ProveedorDAO;
import Ventas.TablaMostrar;
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
public class FormComprasController implements Initializable {

    @FXML
    private JFXButton btnRegistrar, btnEditar, btnRegresar;
    
    private ComprasDAO compras_dao = new ComprasDAO(); 
    private ProveedorDAO proveedor_dao = new ProveedorDAO();
    private ObservableList<ReciboCompra> modelo_recibos = FXCollections.observableArrayList(); 
    private ObservableList<TablaMostrar> mostrarFacturas = FXCollections.observableArrayList(); 
    
    //Tabla para mostrar los recibos de compras
    @FXML
    private TableView<TablaMostrar> tblCompras;
    @FXML
    private TableColumn<TablaMostrar, Integer> colID;
    @FXML
    private TableColumn<TablaMostrar, String> colProveedor;
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
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreCliente")); 
        colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total")); 
 
        List<ReciboCompra> f = compras_dao.getRecibos();
        int tamF = f.size();
        int i = 1;
        while(i <= tamF) {
            ReciboCompra recibo_enviar = compras_dao.getReciboByID(i);
            Proveedor proveedor_enviar = proveedor_dao.getProveedorByID(recibo_enviar.getProveedorid().getIdProveedor());
            mostrarFacturas.add(new TablaMostrar(recibo_enviar.getIdRecibo(), proveedor_enviar.getEmpresa(), recibo_enviar.getFecha(), recibo_enviar.getTotal()));
            tblCompras.setItems(mostrarFacturas);
            i++;
        }
    }
    
    @FXML
    private void registrarCompra(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrarCompra.fxml"));
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
    private void revisarCompra(ActionEvent event) throws IOException {
        try {
            TablaMostrar recibo_seleccionado = (TablaMostrar) tblCompras.getSelectionModel().getSelectedItem();
            ReciboCompra recibo_enviar = compras_dao.getReciboByID(recibo_seleccionado.getIdFactura());
            try (FileWriter fileWriter = new FileWriter("recibo.txt")) {
                System.out.println(recibo_enviar.getIdRecibo());
                fileWriter.write(recibo_enviar.getIdRecibo());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se pudo guardar");
            }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevisarCompra.fxml"));
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
            showException("Error", "Por favor seleccione un recibo.", e);
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
