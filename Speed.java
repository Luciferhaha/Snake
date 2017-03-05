
public class Speed extends Thread{
	 //控制速度和循环闪烁
	private Snake snake;
	public Speed( Snake s) {
		// TODO Auto-generated constructor stub
		
		snake=s;
	}
	
		public void run() {
			while(true){
				snake.move();
				snake.repaint();
				try {
					
					sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.toString());
				}
			}
		}
	
}
