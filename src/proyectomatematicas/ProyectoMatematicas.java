
package proyectomatematicas;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 *
 * @author 
 */
public class ProyectoMatematicas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("Grafica.fxml"));
        
        Scene ventana = new Scene(root);
        stage.setTitle("Funciones Cuadráticas");
        stage.setScene(ventana);
        stage.getIcons().add(new Image ("proyectomatematicas/img/calculator-240.png"));
        stage.setResizable(false);
        
        stage.show();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
