package designpatterns.strategy.camera;

public class Text implements ShareStategy {
	public void share()
	{
		System.out.println("I'm texting the photo");
	}
}