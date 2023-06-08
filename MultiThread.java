import java.io.*;
import java.net.*;

public class MultiThread extends Thread {
    private Socket socket;

    BinaryTree<Integer> intTree = new BinaryTree<Integer>();
    BinaryTree<Double> doubleTree = new BinaryTree<Double>();
    BinaryTree<String> stringTree = new BinaryTree<String>();

    public MultiThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            // Odbieranie od socketa
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            // Wysylanie do socketa
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);

            char funcType;
            int activeTree;
            String line;
            int paramInt;
            double paramDouble;
            String paramString;
            do {

                line = in.readLine();
                //System.out.println(line);
                funcType = line.charAt(0);
                activeTree = line.charAt(2) - 48;

                switch (funcType) {
                    case 'S':
                        switch (activeTree) {
                            case 0:
                                line = line.substring(4, line.length());
                                paramInt = Integer.parseInt(line);
                                line = intTree.draw();
                                out.println(line);
                                break;

                            case 1:
                                line = line.substring(4, line.length());
                                paramDouble = Double.parseDouble(line);
                                line = doubleTree.draw();
                                out.println(line);
                                break;

                            case 2:
                                paramString = line.substring(4, line.length());
                                line = stringTree.draw();
                                out.println(line);
                                break;

                            default:
                                break;
                        }
                        break;

                    case 'I':
                        switch (activeTree) {
                            case 0:
                                line = line.substring(4, line.length());
                                paramInt = Integer.parseInt(line);
                                intTree.insert(paramInt);
                                line = intTree.draw();
                                out.println(line);
                                break;

                            case 1:
                                line = line.substring(4, line.length());
                                paramDouble = Double.parseDouble(line);
                                doubleTree.insert(paramDouble);
                                out.println(doubleTree.draw());
                                break;

                            case 2:
                                paramString = line.substring(4, line.length());
                                stringTree.insert(paramString);
                                out.println(stringTree.draw());
                                break;

                            default:
                                break;
                        }
                        break;

                    case 'D':
                        switch (activeTree) {
                            case 0:
                                line = line.substring(4, line.length());
                                paramInt = Integer.parseInt(line);
                                intTree.delete(paramInt);
                                out.println(intTree.draw());
                                break;

                            case 1:
                                line = line.substring(4, line.length());
                                paramDouble = Double.parseDouble(line);
                                doubleTree.delete(paramDouble);
                                out.println(doubleTree.draw());
                                break;

                            case 2:
                                paramString = line.substring(4, line.length());
                                stringTree.delete(paramString);
                                out.println(stringTree.draw());
                                break;

                            default:
                                break;
                        }
                        break;

                    case 'R':
                        switch (activeTree) {
                            case 0:
                                out.println(intTree.draw());
                                break;

                            case 1:
                                out.println(doubleTree.draw());
                                break;

                            case 2:
                                out.println(stringTree.draw());
                                break;

                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            } while (!line.equals("bye"));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    void search() {

    }
}