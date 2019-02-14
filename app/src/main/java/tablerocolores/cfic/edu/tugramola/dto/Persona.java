package tablerocolores.cfic.edu.tugramola.dto;


public class Persona {

    private int id;
    private String nombre;
    private String foto;
    private String git;
    private String lin;


    public Persona() {}

    public Persona(int id, String nombre, String foto, String git, String lin) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.git = git;
        this.lin = lin;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public String getGit() {
        return git;
    }

    public String getLin() {
        return lin;
    }

}
