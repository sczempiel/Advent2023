package util.pathfinding;

public class Polar {

	private final int x;
	private final int y;
	private final double radius;
	// in radian
	private final double angle;

	public Polar(double radius, double angle) {
		this.radius = radius;
		this.angle = angle;

		this.y = (int) (Math.sin(angle) * radius);
		this.x = (int) (Math.cos(angle) * radius);
	}

	public Polar(int y, int x, double radius, double angle) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
	}

	public Polar(int y, int x) {
		this.x = x;
		this.y = y;

		this.radius = Math.sqrt(y * y + x * x);
		this.angle = Math.toDegrees(Math.atan2(y, x));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getRadius() {
		return radius;
	}

	public double getAngle() {
		return angle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Polar [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", radius=");
		builder.append(radius);
		builder.append(", angle=");
		builder.append(angle);
		builder.append("]");
		return builder.toString();
	}

}
