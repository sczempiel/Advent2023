package util.pathfinding;

import util.Tuple;

public class CoordingateGraph extends Graph<Tuple<Integer, Integer>, CoordingateNode> {

	public CoordingateNode getNode(int y, int x) {
		return this.getNode(Tuple.of(y, x));
	}

	public CoordingateNode addNode(Tuple<Integer, Integer> coord) {
		return this.addNode(coord.getLeft(), coord.getRight());
	}

	public CoordingateNode addNode(int y, int x) {
		CoordingateNode node = new CoordingateNode(y, x);

		this.addNode(node);

		this.addDestination(node, y - 1, x);
		this.addDestination(node, y, x + 1);
		this.addDestination(node, y + 1, x);
		this.addDestination(node, y, x - 1);

		return node;
	}

	private void addDestination(CoordingateNode node, int y, int x) {
		CoordingateNode other = this.getNode(y, x);

		if (other == null) {
			return;
		}

		node.addDestination(other, 1);
		other.addDestination(node, 1);
	}

	public Graph<Tuple<Integer, Integer>, CoordingateNode> calculateShortestPathFromSource(
			Tuple<Integer, Integer> coord) {
		return this.calculateShortestPathFromSource(this.getNode(coord.getLeft(), coord.getRight()));
	}

	public Graph<Tuple<Integer, Integer>, CoordingateNode> calculateShortestPathFromSource(int y, int x) {
		return this.calculateShortestPathFromSource(this.getNode(y, x));
	}
}
