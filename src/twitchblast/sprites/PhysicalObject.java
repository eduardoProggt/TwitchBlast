package twitchblast.sprites;

public interface PhysicalObject {
	/**Gibt eine damage zur�ck, die die Kollision anrichtet*/
	public float collide(int collisionDirection);

	public void update();
	public float getX();
	public float getY();
}
