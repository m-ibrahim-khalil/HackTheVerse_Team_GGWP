package fusics_core_code;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Scanner;

public class ClassForTeacher {

    String Classes = new String();
    Label ClassNames[] = new Label[10];
    int i=0, pos = 60;

    public void Classroom(Stage primaryStage) throws FileNotFoundException {

        Pane root = new Pane();

        //See the code
        Scanner sc = new Scanner(new File("src/Class_Code_Generator.txt"));
        String code = sc.nextLine();


        Scanner scn = new Scanner(new File("src/Classes_Of_Teacher.txt"));
        if(Integer.parseInt(code)>100000) {
            while (scn.hasNextLine()) {
                String ClsNam = scn.nextLine();
                    ClassNames[i] = new Label();
                    setClassName(ClassNames[i], ClsNam, 500, 200 + pos, 2.2, 2.2);
                    pos += 60;
                    root.getChildren().add(ClassNames[i]);
                    i++;
                    if (scn.hasNextLine()) {
                        scn.nextLine();
                    }
                }
        }

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);

        back.setOnAction(e->{
            try {
                Main goM = new Main();
                goM.start(primaryStage);
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

        Image Add = new Image(new FileInputStream("src/Images/add.png"));
        ImageView add = new ImageView(Add);

        Button AddClass = new Button(null,add);
        AddClass.setBackground(null);
        AddClass.setTranslateX(1050);
        AddClass.setTranslateY(580);

        AddClass.setOnAction(e->{
            Pane Rr = new Pane();
            TextField Nam = new TextField();
            Nam.setAlignment(Pos.CENTER_LEFT);
            Nam.setTranslateX(120);
            Nam.setTranslateY(275);
            Nam.setScaleX(1.5);
            Nam.setScaleY(1.5);
            Nam.setFont(Font.font("Aleo", FontWeight.NORMAL, 15));
            Nam.setStyle("-fx-border-color: white;\n" +
                    "-fx-background-insets: 0, 0 0 1 0 ;\n" +
                    " -fx-background-radius: 0 ;\n" +
                    " -fx-background-color: transparent;");
            Button enter = new Button("Enter");
            enter.setTranslateX(170);
            enter.setTranslateY(410);
            setStyleE(enter);
            enter.setPrefSize(80,20);

            Button done = new Button("Done");
            done.setTranslateX(170);
            done.setTranslateY(410);
            setStyleE(done);
            done.setPrefSize(80,20);
            done.setVisible(false);

            Rr.getChildren().addAll(Nam,enter,done);
            Scene Ss = new Scene(Rr, 380, 540);
            Stage eeStage = new Stage();
            eeStage.setScene(Ss);
            Image bgg = null;
            try {
                bgg = new Image(new FileInputStream("src/Images/newClass.png"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            BackgroundImage bii = new BackgroundImage(bgg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background backk = new Background(bii);
            Rr.setBackground(backk);
            Ss.setFill(Color.TRANSPARENT);
            eeStage.initStyle(StageStyle.TRANSPARENT);
            eeStage.show();

            enter.setOnAction(ex->
            {
                Classes = Nam.getText();
                if(!(Classes==null) )
                {
                    Scanner scnn = null;
                    try {
                        scnn = new Scanner(new File("src/Class_Code_Generator.txt"));
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    String Ccode = scnn.nextLine();
                    int ClassCode = Integer.parseInt(Ccode)+1;
                    System.out.println("Class successful");
                    Text ShowCode = new Text("The Class Code is "+ ClassCode);
                    ShowCode.setFill(Color.MAROON);
                    ShowCode.setTranslateX(120);
                    ShowCode.setTranslateY(350);
                    ShowCode.setScaleX(1.5);
                    ShowCode.setScaleY(1.5);
                    Rr.getChildren().addAll(ShowCode);

                    try {
                        // create a new class fileF
                        FileWriter fw1 = null;
                        fw1 = new FileWriter(ClassCode+".txt");
                        fw1.write(ClassCode + "\n"+ Classes);
                        fw1.close();

                        // Overwrite the generator code
                        FileWriter fw2 = null;
                        fw2 = new FileWriter("src/Class_Code_Generator.txt");
                        fw2.write(ClassCode + "\n");
                        fw2.close();

                        // Append in the list of class
                        FileWriter fw3 = null;
                        fw3 = new FileWriter("src/Classes_Of_Teacher.txt",true);
                        fw3.write(Classes+ "\n" + ClassCode + "\n");
                        fw3.close();

                    } catch (IOException Exc) {
                        Exc.printStackTrace();
                    }
                    done.setVisible(true);
                }
                else
                {
                    Text alert = new Text("Please enter the required fields");
                    alert.setFill(Color.RED);
                    alert.setTranslateX(300);
                    alert.setTranslateY(320);
                    alert.setScaleX(1.4);
                    alert.setScaleY(1.4);
                    Rr.getChildren().addAll(alert);
                }
            });

            done.setOnAction(E->{
                eeStage.close();
                ClassNames[i] = new Label();
                setClassName(ClassNames[i],Classes,500,200+pos,2.2,2.2);
                pos+=60;
                root.getChildren().add(ClassNames[i]);
                i++;
            });
        });


        for(int iterator = 0; iterator<i;iterator++)
        {
            ClassNames[iterator].setOnMouseClicked(mouseEvent -> {
                OpenClass goOC = new OpenClass();
                try {
                    goOC.Class(primaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Class clicked");
            });
        }


        Image background = new Image(new FileInputStream("src/Images/cls.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        root.getChildren().addAll(back,user,AddClass);

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

    public Label setClassName (Label cw,String Name, double X, double Y, double sx, double sy)
    {
        cw.setText(" " + Name+" ");
        cw.setTranslateX(X);
        cw.setTranslateY(Y);
        cw.setScaleX(sx);
        cw.setScaleY(sy);
        cw.setTextFill(Color.WHITE);
        cw.setAlignment(Pos.CENTER);
        cw.setStyle("-fx-background-color:DARKBLUE;\n" +
                    "-fx-border-color: white;\n" +
                    "-fx-background-radius: 5px;\n" +
                    "-fx-border-radius: 5px;");

        cw.setPadding(new Insets(3));
        cw.setPrefSize(150,1);
        return cw;
    }

}
