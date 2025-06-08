import java.util.ArrayList;
import java.util.List;

public class Objeto {
    List<Face> faces;

    public Objeto() {

        this.faces = new ArrayList<>();


    }

    public void addFace(Face face) {

        faces.add(face);

    }

    public List<Face> getFaces() {
        return faces;
    }


}

