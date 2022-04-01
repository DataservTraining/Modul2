package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class StartScreenController implements Initializable {

	@FXML
	private Rectangle rectangle;

	@FXML
	private Slider sldBreite;

	@FXML
	private Slider sldLaenge;

	@FXML
	private TextField txtBreite;

	@FXML
	private TextField txtLaenge;

	@FXML
	private Button btnNeuButton;

	@FXML
	private Button btnSpeichern;

	@FXML
    private ColorPicker colorChooser;
	
	@FXML
	private CheckBox chkFilled;


	private Rechteck rechteck;

	private StringConverter<Number> converter = new NumberStringConverter();

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		btnNeuButton.setOnAction(this::btnNeuButtonHandler);
		btnSpeichern.setOnAction(this::btnSpeichernHandler);
		colorChooser.setOnAction(this::colorChooserHandler);
		chkFilled.setOnAction(this::chkFilledHandler);
	}

	private void btnNeuButtonHandler(ActionEvent e) {
		rechteck = new Rechteck();
		init();

	}

	private void init() {
		if (rechteck != null) {
			txtBreite.setText(Integer.toString(rechteck.getBreite()));
			txtLaenge.setText(Integer.toString(rechteck.getLaenge()));
//			chkFilled.setSelected(false);
//			colorChooser.setValue(Color.WHITE);
			sldBreite.setValue(rechteck.getBreite());
			sldLaenge.setValue(rechteck.getLaenge());
		}

		rechteck.breiteProperty().bindBidirectional(sldBreite.valueProperty());
		rechteck.laengeProperty().bindBidirectional(sldLaenge.valueProperty());
		Bindings.bindBidirectional(txtBreite.textProperty(), rechteck.breiteProperty(), converter);
		Bindings.bindBidirectional(txtLaenge.textProperty(), rechteck.laengeProperty(), converter);
		rectangle.widthProperty().bind(rechteck.breiteProperty());
		rectangle.heightProperty().bind(rechteck.laengeProperty());
		rectangle.strokeProperty().bind(rechteck.colorProperty());
		rechteck.colorProperty().bindBidirectional(colorChooser.valueProperty());
		rechteck.filledProperty().bindBidirectional(chkFilled.selectedProperty());
	}

	private void btnSpeichernHandler(ActionEvent actionevent1) {
		if (rechteck != null) {
			rechteck.breiteProperty().unbindBidirectional(sldBreite.valueProperty());
			rechteck.laengeProperty().unbindBidirectional(sldLaenge.valueProperty());
			Bindings.unbindBidirectional(txtBreite, rechteck.breiteProperty());
			Bindings.unbindBidirectional(txtLaenge, rechteck.laengeProperty());
			rectangle.heightProperty().unbindBidirectional(rechteck.laengeProperty());
			rectangle.widthProperty().unbindBidirectional(rechteck.breiteProperty());
			rechteck.filledProperty().unbindBidirectional(chkFilled.selectedProperty());
			rechteck = null;
		}
	}

	private void colorChooserHandler(ActionEvent actionevent1) {
		fillRectangle();
	}
	
	private void fillRectangle() {
		rectangle.setFill(rechteck.isFilled() ? rechteck.getColor() : Color.TRANSPARENT);
	}

	private void chkFilledHandler(ActionEvent actionevent1) {
		fillRectangle();
	}

}
