package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class StartScreenController implements Initializable {

	ObservableList<Rechteck> rechtecke = FXCollections.observableArrayList();

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

	@FXML
	private TableView<Rechteck> tblRechtecke;

	@FXML
	private TableColumn<Rechteck, Integer> colBreite;

	@FXML
	private TableColumn<Rechteck, Color> colColor;

	@FXML
	private TableColumn<Rechteck, Boolean> colFilled;

	@FXML
	private TableColumn<Rechteck, Integer> colFlaeche;

	@FXML
	private TableColumn<Rechteck, Integer> colLaenge;

	private Rechteck rechteck;

	private StringConverter<Number> converter = new NumberStringConverter();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		btnNeuButton.setOnAction(this::btnNeuButtonHandler);
		btnSpeichern.setOnAction(this::btnSpeichernHandler);
		colorChooser.setOnAction(this::colorChooserHandler);
		chkFilled.setOnAction(this::chkFilledHandler);

		tblRechtecke.setItems(rechtecke);

		colBreite = (TableColumn<Rechteck, Integer>) tblRechtecke.getColumns().get(0);
		colLaenge = (TableColumn<Rechteck, Integer>) tblRechtecke.getColumns().get(1);
		colFlaeche = (TableColumn<Rechteck, Integer>) tblRechtecke.getColumns().get(2);
		colColor = (TableColumn<Rechteck, Color>) tblRechtecke.getColumns().get(3);
		colFilled = (TableColumn<Rechteck, Boolean>) tblRechtecke.getColumns().get(4);

		colBreite.setCellValueFactory(new PropertyValueFactory<Rechteck, Integer>("breite"));
		colLaenge.setCellValueFactory(new PropertyValueFactory<Rechteck, Integer>("laenge"));
		colFlaeche.setCellValueFactory(new PropertyValueFactory<Rechteck, Integer>("flaeche"));
		colFilled.setCellValueFactory(new PropertyValueFactory<Rechteck, Boolean>("filled"));
		colFilled.setCellFactory(CheckBoxTableCell.forTableColumn(colFilled));
		colColor.setCellValueFactory(new PropertyValueFactory<Rechteck, Color>("color"));
		colColor.setCellFactory(new ColorCellFactory());
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
			rechtecke.add(rechteck);
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

	class ColorCellFactory implements Callback<TableColumn<Rechteck, Color>, TableCell<Rechteck, Color>> {

		@Override
		public TableCell<Rechteck, Color> call(TableColumn<Rechteck, Color> arg0) {
		
			return new TableCell<Rechteck, Color>() {

				@Override
				public void updateIndex(int i) {
					System.out.println("index: " + i);
//					super.updateIndex(i);
					// select color based on index of row/column
					if (i> -1 && i < rechtecke.size()) {
						Rechteck r = rechtecke.get(i);
						String color = r.getColor().toString();
						System.out.println(color);
						this.setStyle("-fx-background-color: #" + color.substring(2, 8) + ";");
					}
				}
		};
		}

	}

}
