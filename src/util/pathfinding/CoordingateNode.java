package util.pathfinding;

import util.Tuple;

public class CoordingateNode extends Node<Tuple<Integer, Integer>, CoordingateNode> {

	public CoordingateNode(Tuple<Integer, Integer> coordinate) {
		super(coordinate);
	}

	public CoordingateNode(int y, int x) {
		super(Tuple.of(y, x));
	}

}
