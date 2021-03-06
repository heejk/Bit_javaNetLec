package thread.ex07.not_sync_thread;

class Sum implements Runnable {

	static int num = 0;
	int start, end;
	
	Sum(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	void accumulate(int val) {
		num += val;
	}
	
	@Override
	public void run() {
		for (int i = start; i <= end; i++) 
			accumulate(i);
	}
	
	/*
	run()은 thread 2개가 각각 별개로 호출하는 메서드
	but, accumulate() 내부에서는 num이라는 static 변수를 thread가 동시 접근 가능 >> 공유되는 변수는 값이 왜곡된 위험 상존
	그러므로 공유되는 변수 값의 왜곡을 막을 동기화 기법 필요 
	*/
}

public class NotSyncThread {

	public static void main(String[] args) throws InterruptedException {
		Sum sum0 = new Sum(1, 50);
		Sum sum1 = new Sum(51, 100);
		Thread t0 = new Thread(sum0);
		Thread t1 = new Thread(sum1);
		
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
		
		System.out.println("1 ~ 100 accum = " + Sum.num);
	}

}
