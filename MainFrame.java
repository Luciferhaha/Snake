

import javax.swing.*;


public class MainFrame  extends JFrame{
	private Snake snake;
	private static final int width=500;
	private static final int height=500;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		snake=new Snake();
		snake.setFocusable(true);
		this.add(snake);
		this.setTitle("RETRO SNAKER");
		this.setSize(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		new MainFrame();
	}

}
