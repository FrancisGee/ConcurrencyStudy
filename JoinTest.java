//���к��ȴ�Ů����ױ�Ĺ��������õ����join()��sleep()����
public class JoinTest {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ThreadBoy boy = new ThreadBoy();
		boy.start();

	}

	static class ThreadBoy extends Thread{
        @Override
        public void run() {

            System.out.println("�к���Ů��׼����ȥ���");
            System.out.println(Thread.currentThread());

            ThreadGirl girl = new ThreadGirl();
            girl.start();

            int time = 2000;

            try {
                girl.join(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("�к�����" + time + ", �����ٵ��ˣ�ȥ�����");
        }

        static class ThreadGirl extends Thread {
            @Override
            public void run() {
                int time = 5000;
                System.out.println(Thread.currentThread());

                System.out.println("Ů����ʼ��ױ,�к��ڵȴ�������");

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Ů����ױ��ɣ�����ʱ" + time);

            }
        }
	}

}
