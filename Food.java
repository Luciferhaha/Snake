
import java.util.Random;
//继承body类坐标，随机产生苹果
public class Food extends Body{

	public Food() {
		super(0,0);
		// TODO Auto-generated constructor stub
	Random rnd=new Random();
	this.row=rnd.nextInt(Snake.row-1);
	this.col=rnd.nextInt(Snake.col-1);
	}
	
}
