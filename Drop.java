//Oracle官方教程---生产者和消费者问题共享资源类Drop
//Modify the producer-consumer example in Guarded Blocks
//to use a standard library class instead of the Drop class.
//参考: 生产者/消费者问题的多种Java实现方式
//https://blog.csdn.net/MONKEY_D_MENG/article/details/6251879
public class Drop {
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,

    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    //消费者消费
    public synchronized String take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return message;
    }

    //生产者生产
    public synchronized void  put(String message) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        //生产者者生产信息
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        //通知消费者线程可以过来取了
        notifyAll();
    }


}
