package edu.wpi.niftynymphs.controllers;

import edu.wpi.niftynymphs.navigation.Navigation;
import edu.wpi.niftynymphs.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class RootController {

  // Panes
  @FXML GridPane outerGridPane;
  @FXML GridPane innerGridPane;
  @FXML BorderPane innerBorderPane;

  // Images in nav bar
  @FXML ImageView homeLogo;
  @FXML ImageView helpIcon;
  @FXML ImageView exitIcon;

  @FXML
  public void navigateHome() {
    Navigation.navigate(Screen.HOME);
  }

  @FXML
  public void navigateHelp() {
    Navigation.navigate(Screen.HELP);
  }

  @FXML
  public void exitApp() {
    Platform.exit();
  }

  @FXML
  public void initialize() {}
}
