import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FuncButton extends Button{

    public FuncButton(String name, TextField tf, Client client, char butType, Label tree){
        super(name);
        setPrefSize(100, 50);
        setOnAction(new Listener(tf, client, butType,tree));
    }
    
}

class Listener implements EventHandler<ActionEvent>{

    Client client;
    TextField tf;
    char butType;
    Label tree;
    String output;
    public Listener(TextField tf, Client client, char butType, Label tree){
        this.client=client;
        this.tf=tf;
        this.butType=butType;
        this.tree = tree;
    }
    @Override
    public void handle(ActionEvent arg0) {
        try {
            
            client.out.println(butType+" "+MyButton.activeTree+" "+tf.getText());
            
            output=client.in.readLine();
            output=output.replace("*","\n");
            System.out.println(output);
            tree.setText(output);
            tree.setStyle("-fx-text-alignment: center;");
            
            
        } catch (Exception ex) {

        }
    }
    
}

