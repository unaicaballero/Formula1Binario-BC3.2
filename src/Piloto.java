import java.io.Serializable;

public class Piloto implements Serializable {

    private String nombre;
    private String escuderia;
    private int puntos;

    public Piloto(String nombre, String escuderia, int puntos) {
        this.nombre = nombre;
        this.escuderia = escuderia;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEscuderia() {
        return escuderia;
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return nombre + " - " + escuderia + " (" + puntos + " puntos)";
    }
}
