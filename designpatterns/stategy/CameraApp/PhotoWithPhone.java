package designpatterns.strategy.camera;

public abstract class PhoneCameraApp {
	ShareStrategy sharestrategy;
	
	public void SetShareStrategy(ShareStrategy sharestrategy)
	{
		this.sharestrategy = sharestrategy;
	}
	
	public void share();
}