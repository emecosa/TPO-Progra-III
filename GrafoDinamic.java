package TPO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.text.GapContent;

public class GrafoDinamic implements GrafosTDA {
	NodoGrafo nodo;
	int cantidad;

	public void inicializarGrafo(int dim) {
		nodo = null;
		cantidad = 0;
	}
	public void eliminarVertice(int v) {
		NodoGrafo aux = encontrarNodo(v);
		
		if(aux != null) {
			aux = nodo; 
			if(aux.valor == v) {
				nodo = aux.sig;
				System.out.println("1");
			}
			while(aux != null) {
				this.eliminarArista(aux.valor, v);	
				if(aux.sig == this.encontrarNodo(v) && aux.sig != null) {
					aux.sig = aux.sig.sig;
					cantidad--;
					System.out.println("1");
				}
					aux = aux.sig;
					System.out.println("2");
			}
			
		}else {
			System.out.println("El vertice no existe");
		}

		
	}

	public void agregarVertice(int v) {
		if(this.encontrarNodo(v) == null) {
			
		
			NodoGrafo aux = new NodoGrafo();
			aux.valor = v;
			aux.lista = null;
			aux.sig = nodo;
			aux.Visitado = false;
			aux.marcado = false;
			nodo = aux;
			cantidad++;
		}	
			
	}

	
	public int[] vertices() {
		
		return null;
	}

	public void agregarArista(int v1, int v2, int peso) {
		if(this.encontrarNodo(v1) != null && this.encontrarNodo(v2) != null) {
			NodoArista aux = new NodoArista();
			aux.origen = v1;
			aux.peso = peso;
			aux.Visitado = false;
			aux.apunta = this.encontrarNodo(v2);
			aux.sig = encontrarNodo(v1).lista;
			encontrarNodo(v1).lista = aux; 
		}else {
			System.out.println("No existe alguino de los noods");
		}
	}
	

	
	public void eliminarArista(int v1, int v2) {
		NodoGrafo nodo = encontrarNodo(v1);
		NodoArista arista = nodo.lista;
		if(arista != null) {
			if(arista.apunta.valor == v2) {				
				nodo.lista = arista.sig;
			}
			else {
				while(arista.sig != null && arista.apunta.valor == v2) {
					arista = arista.sig;
				}
				if(arista.sig != null) {
					arista.sig = arista.sig.sig;
				}				
			}			
		}
	}
	
	


	public boolean existeArista(int v1, int v2) {
		NodoGrafo aux = this.encontrarNodo(v1);
		NodoArista arista = aux.lista ;
		while(arista != null) {
			if(arista.apunta.valor ==v2) {
				return true;
			}
			arista = arista.sig;
		}		
		return false;
	}

	public int pesoArista(int v1, int v2) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void mostrarMatriz() {
		NodoGrafo aux = nodo;
		NodoArista arista;
		while(aux != null) {
		System.out.print(aux.valor + "\t" );
		arista = aux.lista;
			while(arista != null) {
				
				System.out.print(arista.peso + " "  + arista.apunta.valor +  "\t");
				arista = arista.sig;
			}
			System.out.println();
			aux = aux.sig;
		}
	}

	public boolean pertenece(int x) {
		if (this.encontrarNodo(x) != null) {
			return true;
		}
		return false;
	}

