
public class Main {
    public static void main(String[] args) {
        CircularQueue q = new CircularQueue();
        q.remove();
        q.add("Hello World");
        q.add(10.0);
        q.add(50.50);
        q.displayQueue();
        System.out.println("Removing: " + q.remove() );
        System.out.println("Removing:" + q.remove() );
        System.out.println("is queue empty: " + q.isEmpty());
        q.displayQueue();

        int i = 0;
        while (i < 10) {
            q.add(i);
            i++;
        }
        q.displayQueue();
    }
}
