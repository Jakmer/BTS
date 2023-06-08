import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Gui {

    /******************************************************************
     * 
     * Parameters
     * 
     ******************************************************************/

    String choosenTree = "none";
    int intArg;
    double doubleArg;
    String stringArg;

    private Client clientSocket;

    /******************************************************************
     * 
     * Gui constructor
     * 
     ******************************************************************/

    public Gui(Stage stage) {

        clientSocket = new Client("localHost", 4444);

        BorderPane root = new BorderPane();
        Label tree = new Label();
        TextField textField = new TextField();

        MyButton stringButton = new MyButton("String");
        MyButton doubleButton = new MyButton("Double");
        MyButton intButton = new MyButton("Integer");

        Label currentTree = new Label("Current Tree: " + choosenTree);

        intButton.intButton(currentTree);
        doubleButton.doubleButton(currentTree);
        stringButton.stringButton(currentTree);

        ToolBar toolBar = new ToolBar(intButton, doubleButton, stringButton, currentTree);

        toolBar.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth());
        toolBar.setPrefHeight(50);
        toolBar.getStyleClass().add("toolbar");

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(300);
        VBox rightbar = new VBox(sidebar);
        rightbar.setSpacing(100);
        rightbar.setPrefHeight(300);
        rightbar.getChildren().add(tree);

        // Add buttons or other components to the sidebar
        FuncButton searchButton = new FuncButton("Search", textField, clientSocket, 'S',tree);
        FuncButton insertButton = new FuncButton("Insert", textField, clientSocket, 'I',tree);
        FuncButton deleteButton = new FuncButton("Delete", textField, clientSocket, 'D',tree);
        FuncButton drawButton = new FuncButton("Draw", textField, clientSocket, 'R',tree);

        sidebar.getChildren().addAll(searchButton, insertButton, deleteButton, drawButton, textField);

        sidebar.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 50);
        sidebar.setPrefWidth(150);
        sidebar.setStyle("-fx-background-color: lightgray;");
        rightbar.setStyle("-fx-background-color: red;");
        rightbar.setStyle("-fx-text-alignment: center;");

        // Set the sidebar to the left of the BorderPane
        root.setTop(toolBar);
        root.setLeft(sidebar);
        root.setCenter(tree);

        /******************************************************************
         * 
         * Creating scene
         * 
         ******************************************************************/

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setTitle("Binary Tree");
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
                try {
                    clientSocket.socket.close();
                } catch (UnknownHostException ex) {
                    System.out.println("Server not found: " + ex.getMessage());

                } catch (IOException ex) {
                    System.out.println("I/O error: " + ex.getMessage());
                }
            }
        });
    }
}
class Client {
    public Socket socket;
    public PrintWriter out;
    public BufferedReader in;

    public Client(String name, int port) {
        try {
            socket = new Socket(name, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

    }
}
