public class FileReader {


    public Objeto createObject() {

        Objeto meuObjeto = new Objeto();

        Vertice v1 = new Vertice('a', 30, 200, 0);
        Vertice v2 = new Vertice('b', 70, 200, 0);
        Vertice v3 = new Vertice('c', 90, 200, 50);
        Vertice v4 = new Vertice('d', 70, 200, 100);
        Vertice v5 = new Vertice('e', 30, 200, 100);
        Vertice v6 = new Vertice('f', 10, 200, 50);
        Vertice v7 = new Vertice('g', 20, 100, 0);
        Vertice v8 = new Vertice('h', 80, 100, 0);
        Vertice v9 = new Vertice('i', 100, 100, 50);
        Vertice v10 = new Vertice('j', 80, 100, 100);
        Vertice v11 = new Vertice('k', 20, 100, 100);
        Vertice v12 = new Vertice('l', 0, 100, 50);
        Vertice v13 = new Vertice('m', 30, 0, 0);
        Vertice v14 = new Vertice('n', 70, 0, 0);
        Vertice v15 = new Vertice('o', 90, 0, 5);
        Vertice v16 = new Vertice('p', 70, 0, 100);
        Vertice v17 = new Vertice('q', 30, 0, 100);
        Vertice v18 = new Vertice('r', 10, 0, 50);



        Aresta a1 = new Aresta("A1", v7, v8);
        Aresta a2 = new Aresta("A2", v8, v9);
        Aresta a3 = new Aresta("A3", v9, v10);
        Aresta a4 = new Aresta("A4", v10, v11);
        Aresta a5 = new Aresta("A5", v11, v12);
        Aresta a6 = new Aresta("A6", v12, v7);
        Aresta a7 = new Aresta("A7", v1, v2);
        Aresta a8 = new Aresta("A8", v2, v3);
        Aresta a9 = new Aresta("A9", v3, v4);
        Aresta a10 = new Aresta("A10", v4, v5);
        Aresta a11 = new Aresta("A11", v5, v6);
        Aresta a12 = new Aresta("A12", v6, v1);
        Aresta a13 = new Aresta("A13", v13, v14);
        Aresta a14 = new Aresta("A14", v14, v15);
        Aresta a15 = new Aresta("A15", v15, v16);
        Aresta a16 = new Aresta("A16", v16, v17);
        Aresta a17 = new Aresta("A17", v17, v18);
        Aresta a18 = new Aresta("A18", v18, v13);
        Aresta a19 = new Aresta("A19", v12, v18);
        Aresta a20 = new Aresta("A20", v1, v7);
        Aresta a21 = new Aresta("A21", v2, v8);
        Aresta a22 = new Aresta("A22", v3, v9);
        Aresta a23 = new Aresta("A23", v4, v10);
        Aresta a24 = new Aresta("A24", v5, v11);
        Aresta a25 = new Aresta("A25", v6, v12);
        Aresta a26 = new Aresta("A26", v7, v13);
        Aresta a27 = new Aresta("A27", v8, v14);
        Aresta a28 = new Aresta("A28", v9, v15);
        Aresta a29 = new Aresta("A29", v10, v16);
        Aresta a30 = new Aresta("A30", v11, v17);

        Face f1 = new Face("F1");
        Face f2 = new Face("F2");
        Face f3 = new Face("F3");
        Face f4 = new Face("F4");
        Face f5 = new Face("F5");
        Face f6 = new Face("F6");
        Face f7 = new Face("F7");
        Face f8 = new Face("F8");
        Face f9 = new Face("F9");
        Face f10 = new Face("F10");
        Face f11 = new Face("F11");
        Face f12 = new Face("F12");
        Face f13 = new Face("F13");
        Face f14 = new Face("F14");

        f1.addAresta(a7);
        f1.addAresta(a21);
        f1.addAresta(a1);
        f1.addAresta(a20);

        f2.addAresta(a8);
        f2.addAresta(a22);
        f2.addAresta(a2);
        f2.addAresta(a21);

        f3.addAresta(a9);
        f3.addAresta(a23);
        f3.addAresta(a3);
        f3.addAresta(a22);

        f4.addAresta(a10);
        f4.addAresta(a24);
        f4.addAresta(a4);
        f4.addAresta(a23);

        f5.addAresta(a11);
        f5.addAresta(a24);
        f5.addAresta(a5);
        f5.addAresta(a25);

        f6.addAresta(a12);
        f6.addAresta(a20);
        f6.addAresta(a6);
        f6.addAresta(a25);

        f7.addAresta(a7);
        f7.addAresta(a8);
        f7.addAresta(a9);
        f7.addAresta(a10);
        f7.addAresta(a11);
        f7.addAresta(a12);

        f8.addAresta(a1);
        f8.addAresta(a27);
        f8.addAresta(a13);
        f8.addAresta(a26);

        f9.addAresta(a2);
        f9.addAresta(a28);
        f9.addAresta(a14);
        f9.addAresta(a27);

        f10.addAresta(a3);
        f10.addAresta(a29);
        f10.addAresta(a15);
        f10.addAresta(a28);

        f11.addAresta(a4);
        f11.addAresta(a30);
        f11.addAresta(a29);
        f11.addAresta(a16);

        f12.addAresta(a30);
        f12.addAresta(a5);
        f12.addAresta(a19);
        f12.addAresta(a17);

        f13.addAresta(a18);
        f13.addAresta(a26);
        f13.addAresta(a6);
        f13.addAresta(a19);

        f14.addAresta(a13);
        f14.addAresta(a14);
        f14.addAresta(a15);
        f14.addAresta(a16);
        f14.addAresta(a17);
        f14.addAresta(a18);

        f1.addVertice(v1);
        f1.addVertice(v2);
        f1.addVertice(v8);
        f1.addVertice(v7);

        f2.addVertice(v2);
        f2.addVertice(v3);
        f2.addVertice(v9);
        f2.addVertice(v8);

        f3.addVertice(v3);
        f3.addVertice(v4);
        f3.addVertice(v10);
        f3.addVertice(v9);

        f4.addVertice(v4);
        f4.addVertice(v5);
        f4.addVertice(v11);
        f4.addVertice(v10);

        f5.addVertice(v5);
        f5.addVertice(v6);
        f5.addVertice(v12);
        f5.addVertice(v11);

        f6.addVertice(v1);
        f6.addVertice(v7);
        f6.addVertice(v12);
        f6.addVertice(v6);

        f7.addVertice(v1);
        f7.addVertice(v2);
        f7.addVertice(v3);
        f7.addVertice(v4);
        f7.addVertice(v5);
        f7.addVertice(v6);

        f8.addVertice(v7);
        f8.addVertice(v8);
        f8.addVertice(v14);
        f8.addVertice(v13);

        f9.addVertice(v8);
        f9.addVertice(v9);
        f9.addVertice(v15);
        f9.addVertice(v14);

        f10.addVertice(v9);
        f10.addVertice(v10);
        f10.addVertice(v16);
        f10.addVertice(v15);

        f11.addVertice(v10);
        f11.addVertice(v16);
        f11.addVertice(v17);
        f11.addVertice(v11);

        f12.addVertice(v11);
        f12.addVertice(v17);
        f12.addVertice(v12);
        f12.addVertice(v18);

        f13.addVertice(v7);
        f13.addVertice(v13);
        f13.addVertice(v18);
        f13.addVertice(v12);

        f14.addVertice(v13);
        f14.addVertice(v14);
        f14.addVertice(v15);
        f14.addVertice(v16);
        f14.addVertice(v17);
        f14.addVertice(v18);

        meuObjeto.addFace(f1);
        meuObjeto.addFace(f2);
        meuObjeto.addFace(f3);
        meuObjeto.addFace(f4);
        meuObjeto.addFace(f5);
        meuObjeto.addFace(f6);
        meuObjeto.addFace(f7);
        meuObjeto.addFace(f8);
        meuObjeto.addFace(f9);
        meuObjeto.addFace(f10);
        meuObjeto.addFace(f12);
        meuObjeto.addFace(f12);
        meuObjeto.addFace(f13);
        meuObjeto.addFace(f14);

        return meuObjeto;
    }
}



