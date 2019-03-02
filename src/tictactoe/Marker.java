package tictactoe;

import termcolor.Color;
import termcolor.Formatter;

public class Marker {

    private char marker;
    private Color color;

    public Marker(char marker, Color color) {
        this.marker = marker;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && getClass() == o.getClass() && o instanceof Marker) {
            return this.marker == ((Marker)o).marker;
        }
        return false;
    }

    @Override
    public String toString() {
        return Formatter.Color(color, String.valueOf(marker));
    }

}
