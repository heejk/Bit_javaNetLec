package thread.ex08.sync_thread;

class Anum {
	static int num = 0;
	
	static synchronized void accumulate (int val) {
		num += val;
	}
	
	/* 
	이 메서드 내에 여러 thread가 동시 접근 가능 
	ㄴ 해결방안: synchronized 키워드를 주면 동시 접근 불가
	그러므로 num += val 연산이 끝나서 메서드가 리턴되기 전에는 다른 스레드로 제어권이 넘어가지 않음
	즉, 컨텍스트 스위칭이 일어나지 않음 >> 메서드의 한 번에 1개 thread의 연산 보장 
	*/
}

class Sum implements Runnable {

	int start, end;
	
	Sum(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		for (int i = start; i <= end; i++) 
			Anum.accumulate(i);
	}
}

public class SyncThread {

	public static void main(String[] args) throws InterruptedException {
		Sum sum0 = new Sum(1, 50);
		Sum sum1 = new Sum(51, 100);
		Thread t0 = new Thread(sum0);
		Thread t1 = new Thread(sum1);
		
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
		
		System.out.println("1 ~ 100 accum = " + Anum.num);
	}

}
