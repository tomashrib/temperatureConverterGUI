import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BiTempConvert extends Application{
    private Label lbTemperatureConvert, lbFahrenheit, lbCelsius, lbOutputResult;
    private TextField tfResult;
    private RadioButton rbFtoC, rbCtoF;
    private Button btConvertTemperature;
    private static ConvertCalculate convertCalculate;

    public void start(final Stage stage){
        lbTemperatureConvert = new Label("°C to °F");
        lbTemperatureConvert.getStyleClass().add("lbTemperatureConvert");

        tfResult = new TextField("");
        // tfResultF.getStyleClass().add("tfResultF");
        tfResult.setMaxWidth(Double.MAX_VALUE);

        lbFahrenheit = new Label("°F");
        // lbFahrenheit.getStyleClass().add("lbFahrenheit");

        lbOutputResult = new Label("output");

        lbCelsius = new Label("°C");
        // lbCelsius.getStyleClass().add("lbCelsius");
        
        rbFtoC = new RadioButton();
        rbFtoC.setText("°F -> °C");
        
        rbCtoF = new RadioButton();
        rbCtoF.setText("°C -> °F");

        btConvertTemperature = new Button("Konvertuj");
        btConvertTemperature.getStyleClass().add("btConvertTemperature");
        btConvertTemperature.setOnAction(new ClickConvert());

        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(25);
        gridPane.setHgap(15);
        // gridPane.setHalignment(lbTemperatureConvert, HPos.CENTER);

        gridPane.add(lbTemperatureConvert, 0, 0, 2, 1);
        lbTemperatureConvert.setMaxWidth(Double.MAX_VALUE);
        lbTemperatureConvert.setAlignment(Pos.CENTER);

        gridPane.add(tfResult, 0, 1, 1, 1);
        // tfResult.setMaxWidth(Double.MAX_VALUE);
        tfResult.setMaxWidth(100);

        gridPane.add(lbFahrenheit, 1, 1, 1, 1);   
        lbFahrenheit.setMaxWidth(Double.MAX_VALUE);
        lbFahrenheit.setAlignment(Pos.CENTER);

        gridPane.add(lbOutputResult, 0, 2, 1, 1);
        lbOutputResult.setMaxWidth(Double.MAX_VALUE);
        lbOutputResult.setAlignment(Pos.CENTER_LEFT);

        gridPane.add(lbCelsius, 1, 2, 1, 1);
        lbCelsius.setMaxWidth(Double.MAX_VALUE);
        lbCelsius.setAlignment(Pos.CENTER);

        gridPane.add(rbFtoC, 0, 3, 1, 1);
        gridPane.add(rbCtoF, 1, 3, 1, 1);
        gridPane.add(btConvertTemperature, 0, 4, 2, 1);
        btConvertTemperature.setMaxWidth(Double.MAX_VALUE);
        
        ToggleGroup toggleGroup = new ToggleGroup();

        rbCtoF.setToggleGroup(toggleGroup);
        rbCtoF.setSelected(true);
        rbFtoC.setToggleGroup(toggleGroup);

        rbCtoF.setOnAction(new UnitChange());
        rbFtoC.setOnAction(new UnitChange());

        Scene scene = new Scene(gridPane, 600, 400);
        scene.getStylesheets().add("/css/style.css");
        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public class ClickConvert implements EventHandler{
        
        public void handle(Event event){
            DecimalFormat dcmf = new DecimalFormat();
            dcmf.setMaximumFractionDigits(2);


            try{
                float degree = Float.parseFloat(tfResult.getText());
    
                if(rbCtoF.isSelected()){
                    degree = convertCalculate.convertCtoF(degree);
                    lbOutputResult.setText(String.valueOf(dcmf.format(degree)));
                }
                else if(rbFtoC.isSelected()){
                    degree = convertCalculate.convertFtoC(degree);
                    lbOutputResult.setText(String.valueOf(dcmf.format(degree)));
                }
            }
            catch(Exception exception){
                Alert alert = new Alert(AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                DialogPane dialogPane = alert.getDialogPane();
                // dialogPane.getStylesheets().add("css/style.css");
                // dialogPane.getStyleClass().add("warning");
                alert.setHeaderText("Zadajte číslo!");
                //javac -encoding utf8 ....java
                alert.setTitle("Chyba");
                alert.setContentText("Zadaný vstup musí byť číslo.");
                alert.show();
            }
        }
    }

    public class UnitChange implements EventHandler{
        public void handle(Event event){
            if(rbCtoF.isSelected()){
                lbTemperatureConvert.setText("°C -> °F");
                lbFahrenheit.setText("°C");
                lbCelsius.setText("°F");
            }
            if(rbFtoC.isSelected()){
                lbTemperatureConvert.setText("°F -> °C");
                lbFahrenheit.setText("°F");
                lbCelsius.setText("°C");
            }
        }
    }


    public static void main(String arg[]){
        Application.launch(arg);
    }
}

//javac -encoding utf8 BiTempConvert.java && java BiTempConvert
//javac -encoding utf8 BiTempConvert.java ; java BiTempConvert