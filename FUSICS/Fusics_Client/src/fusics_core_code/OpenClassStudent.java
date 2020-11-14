package fusics_core_code;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OpenClassStudent {

            private File selectedFile;
            Button tasks[] = new Button[20];
            int taskN = 0;
            int pos = 50;

            public void Class(Stage primaryStage) throws FileNotFoundException {
                Pane root = new Pane();

                Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
                ImageView bb = new ImageView(Back);

                Button back = new Button(null,bb);
                back.setBackground(null);
                back.setTranslateX(50);
                back.setTranslateY(20);

                back.setOnAction(e->{
                    try {
                        ClassForStudent goClas = new ClassForStudent();
                        goClas.StudentClass(primaryStage);
                    }catch (Exception E)
                    {
                        E.printStackTrace();
                    }
                });

                Image User = new Image(new FileInputStream("src/Images/user.png"));
                ImageView uu = new ImageView(User);

                Button user = new Button(null,uu);
                user.setBackground(null);
                user.setTranslateX(1400);
                user.setTranslateY(20);

                user.setOnAction(e->{
                    Profile pf = new Profile();
                    try {
                        pf.UserPrf(primaryStage);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                });

                Button query = new Button("Ask a Question");
                query.setTranslateX(270);
                query.setTranslateY(350);
                setStyleO(query);
//                Button Take_Exam = new Button("Give Exam");
//                Take_Exam.setTranslateX(270);
//                Take_Exam.setTranslateY(400);
//                setStyleO(Take_Exam);

                FileChooser fileChooser = new FileChooser();

                Text task1 = new Text("Task 1: A rock is thrown at some velocity and angle. For which initial" + "\n"+
                                         "launch angle the Horizontal Range and Maximum Height will be the same?");
                task1.setTranslateX(680);
                task1.setTranslateY(250);
                task1.setScaleX(1.5);
                task1.setScaleY(1.5);

                Button submit1 = new Button("Submit");
                submit1.setTranslateX(800);
                submit1.setTranslateY(360);
                setStyleE(submit1);
                submit1.setVisible(false);

                Button submit = new Button("Submit Answer");
                submit.setTranslateX(790);
                submit.setTranslateY(290);
                setStyleO(submit);
                submit.setOnAction(ev->{
                    submit1.setVisible(true);
                    selectedFile = fileChooser.showOpenDialog(primaryStage);
                    System.out.println(selectedFile);
                });

                submit1.setOnAction(e -> {
                    if(selectedFile != null) {
                        submit1.setVisible(false);
                        System.out.println("File submitted successfully!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("File submitted successfully!");
                        alert.show();
                    }
                });



                root.getChildren().addAll(submit,task1,query,submit1);
                Image background = new Image(new FileInputStream("src/Images/clsForT.png"));

                BackgroundImage bi = new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                root.setBackground(bg);
                root.getChildren().addAll(back,user);

                Scene scene = new Scene(root, 1600, 800);
                primaryStage.setScene(scene);
                primaryStage.show();

                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        e.consume();
                        Pane R = new Pane();
                        Text exit = new Text("Do you want to exit?");
                        exit.setScaleX(3);
                        exit.setScaleY(3);
                        exit.setTranslateX(300);
                        exit.setTranslateY(100);
                        exit.setFill(Color.WHITE);
                        Button yes = new Button("Yes");
                        setStyleE(yes);
                        yes.setTranslateX(170);
                        yes.setTranslateY(200);
                        yes.setPrefSize(150, 50);
                        Button no = new Button("No");
                        setStyleE(no);
                        no.setTranslateX(400);
                        no.setTranslateY(200);
                        no.setPrefSize(150, 50);
                        R.getChildren().addAll(exit, yes, no);
                        Scene S = new Scene(R, 700, 400);
                        Stage eStage = new Stage();
                        eStage.setScene(S);
                        Image bg = null;
                        try {
                            bg = new Image(new FileInputStream("src/Images/exit.png"));
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        BackgroundImage bi = new BackgroundImage(bg,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        Background back = new Background(bi);
                        R.setBackground(back);
                        S.setFill(Color.TRANSPARENT);
                        eStage.initStyle(StageStyle.TRANSPARENT);
                        eStage.show();

                        yes.setOnAction(ev -> {
                            System.out.println("Closing");
                            System.exit(0);
                        });
                        no.setOnAction(ev -> {
                            eStage.close();
                        });
                    }
                });
            }

            public Button setStyle( Button b)
            {
                b.setStyle("-fx-padding: 8 15 15 15;\n" +
                        "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "    -fx-background-radius: 8;\n" +
                        "    -fx-background-color: \n" +
                        "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                        "        #8d9092,\n" +
                        "        #717375,\n" +
                        "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                        "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.1em;");
                return b;
            }
            public Button setStyleE(Button b) {
                b.setStyle("-fx-background-color: \n" +
                        "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                        "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                        "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                        "    -fx-background-radius: 8,7,6;\n" +
                        "    -fx-background-insets: 0,1,2;\n" +
                        "    -fx-text-fill: black;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.6em;\n" +
                        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
                return b;
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
            public Button setTaskAnswer(Button ans,int x, int y)
            {
                ans.setTranslateX(x);
                ans.setTranslateY(y);
                ans.setScaleX(1.1);
                ans.setScaleY(1.1);
                pos+=50;
                return ans;
            }
}
