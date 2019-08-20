
public abstract class PhotoWithPhone {
	ShareStrategy sharestrategy;
	
	public void SetShareStrategy(ShareStrategy sharestrategy)
	{
		this.sharestrategy = sharestrategy;
	}
	
	public void share(){};
}