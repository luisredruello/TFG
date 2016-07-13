package javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreensFramework extends Application{ // es la clase que arranca las interfaces

	public static final String MAIN_SCREEN = "ControladorEscogeCertificacion";
    public static final String MAIN_SCREEN_FXML = "EscogeCertificacion.fxml";
    public static final String C1_SCREEN = "ControladorC1";
    public static final String C1_SCREEN_FXML = "C1.fxml";
    public static final String C2_SCREEN = "ControladorC2";
    public static final String C2_SCREEN_FXML = "C2.fxml";
    public static final String C3_SCREEN = "ControladorC3";
    public static final String C3_SCREEN_FXML = "C3.fxml";

    @Override
    public void start(Stage primaryStage) {

      ScreensController mainContainer = new ScreensController();
      mainContainer.loadScreen(ScreensFramework.MAIN_SCREEN, ScreensFramework.MAIN_SCREEN_FXML);
      mainContainer.loadScreen(ScreensFramework.C1_SCREEN, ScreensFramework.C1_SCREEN_FXML);
      mainContainer.loadScreen(ScreensFramework.C2_SCREEN, ScreensFramework.C2_SCREEN_FXML);
      mainContainer.loadScreen(ScreensFramework.C3_SCREEN, ScreensFramework.C3_SCREEN_FXML);

      mainContainer.setScreen(ScreensFramework.MAIN_SCREEN);
      
      Group root = new Group();
      root.getChildren().addAll(mainContainer);
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    } 
	

}
