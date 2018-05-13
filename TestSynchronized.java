//���������Synchronized
//1.����ʵ�������������ڵ�ǰʵ������������ͬ������ǰҪ��õ�ǰʵ������
//2.���ξ�̬�����������ڵ�ǰ��������������ͬ������ǰҪ��õ�ǰ��������
//3.���δ���飬ָ���������󣬶Ը����������������ͬ�������ǰҪ��ø������������

//�ο�: https://blog.csdn.net/javazejian/article/details/72828483
public class TestSynchronized implements Runnable {

	//Synchronized�ؼ������δ���飬��ָ���������
	static TestSynchronized instance = new TestSynchronized();

    //������Դ(�ٽ���Դ)
    static int i=0;

    /**
     * synchronized ����ʵ�������� ��ʵ������
     */
//    public synchronized void increase(){
//        i++;
//    }


    /**
     * synchronized ���ξ�̬����,����������
     */
    public static synchronized void increase(){
        i++;
    }



//	@Override
//	public void run() {
//		// TODO �Զ����ɵķ������
//        for(int j=0;j<1000000;j++){
//            increase();
//        }
//
//	}

    ////Synchronized�ؼ������δ���飬��ָ���������
	@Override
	public void run() {
		//ʡ��������ʱ����...
		//ʹ��ͬ�������Ա���i����ͬ������,������Ϊinstance
		// TODO �Զ����ɵķ������
		synchronized (instance) {
	        for(int j=0;j<1000000;j++){
	            increase();
	        }
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO �Զ����ɵķ������
		//��ʵ��instance�������, Synchronized�ؼ�������ʵ������
//		TestSynchronized instance = new TestSynchronized();
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		//main�̵߳ȴ�t1���
		t1.join();
		//main�̵߳ȴ�t2���
		t2.join();

		System.out.println(i);

	}


}
