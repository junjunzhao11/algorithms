package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShortestPath {
	static List<Vertex> nodes;
	
	static List<Edge> edges;
	public static void main(String[] args) {
		
		List<ConveyorSystem> conveyorSystem = new ArrayList<ConveyorSystem>();
		List<Flight> departures = new ArrayList<Flight>();
		List<Bag> bags = new ArrayList<Bag>();

		// init conveyor system
		conveyorSystem.add(new ConveyorSystem("Concourse_A_Ticketing", "A5", 5));
		conveyorSystem.add(new ConveyorSystem("A5", "BaggageClaim", 5));
		conveyorSystem.add(new ConveyorSystem("A5", "A10", 4));
		conveyorSystem.add(new ConveyorSystem("A5", "A1", 6));
		conveyorSystem.add(new ConveyorSystem("A1", "A2", 1));
		conveyorSystem.add(new ConveyorSystem("A2", "A3", 1));
		conveyorSystem.add(new ConveyorSystem("A3", "A4", 1));
		conveyorSystem.add(new ConveyorSystem("A10", "A9", 1));
		conveyorSystem.add(new ConveyorSystem("A9", "A8", 1));
		conveyorSystem.add(new ConveyorSystem("A8", "A7", 1));
		conveyorSystem.add(new ConveyorSystem("A7", "A6", 1));

		// init departures
		departures.add(new Flight("UA10", "A1", "MIA", "08:00"));
		departures.add(new Flight("UA11", "A1", "LAX", "09:00"));
		departures.add(new Flight("UA12", "A1", "JFK", "09:45"));
		departures.add(new Flight("UA13", "A2", "JFK", "08:30"));
		departures.add(new Flight("UA14", "A2", "JFK", "09:45"));
		departures.add(new Flight("UA15", "A2", "JFK", "10:00"));
		departures.add(new Flight("UA16", "A3", "JFK", "09:00"));
		departures.add(new Flight("UA17", "A4", "MHT", "09:15"));
		departures.add(new Flight("UA18", "A5", "LAX", "10:15"));

		// init bags
		bags.add(new Bag("0001", "Concourse_A_Ticketing", "UA12"));
		bags.add(new Bag("0002", "A5", "UA17"));
		bags.add(new Bag("0003", "A2", "UA10"));
		bags.add(new Bag("0004", "A8", "UA18"));
		bags.add(new Bag("0005", "A7", "ARRIVAL"));
		
		
		
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		//init vertex
		Vertex v1 = new Vertex("Concourse_A_Ticketing", "Concourse_A_Ticketing");
		Vertex v2 = new Vertex("BaggageClaim", "BaggageClaim");
		nodes.add(v1);
		nodes.add(v2);
		for (int i = 1; i < 11; i++) {
			Vertex location = new Vertex("A" + i, "A" + i);
			nodes.add(location);
		}
		
		//init edges
		addEdge("Edge_0", 0, 6, 5);
		addEdge("Edge_1", 6, 1, 5);
		addEdge("Edge_2", 6, 11, 4);
		addEdge("Edge_3", 6, 2, 6);
		addEdge("Edge_4", 2, 3, 1);
		addEdge("Edge_5", 3, 4, 1);
		addEdge("Edge_6", 4, 5, 1);
		addEdge("Edge_7", 11, 10, 1);
		addEdge("Edge_8", 10, 9, 1);
		addEdge("Edge_9", 9, 8, 1);
		addEdge("Edge_10", 8, 7, 1);
		
		addEdge("Edge_11", 6, 0, 5);
		addEdge("Edge_12", 1, 6, 5);
		addEdge("Edge_13", 11, 6, 4);
		addEdge("Edge_14", 2, 6, 6);
		addEdge("Edge_15", 3, 2, 1);
		addEdge("Edge_16", 4, 3, 1);
		addEdge("Edge_17", 5, 4, 1);
		addEdge("Edge_18", 10, 11, 1);
		addEdge("Edge_19", 9, 10, 1);
		addEdge("Edge_20", 8, 9, 1);
		addEdge("Edge_21", 7, 8, 1);
		
		Map<String, Integer> gateNum = new HashMap<String, Integer>();
		gateNum.put("Concourse_A_Ticketing", 0);
		gateNum.put("BaggageClaim", 1);
		gateNum.put("A1", 2);
		gateNum.put("A2", 3);
		gateNum.put("A3", 4);
		gateNum.put("A4", 5);
		gateNum.put("A5", 6);
		gateNum.put("A6", 7);
		gateNum.put("A7", 8);
		gateNum.put("A8", 9);
		gateNum.put("A9", 10);
		gateNum.put("A10", 11);
		
		Map<String, String> flightGate = new HashMap<String, String>();
		for(Flight f : departures){
			flightGate.put(f.getFlightId(), f.getFlightGate());
		}
		

		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		
		//use Dijkstra Algorithm to compute path and shortest time. 
		for(Bag b : bags){
			dijkstra.execute(nodes.get(gateNum.get(b.getEntryPoint())));
			String flightid = b.getFlightId();
			Integer gate;
			if(flightid.equals("ARRIVAL")){
				gate = 1;
			}
			else{
				gate = gateNum.get(flightGate.get(b.getFlightId()));
			}
			LinkedList<Vertex> path = dijkstra.getPath(nodes.get(gate));
			
			for (Vertex vertex : path) {
				System.out.print(vertex + " ");
			}
			
			System.out.println(": " + dijkstra.distance.get(nodes.get(gate)));
		}
		
	}

	private static void addEdge(String laneId, int sourceLocNo, int destLocNo, int duration){
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		edges.add(lane);
	}
}