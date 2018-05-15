//Oracle官方例子中，生产者和消费者问题测试用例
public class ProducerConsumerExample {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();

	}

}
