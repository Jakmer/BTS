import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyButton extends Button {

    static int activeTree = -1; // 0 - int, 1 - double, 2 - string

    public MyButton(String name) {
        super(name);
        setPrefSize(100, 50);
    }

    public void intButton(Label currentTree) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    currentTree.setText("Current Tree: Integer");
                    activeTree = 0;
                } catch (Exception ex) {

                }

            }
        });
    }

    /**
     * 
     */
    public void doubleButton(Label currentTree) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    currentTree.setText("Current Tree: Double");
                    activeTree = 1;
                } catch (Exception ex) {

                }

            }
        });
    }

    /**
     * 
     */
    public void stringButton(Label currentTree) {
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                try {
                    currentTree.setText("Current Tree: String");
                    activeTree = 2;
                } catch (Exception ex) {

                }

            }
        });
    }
}