	public int mayorArista(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int[] conjuntoAislados() {
		int aislados[]= new int [cantidad];
		NodoGrafo aux = nodo;
		
		while(aux != null) {
			
		}
		return null;
	}

	public void imparAristas() {
		// TODO Auto-generated method stub
		
	}
	
	public NodoGrafo encontrarNodo(int v) {
		NodoGrafo aux = nodo;
		while(aux != null) {
			if(aux.valor == v) {
				return aux;
			}
			aux = aux.sig;
		}
		return null;
	}

	
	private ArrayList<NodoArista> calcularAristas(NodoGrafo vertice) {
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		NodoArista aristaAux = new NodoArista();
		aristaAux = vertice.lista;
		while (aristaAux != null) {
			aristas.add(aristaAux);
			aristaAux = aristaAux.sig;
		}
		return aristas;
	}
	
	public ArrayList<NodoArista> adyacente(NodoGrafo nodo)
	{
		ArrayList<NodoArista> aristasAdj = new ArrayList<NodoArista>();
		NodoArista arista;

		arista = nodo.lista;
		while(arista != null)
		{
			aristasAdj.add(arista);
			arista = arista.sig;
		}

		return aristasAdj;
	}
	
	public void depthFirstSearch(int origen) {
		NodoGrafo nodo = encontrarNodo(origen); // Apuntamos el nuevo nodo con respecto a origen pasado por parametro
		ArrayList<NodoArista> lista = adyacente(nodo); // Utilizamos el metodo adyacente para traer un Arraylis de aristas con los adyacentes
		for (NodoArista nodoArista : lista) {
			depthFirstSearch(nodoArista.apunta.valor); // Llamamos al metodo recursivo
		}
		System.out.println(nodo.valor); // Mostramos el valor a medida que lo encontramos
	}
	
	public void breadthFirstSearch(int origen) {
		NodoGrafo nodo = encontrarNodo(origen);// Apuntamos el nuevo nodo con respecto a origen pasado por parametro
		ArrayList<Integer> cola = new ArrayList<Integer>();
		cola.add(nodo.valor);
		while (!cola.isEmpty())
		{
			nodo = encontrarNodo(cola.get(0));
			cola.remove(0);
			for (NodoArista nodoArista : adyacente(nodo)) {
				cola.add(nodoArista.apunta.valor);
			}
			if(!nodo.marcado) // Preguntamos si el nodo no esta marcado para que no repita 
			{
				nodo.marcado = true; // Si es la primera vez que entra, lo marcamos como true para que no lo vuelva a imprimir.
				System.out.println(nodo.valor);
			}
		}
	}
	/*
	 * Lo que se trata de hacer en esta funcion es comparar todo el tiempo, todos los vertices , con que arista (de menor peso) se puede conectar
	 * una vez que se encuentra un vertice y una arista que conecta a otro vertice, se guarda en un auxiliar y luego se vuelven a comparar los dos
	 * para saber si la proxima menor arista corresponde a alguno de los dos. Luego se vuelve a añadir el vertice siguiente y se vuelve a comparar 
	 * con los 3. Y asi sucesivamente comparando con todos los vertices auxiliares. Las aristas a medida que se usan, se van eliminando del
	 * array, para que no las vuelva a repetir.
	 */
	
	public GrafosTDA Prim(GrafoDinamic Gra, int x){ 
	    GrafoDinamic GrafoNuevo = new GrafoDinamic();
	    GrafoNuevo.inicializarGrafo(Gra.cantidad);
	    GrafoNuevo.agregarVertice(x);

	    NodoArista aristaActual;
        ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();

        ArrayList<Integer> verticesPendientes = new ArrayList<Integer>();
        for(Integer vertice : Gra.obtenerNodos()){
            if(vertice != x && !verticesPendientes.contains(vertice)) {
                verticesPendientes.add(vertice);
            }
        }

        while(!verticesPendientes.isEmpty()){
            for(Integer vertice : GrafoNuevo.obtenerNodos()){ // Obetenemos un ArrayList de enteros con todos los vertices de CADA nodo.
                aristas.addAll(Gra.adyacente(Gra.encontrarNodo(vertice))); // Agregamos las aristas adyacentes para cada nodo
            }
            Collections.sort(aristas); // Las ordenamos de menor a mayor.
            aristaActual = aristas.get(0);
            while(aristaActual != null)
            {
                if(GrafoNuevo.pertenece(aristaActual.origen) &&
                        verticesPendientes.contains(aristaActual.apunta.valor))
                {
                    GrafoNuevo.agregarVertice(aristaActual.apunta.valor);
                    GrafoNuevo.agregarArista(aristaActual.origen, aristaActual.apunta.valor, aristaActual.peso);
                    Gra.eliminarArista(aristaActual.origen, aristaActual.apunta.valor);
                    Gra.eliminarArista(aristaActual.apunta.valor, aristaActual.origen);
                    verticesPendientes.remove(verticesPendientes.indexOf(aristaActual.apunta.valor));
                    aristaActual = null;
                }
                else
                {
                    aristaActual = aristas.get(aristas.indexOf(aristaActual)+1);
                }
            }
            aristas = new ArrayList<NodoArista>();
        }
        System.out.println("Muestro grafo Prim");
        GrafoNuevo.mostrarG();
        return GrafoNuevo;
    }
	
	public  void mostrarG () { //Nuevo mostrar
		
		NodoGrafo aux = nodo;
		NodoArista arista;
		while(aux != null) {
		System.out.print( "V: " +  aux.valor);
		arista = aux.lista;
			while(arista != null) {
				
				System.out.print( " A:" +arista.apunta.valor + " P:"+ arista.peso  );
				arista = arista.sig;
			}
			System.out.println();
			aux = aux.sig;
		}
	}
	
	/*
	 * En este algoritmo lo que tratamos de hacer es tener un auxiliar de vertices y de aristas (ordenadas de menor a mayor)
	 * Y luego completamos un arreglo de conjuntos donde en cada conjunto va a haber un vertice. Creamos el grafo auxuliar con todos los vertices
	 * y luego lo vamos llenando con las aristas que correspondan luego de la comparacion de conjunto origen y conjunto destino.
	*/
	public GrafoDinamic kruskal() {
		
		GrafoDinamic gf  = new GrafoDinamic();
		ArrayList<Integer>verticesL = new ArrayList<Integer>();
		ArrayList<NodoArista>aristas = new ArrayList<NodoArista>();
		ArrayList<ConjuntoTA>con = new ArrayList<ConjuntoTA>();
		
		verticesL= obtenerNodos();
		
		
		for (int i = 0 ; i< verticesL.size () ; i++) { //llenamos a conjunto con todos los vertices
			
			ConjuntoTA conjunto = new ConjuntoTA();	
			conjunto.InicializarConjunto();
			conjunto.Agregar(verticesL.get(i));
			con.add(conjunto);
			
			gf.agregarVertice(verticesL.get(i));
			
		}
		
		aristas=obtenerAristas(); //Paso a aristas todos los adyacentes del nodo
		
		Collections.sort(aristas);
	
		while (con.size()>1) {
			
			ArrayList<ConjuntoTA> aux = new ArrayList<ConjuntoTA>(); // conjunto auxiliar que voy a usar para comparar contra el conjunto original
			ConjuntoTA co = new ConjuntoTA(); // conjunto origen
			ConjuntoTA cd = new ConjuntoTA();// conjunto destino
			NodoArista aAux = new NodoArista(); // auxiliar de arista
			
			int vo= 0, vd= 0;
			
			co.InicializarConjunto(); // inicializo
			cd.InicializarConjunto();
			aAux = aristas.get(0);
			aristas.remove(0);
			
			for (int i=0 ; i < con.size(); i++) { // todas las pos origen y destino
				
				if(con.get(i).Pertenece(aAux.origen)){
					vo=i;
					co= con.get(i);
				}
				
				if (con.get(i).Pertenece(aAux.apunta.valor)) {
					vd = i;
					cd=con.get(i);
					
				}
			}
			if (vo != vd) {
				con.remove(co); // remuevo el que usé
				con.remove(cd);
				while (!cd.ConjuntoVacio()) {
					int x = cd.Elegir();
					co.Agregar(x); // agrego al conjunto
					cd.Sacar(x);
				}
				 
				aux.add(co);
				aux.addAll(con);
				con = aux;
				gf.agregarArista(aAux.origen, aAux.apunta.valor,aAux.peso); //Uno los vertices con la arista menor obtenida arriba.
			}
			
		}
		System.out.println("Muestro grafo kruskal ");
		System.out.println("");
		gf.mostrarG();
		System.out.println("");
		return gf;
		
	}

	public ArrayList<Integer> obtenerNodos (){
	    ArrayList<Integer> vertices = new ArrayList<Integer>();
	    NodoGrafo aux;
	    aux = this.nodo;
	    vertices.add(aux.valor);
	    while(aux.sig != null){
	        vertices.add(aux.sig.valor);
	        aux = aux.sig;
        }
	    return vertices;
    }
	public ArrayList<NodoArista> obtenerAristas (){
		ArrayList<NodoGrafo> listaNodos = new ArrayList<NodoGrafo>();
		ArrayList<NodoArista> listaAristas = new ArrayList<NodoArista>();

		for (Integer nodo : this.obtenerNodos()){
			listaNodos.add(this.encontrarNodo(nodo));
		}

		NodoArista aux;

		for(NodoGrafo nodo : listaNodos){
			aux = nodo.lista;
			listaAristas.add(aux);
			while(aux.sig != null){
				aux = aux.sig;
				listaAristas.add(aux);
			}
		}

		return listaAristas;
	}
}

