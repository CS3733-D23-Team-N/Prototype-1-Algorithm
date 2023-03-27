package edu.wpi.niftynymphs.controllers;

import edu.wpi.niftynymphs.navigation.Navigation;
import edu.wpi.niftynymphs.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class ServiceRequestController {

  @FXML MFXButton backButton;

  @FXML
  public void initialize() {
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
