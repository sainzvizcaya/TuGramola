package tablerocolores.cfic.edu.tugramola.dto;

import java.util.List;

public class ResultadoCanciones {

    //Estas dos variables son las que tiene el JSON y se tiene que llamar TAL CUAL

    private int resultCount;
    private List <Cancion> results;

    //Implementamos los GETTER y SETTER

    public int getResultCount() { return resultCount; }

    public void setResultCount(int resultCount) { this.resultCount = resultCount; }

    public List<Cancion> getResults() { return results; }

    public void setResults(List<Cancion> results) { this.results = results; }

}
