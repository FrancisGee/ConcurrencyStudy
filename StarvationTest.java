//����״̬������
//�ο�: http://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java
import java.io.*;
public class StarvationTest {

	static class Worker {
	    public synchronized void work() {
	        String name = Thread.currentThread().getName();
	        String fileName = name + ".txt";

	        try (
	            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	        ) {
	            writer.write("Thread " + name + " wrote this mesasge");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	        while (true) {
	            System.out.println(name + " is working");

	            //�������״̬�ķ�ʽ
//	            try {
//					wait(1000);
//				} catch (InterruptedException e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}
	        }
	    }
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//������Դ
        Worker worker = new Worker();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    worker.work();
                }
            }).start();
        }
    }

	}


