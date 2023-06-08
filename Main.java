
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] arg) {
        
        Application.launch(arg);   
        
    }
    @Override
    public void start(Stage stage){
        new Gui(stage);
        
    }
}