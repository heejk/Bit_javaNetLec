package thread.ex06.thread_join;

class Sum implements Runnable {
	int num = 0;
	int start, end;
	
	Sum(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		for (int i = start; i <= end; i++)
			num += i;
	}
	
	public int getNum() {
		return this.num;
	}
}

public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		// ex. 알바생 고용중 
		Sum sum0 = new Sum(1, 50);
		Sum sum1 = new Sum(51, 100);
		Thread t0 = new Thread(sum0);
		Thread t1 = new Thread(sum1);

		// ex. 알바생 고용 시작 
		t0.start();
		t1.start();
		
		/*
		main thread와 t0/t1 thread는 별개로 동작하므로 
		t0/t1 thread를 동작시키자마자 
		main thread는 다음을 진행하고 프로세스 마무리
		>> 결과값: 0 또는 1275
		-- ex05.thread_need_join/ThreadNeedJoin.java 
		*/ 

		// 해결방안: t0/t1 thread의 동작이 마칠 때까지 대기
		
		
		// 1. sleep으로 대략 대기시간 주기 >> 예측할 수 있는 가장 긴 시간에 초점 >> 성능 저하
		/*
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// 2. thread 객체 내의 run()이 리턴하면 jvm은 신호를 받게 됨 >> 이 신호를 대기하는 join() 사용 >> main thread는 join()에서 신호가 올 때까지 대기 상태 
		t0.join();
		t1.join();
		
		System.out.println("1 ~ 100 = " + (sum0.getNum() + sum1.getNum()));
	}

}
