package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Rechteck {
		private IntegerProperty breite = new SimpleIntegerProperty();
		private IntegerProperty laenge = new SimpleIntegerProperty();
		private IntegerProperty flaeche = new SimpleIntegerProperty();
		private ObjectProperty<Color> color = new SimpleObjectProperty<>();
		private BooleanProperty filled = new SimpleBooleanProperty();
		
		public Rechteck() {
			this(200, 200, Color.CORAL, false);
		}
		
		public Rechteck(int breite, int laenge, Color color, boolean filled) {
			super();
			setBreite(breite);
			setLaenge(laenge);
			this.flaeche.set(getFlaeche());
			this.color.set(color);
			this.filled.set(filled);
		}
		
		public int getFlaeche() {
			return laenge.get() * breite.get();
		}

		public int getLaenge() { return laenge.get(); }
		public int getBreite() { return breite.get(); }
		public int getflaeche() { return flaeche.get(); }
		
		public void setLaenge(int laenge) { 
			if(laenge < 0) laenge = 0;
			if(laenge > 400) laenge = 400;
			this.laenge.set(laenge);
		}

		public void setBreite(int breite) { 
			if(breite < 0) breite = 0;
			if(breite > 400) breite = 400;
			this.breite.set(breite);
		}
		
		public void setfilled(boolean filled) { 
			this.filled.set(filled);
		}
		
		public boolean isFilled() { return filled.get();}
		
		public Color getColor() { return color.get();}
		public void setColor(Color color) {
			this.color.set(color);
		}

		public IntegerProperty breiteProperty() {
			return breite;
		}
		
		public IntegerProperty laengeProperty() {
			return laenge;
		}
		
		public BooleanProperty filledProperty() {
			return filled;
		}
		
		public IntegerProperty flaecheProperty() {
			flaeche.set(getBreite() * getLaenge());
			return flaeche;
		}
		
		public ObjectProperty<Color> colorProperty(){
			return color;
		}
		

//		public void setFilled(BooleanProperty filled) {
//			this.filled = filled;
//		}
//
//		public void setBreite(IntegerProperty breite) {
//			this.breite = breite;
//		}
//
//		public void setLaenge(IntegerProperty laenge) {
//			this.laenge = laenge;
//		}

//		public void setFlaeche(IntegerProperty flaeche) {
//			this.flaeche = flaeche;
//		}

		public void setColor(ObjectProperty<Color> color) {
			this.color = color;
		}
		
		
		
		
}
