package fusics_core_code;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class logIn {

    Pane root = new Pane();

        public  void LogIn(Stage stage) throws IOException {
            stage.setTitle("Registration Form");

            // Create the registration form grid pane

            // Add UI controls to the registration form grid pane
            UIControls(root,stage);
            // Create a scene with registration form grid pane as the root node
            Scene scene = new Scene(root, 1600, 800);
            Image background = new Image(new FileInputStream("src/Images/login.png"));

            Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
            ImageView bb = new ImageView(Back);

            Button back = new Button(null,bb);
            back.setBackground(null);
            back.setTranslateX(50);
            back.setTranslateY(20);

            back.setOnAction(e->{
                try {
                    Main goBack = new Main();
                    goBack.start(stage);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });

            root.getChildren().addAll(back);

            BackgroundImage bi = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background bg = new Background(bi);

            root.setBackground(bg);
            // Set the scene in primary stage
            stage.setScene(scene);

            stage.show();

        }

//        private static Pane createLogInFormPane() {
//            Pane pane = new Pane();
////            gridPane.setAlignment(Pos.TOP_CENTER);
////            gridPane.setPadding(new Insets(80,80,80,80));
////            gridPane.setHgap(10);
////            gridPane.setVgap(10);
//            ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
//            columnOneConstraints.setHalignment(HPos.RIGHT);
//            ColumnConstraints columnTwoConstrains = new ColumnConstraints(400,400, Double.MAX_VALUE);
//            columnTwoConstrains.setHgrow(Priority.ALWAYS);
//
////            gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
//
//            return pane;
//        }

        public int findFile(String name,File file)
        {
            File[] list = file.listFiles();
            if(list!=null)
                for (File fil : list)
                {
                    if (fil.isDirectory())
                    {
                        findFile(name,fil);
                    }
                    else if (name.equalsIgnoreCase(fil.getName()))
                    {
                        System.out.println(fil.getParentFile());
                        return 0;
                    }
                }
            return 1;
        }
        private final void UIControls(Pane gridPane, Stage stage) {
            // Add Header
            // Add Name Label

            // Add Name Text Field
            TextField nameField = new TextField();
            nameField.setTranslateX(700);
            nameField.setTranslateY(305);
            nameField.setPrefSize(250,40);
            nameField.setAlignment(Pos.CENTER_LEFT);
            nameField.setFont(Font.font("Aleo", FontWeight.NORMAL, 20));
            nameField.setScaleX(1.5);
            nameField.setScaleY(1.5);
            nameField.setStyle("-fx-border-color: white;\n" +
                    "-fx-background-insets: 0, 0 0 0 0 ;\n" +
                    " -fx-background-radius: 0 ;\n" +
                    " -fx-background-color: transparent;");

            // Add Age Field
            PasswordField passwordField = new PasswordField();
            passwordField.setTranslateX(640);
            passwordField.setTranslateY(400);
            passwordField.setPrefSize(250,40);
            passwordField.setFont(Font.font("Aleo", FontWeight.NORMAL, 20));
            passwordField.setStyle("-fx-border-color: white;\n" +
                    "-fx-background-insets: 0, 0 0 0 0 ;\n" +
                    " -fx-background-radius: 0 ;\n" +
                    " -fx-background-color: transparent;");


            // Add Submit Button
            Button submitButton = new Button("Submit");
            submitButton.setPrefHeight(40);
            submitButton.setPrefWidth(200);
            submitButton.setDefaultButton(true);
            submitButton.setTranslateX(700);
            submitButton.setTranslateY(500);
            setStyleO(submitButton);


            Hyperlink link = new Hyperlink("Not a member? Create new account");

            link.setTranslateX(700);
            link.setTranslateY(600);
            link.setScaleX(2);
            link.setScaleY(2);
            link.setTextFill(Color.DARKBLUE);


            link.setOnAction(e -> {
                System.out.println("The Hyperlink was clicked!");


                Registration reg=new Registration();
                try {
                    reg.registration(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            });



            submitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if(nameField.getText().isEmpty()) {
                        showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                        return;
                    }

                    if(passwordField.getText().isEmpty()) {
                        showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                        return;
                    }

                    String name =nameField.getText()+passwordField.getText()+".txt";

                    System.out.println("file name"+name);
                    String directory = "C:\\Users\\ttaos\\Documents\\HackTheVerse";

                    if( findFile(name,new File(directory))==0)
                    {
                        Scanner sc = null;
                        try {
                            sc = new Scanner(new File(name));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        String st = sc.nextLine();
                        System.out.println(st);
                        if(st.contains("Student")) {
                            showAlerts(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "LogIn Successful!", "Welcome " + nameField.getText());
                            String s = nameField.getText();
                            System.out.println(s);
                            try {
                                secondPage sp = new secondPage();
                                sp.TheSecond(stage);
                            } catch (Exception excep) {
                                excep.printStackTrace();
                            }
                        }
                        else
                        {
                            showAlerts(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "LogIn Successful!", "Welcome " + nameField.getText());
                            String s = nameField.getText();
                            System.out.println(s);
                            try {
                                ClassForTeacher goClsT = new ClassForTeacher();
                                goClsT.Classroom(stage);
                            } catch (Exception excep) {
                                excep.printStackTrace();
                            }
                        }


//                        FileWriter fw = null;
//                        try {
//                            fw = new FileWriter(s+".txt");
//                            fw.write(s+"\n"+passwordField.getText()+"\n");
//                            fw.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }


                    }


                    else {
                        showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "LogIn Error!", "Please enter valid password");
                        return;
                    }

                }

            });
            root.getChildren().addAll(nameField,passwordField,submitButton,link);
        }

        private static void showAlerts(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }

    public Button setStyleO(Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#1289C4 0%, #40BAEC 25%, #0097CE 75%, #1289C4 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 20%, #118cc6 80%, #1476a9 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 20%, #118cc6 80%, #1476a9 100%),\n" +
                "        linear-gradient(#40baec 0%, #0097ce 40%, #118cc6 80%, #1476a9 100%);\n" +
                "    -fx-background-insets: 0,1,4,5,6;\n" +
                "    -fx-background-radius: 9,8,5,4,3;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");
        return b;
    }

    }

