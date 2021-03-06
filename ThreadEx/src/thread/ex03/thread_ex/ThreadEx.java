package thread.ex03.thread_ex;

class Study extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("study...");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class Music extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("music...");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class ThreadEx {

	public static void main(String[] args) {
		Study study = new Study();
		Music music = new Music();
		
		study.start(); // study thread가 run() 동작 시킴 
		music.start(); // music thread가 run() 동작 시킴
	}

}
