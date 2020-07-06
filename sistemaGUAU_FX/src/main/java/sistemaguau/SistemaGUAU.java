package sistemaguau;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author José Sánchez
 */
public class SistemaGUAU extends Application {
    
    public static EntityManagerFactory emf;
    
    @Override
    public void start(Stage stage) throws Exception {
        emf = Persistence.createEntityManagerFactory("com.mevecom_sistemaGUAU_FX_jar_1.0PU");
        Parent root = FXMLLoader.load(getClass().getResource("/Login/FormLogin.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
