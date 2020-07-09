/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventas;

import JPA.Cliente;
import static JPA.Cliente_.nombre;
import JPA.Factura;
import JPA.Usuario;
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
    
    private VentasDAO ventas_dao = new VentasDAO();
    
    //Tabla para mostra las ventas
    @FXML
    private TableView<Factura> tblVentas;
    @FXML
    private TableColumn<Factura, Integer> colID;
    @FXML
    private TableColumn<Factura, Cliente> colCliente;
    @FXML
    private TableColumn<Factura, Date> colFecha;
    @FXML
    private TableColumn<Factura, Float> colTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<>("idFactura")); 
        colCliente.setCellValueFactory(new PropertyValueFactory<>("clienteid")); 
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha")); 
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total")); 
 
        List<Factura> lista_recibos = ventas_dao.getFacturas(); 
 
        for(Iterator<Factura> iterator = lista_recibos.iterator(); iterator.hasNext();) { 
            Factura next = iterator.next(); 
            modelo_facturas.add(next); 
        } 
        tblVentas.setItems(modelo_facturas); 
    }    
    private ObservableList<Factura> modelo_facturas = FXCollections.observableArrayList(); 

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
        Factura factura_seleccionada = tblVentas.getSelectionModel().getSelectedItem();
        Factura factura_enviar = ventas_dao.getFacturaByID(factura_seleccionada.getIdFactura());
        try (FileWriter fileWriter = new FileWriter("factura.txt")) {
            fileWriter.write(factura_enviar.getIdFactura());
            fileWriter.close();
        } catch (IOException e) {
            // Exception handling
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
