package com.company;

/**
 * Created by johnny1024 on 2016-01-18.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Main extends Application
{
    private AnchorPane anchorPane;
    private Button newCivilPlane;
    private Button newCivilShip;
    private Button newWarPlane;
    private Button newWarShip;
    private ListView passengerList;

    public static void main(String[] args)
    {
        launch(args);

        //String fileName = "src\\save.ser";
        //ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        //final URL resource = getClass().getResource("src\\com\\company\\Sounds\\music.mp3");
        //final Media media = new Media(resource.toString());
        //final MediaPlayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.play();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLfile.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        anchorPane = controller.getAnchorPane();
        newCivilPlane = controller.getNewCivilPlane();
        newCivilShip = controller.getNewCivilShip();
        newWarPlane = controller.getNewMilitaryPlane();
        newWarShip = controller.getNewMilitaryShip();
        passengerList = controller.getPassengerList();

        Canvas map = new Canvas(800, 600);
        GraphicsContext gcMap = map.getGraphicsContext2D();
        Image imageMap = new Image(new FileInputStream(new File("src\\com\\company\\Pictures\\map.png")));
        gcMap.drawImage(imageMap, 0, 0);
        anchorPane.getChildren().add(map);

        AllObjects Universe = new AllObjects(anchorPane, passengerList);

        newCivilPlane.setOnAction(event -> Universe.addCivilPlane());
        newCivilShip.setOnAction(event -> Universe.addCivilShip());
        newWarPlane.setOnAction(event -> Universe.addWarPlane());
        newWarShip.setOnAction(event -> Universe.addWarShip());
        primaryStage.setOnCloseRequest(event -> Universe.endAll());

        //map.setOnMouseClicked(e -> VehicleInformation.display());

        primaryStage.setTitle("Statki i Samoloty v2.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                for (int i = 0; i < Universe.getCivilPlaneVector().size(); i++)
                {
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getCivilPlaneVector().get(i).getIcon())).setTranslateX(Universe.getCivilPlaneVector().get(i).getX() - 10);
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getCivilPlaneVector().get(i).getIcon())).setTranslateY(Universe.getCivilPlaneVector().get(i).getY() - 10);
                }
                for (int i = 0; i < Universe.getCivilShipVector().size(); i++)
                {
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getCivilShipVector().get(i).getIcon())).setTranslateX(Universe.getCivilShipVector().get(i).getX() - 10);
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getCivilShipVector().get(i).getIcon())).setTranslateY(Universe.getCivilShipVector().get(i).getY() - 10);
                }
                for (int i = 0; i < Universe.getWarPlaneVector().size(); i++)
                {
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getWarPlaneVector().get(i).getIcon())).setTranslateX(Universe.getWarPlaneVector().get(i).getX() - 10);
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getWarPlaneVector().get(i).getIcon())).setTranslateY(Universe.getWarPlaneVector().get(i).getY() - 10);
                }
                for (int i = 0; i < Universe.getWarShipVector().size(); i++)
                {
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getWarShipVector().get(i).getIcon())).setTranslateX(Universe.getWarShipVector().get(i).getX() - 10);
                    anchorPane.getChildren().get(anchorPane.getChildren().indexOf(Universe.getWarShipVector().get(i).getIcon())).setTranslateY(Universe.getWarShipVector().get(i).getY() - 10);
                }
            }
        }.start();
    }
}
