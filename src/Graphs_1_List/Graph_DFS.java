package Graphs_1_List;

import java.util.*;

public class Graph_DFS {

	static class Node {
		int data;

		Node(int data) {
			this.data = data;
		}
	}

	static class Graph {
		static List<LinkedList<Node>> alist;
		static Map<Integer, Integer> dataIdxMap;

		Graph() {
			alist = new ArrayList<>();
			dataIdxMap = new HashMap<>();
		}

		public static void Create(Node node) {
			LinkedList<Node> currentList = new LinkedList<>();
			currentList.add(node);
			alist.add(currentList);
			dataIdxMap.put(node.data, alist.size() - 1);
		}

		public static void AddEdges(int src, int dst) {
			LinkedList<Node> List_1 = alist.get(src);
			Node dstNode = alist.get(dst).get(0);
			List_1.add(dstNode);
			// Comment if not directed
			LinkedList<Node> List_2 = alist.get(dst);
			Node srcNode = alist.get(src).get(0);
			List_2.add(srcNode);
		}

		public static void print() {
			System.out.println("The Adjecency List :-");
			for (int i = 0; i < alist.size(); i++) {
				System.out.print(alist.get(i).get(0).data + "->");
				for (int j = 1; j < alist.get(i).size(); j++) {
					System.out.print(alist.get(i).get(j).data + " ");
				}
				System.out.println();
			}

		}

		public static void DFS() {
			boolean visited[] = new boolean[alist.size()];
			System.out.println("DFS Full Graph Traverse:");
			for (int i = 0; i < alist.size(); i++) {
				if (!visited[i]) {
					helper(i, visited);
				}
			}
		}

		public static void helper(int currentidx, boolean[] visited) {
			visited[currentidx] = true;
			System.out.print(alist.get(currentidx).get(0).data + " ");
			for (int i = 1; i < alist.get(currentidx).size(); i++) {
				int neighbourdata = alist.get(currentidx).get(i).data;
				int neighbouridx = dataIdxMap.get(neighbourdata);
				if (!visited[neighbouridx]) {
					helper(neighbouridx, visited);
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter The Number Of Nodes Required :-");
		int NumOfNodes = in.nextInt();
		Graph graph = new Graph();
		System.out.println("Enter The Data");
		for (int i = 0; i < NumOfNodes; i++) {
			int data = in.nextInt();
			graph.Create(new Node(data));
		}
		System.out.println("Enter The Nomber Of Edges Required :- ");
		int NumOfEdges = in.nextInt();
		System.out.println("Enter The Src And Dst in a line for each Node :-");
		for (int i = 0; i < NumOfEdges; i++) {
			int srcData = in.nextInt();
			int dstData = in.nextInt();
			
			int srcIdx = graph.dataIdxMap.get(srcData);
			int dstIdx = graph.dataIdxMap.get(dstData);
			graph.AddEdges(srcIdx, dstIdx);
		}

		graph.print();

		graph.DFS();
		in.close();

	}
}
