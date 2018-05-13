//饥饿状态的例子
//参考: http://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java
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

	            //解决饥饿状态的方式
//	            try {
//					wait(1000);
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
	        }
	    }
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//共享资源
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


