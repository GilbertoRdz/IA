public class ArbolBinarioBusqueda {
    Nodo raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarR(raiz, valor);
    }

    private Nodo insertarR(Nodo nodo, int valor) {

        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }
        if (valor < nodo.valor) {
            nodo.izquierda = insertarR(nodo.izquierda, valor);
        }
        else if (valor > nodo.valor) {
            nodo.derecha = insertarR(nodo.derecha, valor);
        }
        return nodo;
    }
    public void inorder() {
        inorderR(raiz);
    }

    private void inorderR(Nodo nodo) {
        if (nodo != null) {
            inorderR(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            inorderR(nodo.derecha);
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        arbol.insertar(10);
        arbol.insertar(3);
        arbol.insertar(7);
        arbol.insertar(2);
        arbol.insertar(4);
        arbol.insertar(6);
        arbol.insertar(5);

        if(arbol.estaVacio()){
            System.out.println("El arbol esta vacio");
        }else {
            System.out.println("Recorrido:");
            arbol.inorder();
        }
    }
}