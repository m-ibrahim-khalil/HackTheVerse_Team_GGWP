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

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {
    Pane root = new Pane();
    public void registration(Stage stage) throws Exception {
        // Stage primaryStage=new Stage();

        // Create the registration form grid pane

        // Add UI controls to the registration form grid pane
        addUIControls(root,stage);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(root, 1600, 800);
        Image background = new Image(new FileInputStream("src/Images/reg.png"));

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);

        back.setOnAction(e->{
            try {
                logIn goLog = new logIn();
                goLog.LogIn(stage);
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

    private void addUIControls(Pane gridPane,Stage stage) {

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setTranslateX(650);
        nameField.setTranslateY(270);
        nameField.setPrefSize(350,40);
        nameField.setAlignment(Pos.CENTER_LEFT);
        nameField.setFont(Font.font("Aleo", FontWeight.NORMAL, 17));
        nameField.setScaleX(1);
        nameField.setScaleY(1);
        nameField.setStyle("-fx-border-color: white;\n" +
                "-fx-background-insets: 0, 0 0 0 0 ;\n" +
                " -fx-background-radius: 0 ;\n" +
                " -fx-background-color: transparent;");

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setTranslateX(650);
        emailField.setTranslateY(340);
        emailField.setPrefSize(350,40);
        emailField.setAlignment(Pos.CENTER_LEFT);
        emailField.setFont(Font.font("Aleo", FontWeight.NORMAL, 17));
        emailField.setScaleX(1);
        emailField.setScaleY(1);
        emailField.setStyle("-fx-border-color: white;\n" +
                "-fx-background-insets: 0, 0 0 0 0 ;\n" +
                " -fx-background-radius: 0 ;\n" +
                " -fx-background-color: transparent;");


        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setTranslateX(650);
        passwordField.setTranslateY(420);
        passwordField.setPrefSize(250,40);
        passwordField.setAlignment(Pos.CENTER_LEFT);
        passwordField.setFont(Font.font("Aleo", FontWeight.NORMAL, 17));
        passwordField.setScaleX(1);
        passwordField.setScaleY(1);
        passwordField.setStyle("-fx-border-color: white;\n" +
                "-fx-background-insets: 0, 0 0 0 0 ;\n" +
                " -fx-background-radius: 0 ;\n" +
                " -fx-background-color: transparent;");


        // Add sign-in as who
        final ToggleGroup grp = new ToggleGroup();
        RadioButton Student = new RadioButton();
        Student.setToggleGroup(grp);
        Student.setText("Student");
        RadioButton Teacher = new RadioButton();
        Teacher.setToggleGroup(grp);
        Teacher.setText("Teacher");
        Student.setTranslateX(650);
        Student.setTranslateY(520);
        Teacher.setTranslateX(800);
        Teacher.setTranslateY(520);
        Student.setScaleX(1.5);
        Student.setScaleY(1.5);
        Teacher.setScaleX(1.5);
        Teacher.setScaleY(1.5);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setPrefWidth(200);
        submitButton.setDefaultButton(true);
        submitButton.setTranslateX(700);
        submitButton.setTranslateY(570);
        setStyleO(submitButton);

        Hyperlink link = new Hyperlink("Already a member? Login");

        link.setTranslateX(730);
        link.setTranslateY(640);
        link.setScaleX(1.5);
        link.setScaleY(1.5);
        link.setTextFill(Color.DARKBLUE);


        link.setOnAction(e -> {
            System.out.println("The Hyperlink was clicked!");


            logIn Log = new logIn();
            try {
                Log.LogIn(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        root.getChildren().addAll(nameField,emailField,passwordField,submitButton,Student,Teacher,link);



        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(grp.getSelectedToggle().toString());

                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }

                if(regexChecker("[\\w._%-]+@[\\w._%-]+\\.[A-Za-z]{2,4}",emailField.getText())!=1) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your valid email id");
                    return;
                }

                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                if(grp.getSelectedToggle()==null) {

                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please select a specific type");
                    return;
                }


                else
                {
                    showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
                    //  stage.close();
                }



                String s=nameField.getText();
                System.out.println(s);


                FileWriter fw = null;
                try {
                    fw = new FileWriter(s+passwordField.getText()+".txt");
                    fw.write(s+"\n"+emailField.getText()+"\n"+passwordField.getText()+"\n"+grp.getSelectedToggle().toString()+"\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                try {
//                        thirdPage tp=new thirdPage();
//                        tp.app(stage,s+passwordField.getText()+".txt");
                } catch (Exception excep) {
                    excep.printStackTrace();
                }



            }
        });
    }

    public static int regexChecker(String theRegex ,String str2Check) {
        Pattern checkRegex=Pattern.compile(theRegex);
        Matcher regexMatcher =checkRegex.matcher(str2Check);
        while(regexMatcher.find())
        {

            if(regexMatcher.group().length()!=0)
            {
                System.out.println(regexMatcher.group().trim());
            }

            System.out.println("Start index :"+regexMatcher.start());
            System.out.println("End index :"+regexMatcher.end());
            return 1;
        }
        return 0;
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
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
