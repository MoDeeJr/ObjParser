package pkg_main;

/**
 * 
 * basic representation of a math 3D Vector
 *
 */
public class Vec3 {

	public float x = 0.0f;
	public float y = 0.0f;
	public float z = 0.0f;
	
	Vec3() {
		// TODO Auto-generated constructor stub
	}
	
	void swapXAndY()
	{
		float temp_x = this.x;
		this.x = this.y;
		this.y = temp_x;
	}
	
	Vec3 setX(float in_x)
	{
		this.x = in_x;
		return this;
	}
	
	Vec3 setY(float in_y)
	{
		this.y = in_y;
		return this;
	}
	
	Vec3 setZ(float in_z)
	{
		this.z = in_z;
		return this;
	}
	
    public String toString() {
        return "Vec3(" + this.x + "," + this.y + "," + this.z + ")";
    }
	
}
