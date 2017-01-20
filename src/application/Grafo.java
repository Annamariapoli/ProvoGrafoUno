package application;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.BellmanFordShortestPath;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Grafo {
	
	private DirectedGraph<Integer, DefaultEdge> grafo ;
	
	public static void main(String [] args){
		Grafo g = new Grafo();
		g.stampaGrafo();
	//	g.getVicini();
	//	g.getConnessi();
   //   g.getConnessi1();
	//	g.getCammino(2,7);
		g.getNumeroComponentiConnesse();
	}
	
	public void stampaGrafo(){
	grafo = new SimpleDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		for(int i=1; i<8; i++){
			grafo.addVertex(i);
		}
		grafo.addEdge(1, 2);
		grafo.addEdge(2, 5);
		grafo.addEdge(5, 1);
		
		grafo.addEdge(5,4);
		grafo.addEdge(4, 3);
		grafo.addEdge(7,3);
		grafo.addEdge(3,6);
		
		grafo.addEdge(4, 6);
		grafo.addEdge(6,4);
		
		System.out.println(grafo.toString());   //OK
	}
		
		//trovo i vicini di 3
	public List<Integer> getVicini( ){
		List<Integer> vicini = new LinkedList<Integer>();
		vicini = Graphs.neighborListOf(grafo, 3);
		System.out.println(vicini);
		return vicini;
	}
		
	public List<Integer> getConnessi(){
		List<Integer> connessi = new LinkedList<Integer>();
		BreadthFirstIterator <Integer, DefaultEdge> visita = new BreadthFirstIterator<Integer, DefaultEdge>(grafo, 4);
		while(visita.hasNext()){
			Integer t = visita.next();
			connessi.add(t);
		}
		System.out.println(connessi);
		return connessi;
	}
	
	public List<Integer> getConnessi1(){
		List<Integer> connessi = new LinkedList<Integer>();
		DepthFirstIterator <Integer, DefaultEdge> visita = new DepthFirstIterator<Integer, DefaultEdge>(grafo, 7);
		while(visita.hasNext()){
			Integer t = visita.next();
			connessi.add(t);
		}
		System.out.println(connessi);
		return connessi;
	}
	
	public List<Integer> getCammino(Integer i1, Integer i2){
		DijkstraShortestPath<Integer, DefaultEdge> cammino = new DijkstraShortestPath<Integer, DefaultEdge>(grafo, i1, i2 );
		GraphPath<Integer, DefaultEdge> path= cammino.getPath();
		if(path==null){
			System.out.println("null");
			return null;
		}
		List<Integer> percorso=  Graphs.getPathVertexList(path);
		System.out.println(percorso);
		return percorso;
	}
	
	

	public int getNumeroComponentiConnesse(){   //è corretto??
		Integer ret =0;
		List<Integer> vertici = new LinkedList<Integer>();
		vertici.addAll(grafo.vertexSet());
		while(!vertici.isEmpty()){
			ret++;
			BreadthFirstIterator<Integer, DefaultEdge> visita = new BreadthFirstIterator<Integer, DefaultEdge>(grafo, vertici.get(0));
			while(visita.hasNext()){
				vertici.remove(visita.next());
			}
		}
		System.out.println(ret);
		return ret;
		
	}
		
		
		
//		
//		//stampo i nodi successori di ogni nodo:         //OK
//		for(int i = 0; i<8; i++){
//			Set<DefaultEdge> successori = grafo.outgoingEdgesOf(i);
//		//	System.out.println(successori);
//		
//			//altrimenti:	
//			
//			for(Integer v : grafo.vertexSet()){                             //Ok
//				List<Integer> succ = Graphs.successorListOf(grafo, v);
//				System.out.println(succ);
//			}
//			
//		//determino vertice a partire dal quale posso raggiungere il nume magg di altri vertici
//		int max=0;
//		Integer bestRoot=0;
//		for(Integer v : grafo.vertexSet()){
//			BreadthFirstIterator < Integer, DefaultEdge> best = new BreadthFirstIterator<Integer,DefaultEdge>(grafo,v);
//			int contatore=0;
//			while(best.hasNext()){
//				Integer temp=best.next();
//				if(contatore !=0){
//					System.out.println("cammino :" +temp);
//					
//				}
//				contatore++;
//			}
//			if(contatore> max){
//				bestRoot=v;
//				max=contatore;
//			 }
//		 }	
//	  }

}
