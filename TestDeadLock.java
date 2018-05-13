//���׶�����������
//synchronized�ؼ������δ���飬ָ���������󣬶Ը����������
//����ͬ�������ǰҪ��ø����������



//�ο�֪���ϵĽ���: https://zhuanlan.zhihu.com/p/26945588
//�����ĳ��֣��������������ĸ�����:
//1.����������ָ���̶������䵽����Դ����������ʹ�ã�����һ��ʱ����ĳ��Դֻ��һ������ռ�á�
//�����ʱ������������������Դ����������ֻ�ܵȴ���ֱ��ռ����Դ�Ľ����ñ��ͷš�(ֻ��һ��Կ��)
//2.����ͱ���������ָ�����Ѿ���������һ����Դ������������µ���Դ���󣬶�����Դ�ѱ���������ռ�У�
//��ʱ����������������ֶ��Լ��ѻ�õ�������Դ���ֲ��š�(���ź�Կ�׵�����û�й黹��Կ�׵�����£������Ҫ��Կ��)
//3.������������ ָ�����ѻ�õ���Դ����δʹ����֮ǰ�����ܱ����ᣬֻ����ʹ����ʱ���Լ��ͷš�
// (�˳��ǹ黹��Կ�ף���Ȼһֱռ����Կ��)
//4.��·�ȴ�������ָ�ڷ�������ʱ����Ȼ����һ�����̡�����Դ�Ļ������������̼���{P0��P1��P2����������Pn}�е�P0���ڵȴ�һ��P1ռ�õ���Դ��
//P1���ڵȴ�P2ռ�õ���Դ��������Pn���ڵȴ��ѱ�P0ռ�õ���Դ��
//(���ź�Կ�׵����ڵ���Կ�ף�ͬʱ�Ǹ�������Կ�׵����ڵȺ�Կ��)

//Ҫ����������������⣬ֻ��Ҫ�ƻ��ĸ������е��κ�һ���Ϳ����ˡ�
//1.�ƻ���������: ��ÿ���̵߳�������һ��Կ�׵ĸ���
//2.�ƻ�����ͱ�������: һ���̻߳�ȡһ��Կ��֮�󣬱���黹��Կ��֮�����������һ��Կ��.
//3.�ƻ�����������: ����һ���ռ��ʱ��,�������10������Ȼû�н�����һ�����裬��黹���е�Կ�ס�
//4.�ƻ���·�ȴ�����: ǿ�ƹ涨��ȡ��Կ�ף���ȡ��Կ��
public class TestDeadLock {
	   public static Object Lock1 = new Object();
	   public static Object Lock2 = new Object();

	   public static void main(String args[]) {
	      ThreadDemo1 T1 = new ThreadDemo1();
	      ThreadDemo2 T2 = new ThreadDemo2();
	      T1.start();
	      T2.start();
	   }

	   private static class ThreadDemo1 extends Thread {
	      public void run() {
	         synchronized (Lock1) {
	            System.out.println("Thread 1: Holding lock 1...");
//
	            try { Thread.sleep(5); }
	            catch (InterruptedException e) {}
	            System.out.println("Thread 1: Waiting for lock 2...");
//	            try {
//					Lock1.wait(20);
//				} catch (InterruptedException e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}

	            synchronized (Lock2) {
	               System.out.println("Thread 1: Holding lock 1 & 2...");
	            }
	         }
	      }
	   }
	   private static class ThreadDemo2 extends Thread {
	      public void run() {
	         synchronized (Lock2) {
	            System.out.println("Thread 2: Holding lock 2...");

	            try { Thread.sleep(20); }
	            catch (InterruptedException e) {}
	            System.out.println("Thread 2: Waiting for lock 1...");
//	            try {
//					Lock2.wait(20);
//				} catch (InterruptedException e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}

	            synchronized (Lock1) {
	               System.out.println("Thread 2: Holding lock 1 & 2...");
	            }
	         }
	      }
	   }
}
