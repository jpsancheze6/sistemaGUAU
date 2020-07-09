package Ventas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JPA.DetalleFactura;
import JPA.Factura;
import JPA.Producto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author izasj
 */
public class RevisarVentaController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtTotal;
    private DetalleFacturaDAO ventas_dao = new DetalleFacturaDAO();
    private DetalleFactura factura;
    
    //Tabla de la venta seleccionada
    @FXML
    private TableView<DetalleFactura> tblVenta;
    @FXML
    private TableColumn<DetalleFactura, Producto> colProducto;
    @FXML
    private TableColumn<DetalleFactura, Float> colCantidad;
    @FXML
    private TableColumn<DetalleFactura, Float> colTotal;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colProducto.setCellValueFactory(new PropertyValueFactory<>("productoid")); 
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal")); 
 
        try (FileReader fileReader = new FileReader("factura.txt")) {
            int id = fileReader.read();
            this.factura = ventas_dao.getDetalleFacturaByID(id);
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }
        List<DetalleFactura> lista_recibos = ventas_dao.getDetalleFacturas(); 
 
        for(Iterator<DetalleFactura> iterator = lista_recibos.iterator(); iterator.hasNext();) { 
            DetalleFactura next = iterator.next(); 
            modelo_facturas.add(next); 
        } 
        tblVenta.setItems(modelo_facturas); 
    }
    private ObservableList<DetalleFactura> modelo_facturas = FXCollections.observableArrayList();    
    
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
        Stage actual = (Stage) btnRegresar.getScene().getWindow();
        actual.close();
    }
    
}
