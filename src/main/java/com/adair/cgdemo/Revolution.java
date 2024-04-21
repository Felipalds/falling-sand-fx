package com.adair.cgdemo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Revolution extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double CYLINDER_RADIUS = 10;
    private static final double CYLINDER_HEIGHT = 100;

    @Override
    public void start(Stage primaryStage) {
        // Define 2D points
        double[][] points2D = {
                {100, 100},
                {200, 150},
                {300, 200},
                {400, 250},
                {500, 300}
        };

        // Create 3D object by revolving around y-axis
        Group root = new Group();
        for (double[] point : points2D) {
            Cylinder cylinder = new Cylinder(CYLINDER_RADIUS, CYLINDER_HEIGHT);
            cylinder.setMaterial(new PhongMaterial(Color.BLUE));
            cylinder.setRotationAxis(Rotate.Y_AXIS);
            cylinder.setRotate(90); // Align with y-axis
            cylinder.setTranslateX(point[0]);
            cylinder.setTranslateY(point[1]);
            root.getChildren().add(cylinder);
        }

        // Set up scene and camera
        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);
        scene.setCamera(camera);

        // Set up stage
        primaryStage.setTitle("Point Revolution 3D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
