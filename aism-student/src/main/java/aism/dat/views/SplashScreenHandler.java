package aism.dat.views;

import java.net.URL;
import java.util.ResourceBundle;

import aism.dat.utils.Configs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SplashScreenHandler implements Initializable {

    @FXML
    ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL file = getClass().getResource(Configs.IMAGE_PATH + "/Logo.png");
        assert file != null : "file is not exist";
        Image image = new Image(file.toString());
        logo.setImage(image);
    }
}