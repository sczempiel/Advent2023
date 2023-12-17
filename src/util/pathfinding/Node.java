package util.pathfinding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Node<C, T extends Node<C, T>> {

	private final C coordinate;

	private List<T> shortestPath = new LinkedList<>();

	private int distance = Integer.MAX_VALUE;

	Map<T, Integer> adjacentNodes = new HashMap<>();

	public void addDestination(T destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public Node(C coordinate) {
		this.coordinate = coordinate;
	}

	public C getCoordinate() {
		return coordinate;
	}

	public List<T> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<T> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Map<T, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<T, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		T other = (T) obj;
		if (coordinate == null) {
			if (other.getCoordinate() != null)
				return false;
		} else if (!coordinate.equals(other.getCoordinate()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [");
		if (coordinate != null) {
			builder.append("coordinate=");
			builder.append(coordinate);
			builder.append(", ");
		}
		if (shortestPath != null) {
			builder.append("shortestPath={");

			for (Iterator<T> it = shortestPath.iterator(); it.hasNext();) {
				T node = it.next();

				builder.append(node.getCoordinate());

				if (it.hasNext()) {
					builder.append(", ");
				}
			}

			builder.append("}, ");
		}
		if (adjacentNodes != null) {
			builder.append("adjacentNodes={");

			for (Iterator<Entry<T, Integer>> it = adjacentNodes.entrySet().iterator(); it.hasNext();) {
				Entry<T, Integer> node = it.next();

				builder.append("[");
				builder.append(node.getKey().getCoordinate());
				builder.append(", ");
				builder.append(node.getKey().getDistance());
				builder.append("]");

				if (it.hasNext()) {
					builder.append(", ");
				}
			}

			builder.append("}, ");
		}
		builder.append("distance=");
		builder.append(distance);
		builder.append(", ");
		builder.append("]");
		return builder.toString();
	}
}