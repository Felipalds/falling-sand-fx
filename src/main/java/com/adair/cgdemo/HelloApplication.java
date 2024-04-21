package com.adair.cgdemo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.PixelBuffer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application {

    class Point {

        Color color;
        private double x;
        private double y;

        public Point (double x, double y, Color c) {
            this.x = x;
            this.y = y;
            this.color = c;
        }

        public void setY(double y) {
            this.y = y;
        }
        public void setColor (Color c) {
            this.color = c;
        }


        public Color getColor () {
            return this.color;
        }

        public double getY () {
            return this.y;
        }
        public double getX () {
            return this.x;
        }

        public void fall () {
            if(y < maxInColumn.get(x) - 5) {
                this.setY(y + 5);
//            }
//            if(leftNeed(x, y)) {
//                maxInColumn.remove(x);
//                y = y + 5;
//                x = x + 5;
//            } else if (rightNeed(x, y)) {
//                maxInColumn.remove(x);
//                y = y + 5;
//                x = x - 5;
            } else {
                maxInColumn.put(x, y);
            }
        }
    }

    public boolean rightNeed(double x, double y) {
        if(!maxInColumn.containsValue(x + 5)) {
            return true;
        }
        return maxInColumn.get(x + 5) < y;
    }
    public boolean leftNeed(double x, double y) {
        if(!maxInColumn.containsValue(x - 5)) {
            return true;
        }
        return maxInColumn.get(x - 5) < y;
    }
    private static double hueOffset = 0.0; // Initial hue offset

    private List<Point> points;
    private Map<Double, Double> maxInColumn;
    GraphicsContext graphicsContext;

    private void makePhysics() {
        for (Point point : points) {
            point.fall();
        }
    }
    private void drawPixel(Point point) {
        graphicsContext.setFill(point.getColor());
        graphicsContext.fillRect(point.getX(), point.getY(), 5, 5);
    }

    private void drawPoints() {
        System.out.println("A");
        for (Point point : points) {
            drawPixel(point);
        }
    }

    private void updateMap (double x, double y) {
        if(!maxInColumn.containsKey(x)) {
            maxInColumn.put(x, 400.0);
        }
    }

    private double roundToFive(double v) {
        double remainder = v % 5;
        if(remainder <= 2.5) {
            return v - remainder;
        } else {
            return v + (5 - remainder);
        }
    }
    public static Color generateRainbowColor() {
        // Update hue offset for next color
        hueOffset += 0.001; // Adjust the step size for smoother or faster transition

        // Wrap hue value within [0, 1)
        if (hueOffset >= 1.0) {
            hueOffset -= 1.0;
        }

        // Convert HSB to RGB color
        return Color.hsb(hueOffset * 360, 1.0, 1.0); // Hue in range [0, 360]
    }


    @Override
    public void start(Stage stage) throws IOException {
        points = new ArrayList<Point>();
        maxInColumn = new HashMap<>();

        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);

        Canvas canvas = new Canvas(600, 400);
        graphicsContext = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0, 600, 400);
                drawPoints();
                makePhysics();
            }
        }.start();

        root.getChildren().add(canvas);


        scene.setOnMouseDragged(event -> {
                double x = roundToFive(event.getX());
                double y = roundToFive(event.getY());
                Point point = new Point(x, y, generateRainbowColor());
                points.add(point);
                updateMap(x, y);
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}