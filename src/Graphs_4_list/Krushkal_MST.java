package Graphs_4_list;

import java.util.*;

public class Krushkal_MST {
	static class Node {
		int data;

		Node(int data) {
			this.data = data;
		}
	}

	static class Edge {
		int src;
		int dst;
		int w;

		Edge(int src, int dst, int w) {
			this.src = src;
			this.dst = dst;
			this.w = w;
		}
	}

	static class Graph {
		List<LinkedList<Node>> alist;
		Map<Integer, Integer> dataToIdx;
		Map<String, Integer> weights;

		Graph() {
			alist = new ArrayList<>();
			dataToIdx = new HashMap<>();
			weights = new HashMap<>();
		}

		void Create(Node node) {
			LinkedList<Node> list = new LinkedList<>();
			list.add(node);
			alist.add(list);
			dataToIdx.put(node.data, alist.size() - 1);
		}

		void AddEdges(int srcIdx, int dstIdx, int w) {
			LinkedList<Node> temp_1 = alist.get(srcIdx);
			Node dstNode = alist.get(dstIdx).get(0);
			temp_1.add(dstNode);
			weights.put(srcIdx + "-" + dstIdx, w);

			LinkedList<Node> temp_2 = alist.get(dstIdx);
			Node srcNode = alist.get(srcIdx).get(0);
			temp_2.add(srcNode);
			weights.put(dstIdx + "-" + srcIdx, w);
		}

		public void print() {
			System.out.println("The Adjecenct List :-");
			for (int i = 0; i < alist.size(); i++) {
				System.out.print(alist.get(i).get(0).data + "->");
				for (int j = 1; j < alist.get(i).size(); j++) {
					System.out.print(alist.get(i).get(j).data + " ");
				}
				System.out.println();
			}
		}

		public void Krushkal_MST() {
			List<Edge> edges = new ArrayList<>();
			// fill Edges
			for (int uIdx = 0; uIdx < alist.size(); uIdx++) {
				for (int j = 1; j < alist.get(uIdx).size(); j++) {
					int vVal = alist.get(uIdx).get(j).data;
					int vIdx = dataToIdx.get(vVal);
					if (uIdx < vIdx) {
						String key = uIdx + "-" + vIdx;
						int w = weights.getOrDefault(key, Integer.MAX_VALUE);
						edges.add(new Edge(uIdx, vIdx, w));
					}
				}
			}
			Collections.sort(edges, Comparator.comparingInt(e -> e.w));
			DisJoinSet ds = new DisJoinSet(alist.size());
			int Mst_Weight = 0;
			for (Edge e : edges) {
				int uIdx = e.src;
				int vIdx = e.dst;
				if (ds.findUltimateParent(uIdx) != ds.findUltimateParent(vIdx)) {
					Mst_Weight += e.w;
					ds.Union(uIdx, vIdx);
				}
			}
			System.out.println("The weight Of Minmum Spanning Tree is :- " + Mst_Weight);
		}
	}

	static class DisJoinSet {
		int[] parent;
		int[] rank;

		DisJoinSet(int n) {
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		int findUltimateParent(int node) {
			if (node == parent[node])
				return node;
			return parent[node] = findUltimateParent(parent[node]);
		}
		
		void Union(int uIdx,int vIdx) {
			int uUp = findUltimateParent(uIdx);
			int vUp = findUltimateParent(vIdx);
			if(uUp == vUp) return;
			if(rank[uUp] < rank[vUp]) {
				parent[uUp] = vUp;
				
			}else if(rank[vUp] < rank[uUp]) {
				parent[vUp] = uUp;
				
			}else {
				parent[vUp] = uUp;
				rank[uUp]++;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph graph = new Graph();
		System.out.println("The Number Of Nodes Required :-");
		int numOfNodes = in.nextInt();
		System.out.println("Enter The Node's data : ");
		for (int i = 0; i < numOfNodes; i++) {
			int data = in.nextInt();
			graph.Create(new Node(data));
		}
		System.out.println("Enter The Number Of Edges :-");
		int numOfEdges = in.nextInt();
		System.out.println("Enter The Edges,Src,weigths");
		for (int i = 0; i < numOfEdges; i++) {
			int uVal = in.nextInt();
			int vVal = in.nextInt();
			int w = in.nextInt();
			int srcIdx = graph.dataToIdx.get(uVal);
			int dstIdx = graph.dataToIdx.get(vVal);
			graph.AddEdges(srcIdx, dstIdx, w);
		}
		graph.print();
		graph.Krushkal_MST();
		in.close();
	}

}
