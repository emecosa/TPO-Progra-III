package TPO;

public class main {

	public static void main(String[] args) {
		
		GrafoDinamic grafo = new GrafoDinamic();
		GrafoDinamic grafo2 = new GrafoDinamic();
		grafo.inicializarGrafo(0);  // inicializo grafo 
		
		llenarG(grafo); // Ej grafo ejer
		llenarG2(grafo2);// Ej grafo ejer
		System.out.println("Muestro grafo depthFirstSearch ");
		grafo.depthFirstSearch(10);
		System.out.println("Muestro grafo breadthFirstSearch");
		grafo.breadthFirstSearch(10);
		grafo2.kruskal();       // Muestro adentro de la funcion
		grafo2.Prim(grafo2, 1); // Muestro adentro de la funcion
	}
	
	

	
	
	public static void llenarG (GrafoDinamic grafo) {
	
		// Hardcode del llenado del grafo 1
		
		grafo.agregarVertice(10);
		grafo.agregarVertice(19);
		grafo.agregarVertice(25);
		grafo.agregarVertice(22);
		grafo.agregarVertice(12);
		grafo.agregarVertice(7);
		grafo.agregarVertice(5);
		grafo.agregarVertice(3);
		grafo.agregarVertice(8);
		grafo.agregarVertice(15);
		grafo.agregarVertice(27);
		
		// Orientacion del grafo
		
		grafo.agregarArista(10, 19, 1);
		grafo.agregarArista(19, 25, 1);
		grafo.agregarArista(10, 22, 1);
		grafo.agregarArista(10, 12, 1);
		grafo.agregarArista(12, 7, 1);
		grafo.agregarArista(22, 5, 1);
		grafo.agregarArista(22, 15, 1);
		grafo.agregarArista(15, 27, 1);
		grafo.agregarArista(5, 3, 1);
		grafo.agregarArista(5, 8, 1);
		
	
}
	
	public static void llenarG2 (GrafoDinamic grafo) {
		 grafo.agregarVertice(1);
	        grafo.agregarVertice(2);
	        grafo.agregarVertice(3);
	        grafo.agregarVertice(4);
	        grafo.agregarVertice(5);
	        grafo.agregarVertice(6);


	        grafo.agregarArista(1, 2, 6);
	        grafo.agregarArista(1, 3, 1);
	        grafo.agregarArista(1, 4, 5);
	        grafo.agregarArista(3, 2, 5);
	        grafo.agregarArista(4, 3, 5);
	        grafo.agregarArista(2, 5, 3);
	        grafo.agregarArista(6, 4, 2);
	        grafo.agregarArista(3, 6, 4);
	        grafo.agregarArista(5, 6, 6);
	        grafo.agregarArista(5, 3, 6);

	        grafo.agregarArista(2, 1, 6);
	        grafo.agregarArista(3, 1, 1);
	        grafo.agregarArista(4, 1, 5);
	        grafo.agregarArista(2, 3, 5);
	        grafo.agregarArista(3, 4, 5);
	        grafo.agregarArista(5, 2, 3);
	        grafo.agregarArista(4, 6, 2);
	        grafo.agregarArista(6, 3, 4);
	        grafo.agregarArista(6, 5, 6);
	        grafo.agregarArista(3, 5, 6);

		
	
}
	
	

	
}
