package fusics_core_code;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Profile {
    public void UserPrf(Stage primaryStage) throws FileNotFoundException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1600, 800);
        Image background = new Image(new FileInputStream("src/Images/profile.png"));

        Image Back = new Image(new FileInputStream("src/Images/backButton.png"));
        ImageView bb = new ImageView(Back);

        Button back = new Button(null,bb);
        back.setBackground(null);
        back.setTranslateX(50);
        back.setTranslateY(20);

        back.setOnAction(e->{
            try {
                Main goBack = new Main();
                goBack.start(primaryStage);
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
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
