//Oracle�ٷ������У������ߺ������������������
public class ProducerConsumerExample {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();

	}

}
