import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

enum DIRECTION{
	up,down,left,right;
}
public class Snake extends JPanel {
	private static final int tilewidth=25;
	protected static final int col=20;
	protected static final int row=20;
	private DIRECTION myDirection;
	private DIRECTION mnextDirection;//下一动作指令
	private List<Body> mybody;
	private  boolean dead=false;
	private Body head;
	private Body headold;
	private Speed mSpeed;
	private Food food;
	public Snake() {// TODO Auto-generated constructor stub
		
		//initialize snake'body
		this.addKeyListener(new MyKeyListener());  
		mybody= new ArrayList<Body> ();
		reset();
		produceFood();
		mSpeed=new Speed( this);
		mSpeed.start();
		
	}
	public Food getFood(){
		return food;
	}
	public  void move() {//move operation
		
		myDirection=mnextDirection;
		
		Food f=getFood();
		headold=mybody.get(0);
		switch (myDirection) {
		case up:
			head=new Body(headold.row-1, headold.col);
			break;
		case down:
			head=new Body(headold.row+1, headold.col);
			break;
		case left:
			head = new Body(headold.row, headold.col-1);
			break;
		case right:
			head = new Body(headold.row, headold.col+1);
			break;
		default://in case compiler doesn't work,initialize the snake
			head=new Body(0, 0);
			break;
		}
		
			if (f.row==head.row&&f.col==head.col) {
				
				mybody.add(0,f);
				produceFood();
			}else {
				
				mybody.add(0, head);
				mybody.remove(mybody.size()-1);
			}
		if (checkBodyCollision()||checkBoundCollision()) {
			dead=true;
		}
		
	}
	public void setdirection(DIRECTION d) {//设定前一动作为上，下一动作不能为下
		this.mnextDirection=d;
		switch (d) {
		case up:
			if (myDirection==DIRECTION.down) {
			
				mnextDirection=myDirection;
				return;
			}
			break;
		case down:
			if (myDirection==DIRECTION.up) {
		
				mnextDirection=myDirection;
				return;
			}
			break;
		case left:
			if (myDirection==DIRECTION.right) {
				
				mnextDirection=myDirection;
				return;
			}
			break;
		case right:
			if (myDirection==DIRECTION.left) {
		
				mnextDirection=myDirection;
				return;
			}
			break;
		default:
			break;
		}
		
	}
	public Body getBody(int index){//获取body指针
		return mybody.get(index);
	}
	public void reset() {//reset the snake
		mybody.clear();
		mybody.add(new Body(7, 4));
		mybody.add(new Body(7, 3));
		mybody.add(new Body(7, 2));
		mybody.add(new Body(7, 1));
		mnextDirection=DIRECTION.right;
	}
	public Food produceFood() {//produce a new food
		boolean ate;
		Food food;
		do {
			ate=true;
			food=new Food();
			for (int i = 0; i < mybody.size(); i++) {
				if (this.getBody(i).col==food.col&&this.getBody(i).row==food.row) {
					ate=false;
				}
			}
		} while (ate==false);
		this.food=food;
		return food;
	}
	public void drawApple(Graphics g){//画苹果
		g.setColor(Color.red);
		
			g.fillOval((int)(food.col*tilewidth), food.row*tilewidth, tilewidth, tilewidth);
		
	 }
	public void drawSnake(Graphics g){//画蛇
		g.setColor(Color.blue);
		for (int i = 0; i < mybody.size(); i++) {
			Body body=getBody(i);
			g.fillRect(body.col*tilewidth, body.row*tilewidth, tilewidth, tilewidth);
		}
	}
	public void paint(Graphics g){//总画布
		super.paint(g);
		
		if (dead==false) {
			drawApple(g);
			drawSnake(g);
		}else {
			drawDeadMessage(g);
		}
		
	}
	public boolean checkBodyCollision(){//判断吃到自己身体死亡
		Body head=getBody(0);
		for (int i = 1; i < mybody.size(); i++) {
			if (head.row==getBody(i).row&&head.col==getBody(i).col) {
				
				dead=true;
			}
			
		}
		
		return dead;
		
	}
	public boolean checkBoundCollision(){//判断碰到边界死亡
		Body head=getBody(0);
		if (head.col>=col||head.col<0||head.row<0||head.row>=row-1) {
			dead=true;
		}
		
		return dead;
	}
	public void drawDeadMessage(Graphics g) {//绘制死亡信息
		g.drawString("YOU DEAD ,PRESS Q TO  RETRY", (6)*tilewidth, (row/2)*tilewidth);
	}

	//监听键盘类  
	class MyKeyListener extends KeyAdapter{

		public void keyPressed(KeyEvent e){
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				setdirection(DIRECTION.up);
				break;
			case KeyEvent.VK_DOWN:
				setdirection(DIRECTION.down);
				break;
			case KeyEvent.VK_LEFT:
				setdirection(DIRECTION.left);
				break;
			case KeyEvent.VK_RIGHT:
				setdirection(DIRECTION.right);
				break;
			
			default:
				break;
			}
			if (dead==true&&e.getKeyCode()==81) {
				(Snake.this).reset();
				dead=false;
				
			}
		}
	}
}
