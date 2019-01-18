package controller;

import classes.IWeatherDataService;
import classes.Location;
import classes.WeatherData;
import classes.WeatherDataServiceFactory;
import exceptions.WeatherDataServiceException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import usbProcess.USBClass;
import usbProcess.USBDeviceDetectorManager;

import javax.swing.text.html.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller extends Application {
    public static Stage stage;
    public IWeatherDataService dataService;
    public WeatherData data;
    public USBClass usbClass;
    public USBDeviceDetectorManager driveDetector;

    public Label Temperature;
    public Label Temper;
    public Label Humidity;
    public Label Pressure;
    public Label Wind;
    public Label usbflash;
    public Button SaveButton;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
            primaryStage.setTitle("Weather");
            primaryStage.setScene(new Scene(root, 600, 350));
            Image image = new Image("file:iphone4-mini-black-15_icon-icons.com_76022.png");
            primaryStage.getIcons().add(image);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        dataService = WeatherDataServiceFactory
                .getWeatherDataService(WeatherDataServiceFactory.service.OPEN_WEATHER_MAP);
        driveDetector = new USBDeviceDetectorManager();

        try
        {
            data = dataService.getWeatherData(new Location("Kiev", "UKRAINE"));
            Temperature.setText(data.getTemperature().toString());
            Humidity.setText(data.getHumidity().toString());
            Pressure.setText(data.getPressure().toString());
            Wind.setText(data.getWind().toString());
            Temper.setText(data.getTemperature().getValue());

            driveDetector.getRemovableDevices();
            usbClass = new USBClass();
            driveDetector.addDriveListener(usbClass);

            if(!driveDetector.getRemovableDevices().isEmpty()) {
                String s = String.format("%n");
                usbflash.setText("информация о погоде успешно сохранена  в файл "
                        + s + " \"weather.properties\""
                        + " на USB устройство "
                        + driveDetector.getRemovableDevices().get(0).getDeviceName()
                );
                usbflash.setVisible(true);
                SaveFile(data.toString(), new File(driveDetector.getRemovableDevices().get(0).getRootDirectory()+"weather.properties"));
            } else {
                usbflash.setVisible(true);
                SaveButton.setVisible(true);
            }
        } catch (WeatherDataServiceException e) {
            e.printStackTrace();
        }
    }

    public void PathToSaveFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
            SaveFile(data.toString(), file);

    }

    private void SaveFile(String content, File file){
       if (!file.isDirectory() && file!=null) {
           if (!file.getName().endsWith(".properties")) {
               file.renameTo(new File(file + ".properties"));
           }
       }
       if(file.isDirectory()){
           file.renameTo(new File(file + "weather.properties"));
       }
        try (
                FileWriter fileWriter = new FileWriter(file  ,true)
        ){
            fileWriter.write(content);
        } catch (IOException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


