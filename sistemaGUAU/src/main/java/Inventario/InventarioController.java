package Inventario;

import JPA.Producto;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author GuillePC
 */
public class InventarioController implements Initializable {

    @FXML
    private AnchorPane APaneInventario;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableView tblProductos;
    @FXML
    private TableColumn<Producto, Integer> id;
    @FXML
    private TableColumn<Producto, String> nombre;
    @FXML
    private TableColumn<Producto, Float> existencias;
    @FXML
    private TableColumn<Producto, Float> precio;
    @FXML
    private TableColumn<Producto, String> marca;
    @FXML
    private TableColumn<Producto, Float> peso;

    private ProductoDAO inventario_dao = new ProductoDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        existencias.setCellValueFactory(new PropertyValueFactory<>("Existencias"));
        precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        marca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        peso.setCellValueFactory(new PropertyValueFactory<>("Peso"));

        List<Producto> lista_clientes = inventario_dao.getProductos();

        for (Iterator<Producto> iterator = lista_clientes.iterator(); iterator.hasNext();) {
            Producto next = iterator.next();
            modelo_clientes.add(next);
        }
        tblProductos.setItems(modelo_clientes);
    }

    private ObservableList<Producto> modelo_clientes = FXCollections.observableArrayList();

    public static void showException(String title, String message, Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Excepción");
        alert.setHeaderText(title);
        alert.setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Detalles:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    @FXML
    private void btnAgregarProductoHandel(ActionEvent event) throws IOException {
        //Llamar a una nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Inventario/AgregarProducto.fxml"));
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

    @FXML
    private void btnModificarHandle(ActionEvent event) throws IOException {
        try {
            Producto cliente_seleccionado = (Producto) tblProductos.getSelectionModel().getSelectedItem();
            Producto cliente_enviar = inventario_dao.getProductoByID(cliente_seleccionado.getIdProducto());
            try (FileWriter fileWriter = new FileWriter("producto.txt")) {
                System.out.println(cliente_enviar.getIdProducto());
                fileWriter.write(cliente_enviar.getIdProducto());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se pudo guardar");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Inventario/ModificarProducto.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("MEVECOM <>");
            stage.setResizable(false);
            stage.show();
            //Cerrar ventana actual
            Stage actual = (Stage) btnRegresar.getScene().getWindow();
            actual.close();
        } catch (Exception e) {
            showException("Error", "Por favor seleccione un producto.", e);
        }

    }

    @FXML
    private void btnRegresarHandle(ActionEvent event) throws IOException {
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
