package edu.wpi.niftynymphs.controllers;

import edu.wpi.niftynymphs.navigation.Navigation;
import edu.wpi.niftynymphs.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class ConferenceServiceRequestController {

  @FXML MFXButton submitButton;
  @FXML MFXButton filterButton;
  @FXML MFXButton confButton1;
  @FXML MFXButton confButton2;
  @FXML MFXButton confButton3;

  @FXML MFXDatePicker datePicker;
  @FXML MFXComboBox startTimeBox;
  @FXML MFXComboBox endTimeBox;
  @FXML MFXTextField capacityField;

  @FXML
  public void initialize() {
    submitButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    filterButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    confButton1.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    confButton2.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    confButton3.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
