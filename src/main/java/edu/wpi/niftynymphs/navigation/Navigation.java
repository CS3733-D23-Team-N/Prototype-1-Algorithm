package edu.wpi.niftynymphs.navigation;

import edu.wpi.niftynymphs.App;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class Navigation {

  private static BorderPane navContainer = null;

  public static void navigate(final Screen screen) {
    final String filename = screen.getFilename();

    try {
      final var resource = App.class.getResource(filename);
      final FXMLLoader loader = new FXMLLoader(resource);

      navContainer.setCenter(loader.load());
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  public static void setNavContainer(BorderPane bp) {
    navContainer = bp;
  }
}
