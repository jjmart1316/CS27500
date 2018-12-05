/******************************************************
 * 1. The number if items in the queue is is stored in the instance variable manyItems
 * 2. The items in the queue are stored in a circular linked-list, with the rear of the queue at the final node
 * 3. For a non-empty queue, the instance variable rear points the the final node and links to the front of the the
 *    collection. For an an empty queue the rear is the null reference.
 ******************************************************/
public class CircularQueue<E> {
    private Node<E> rear;
    private int manyItems;

    /**
     * Initialize an empty queue and instance manyItems to 0
     * @postcondition The queue is empty
     */
    public CircularQueue() {
        rear = null;
        manyItems = 0;
    }

    /**
     *puts a new item in the queue and increases manyItems.
     * @param data Data to be put in the queue
     * @postcondition Data has been pushed in the queue and the manyItems variable is increased by one
     */
    public void add(E data) {
        Node newNode = new Node<E>(data, null);

        //If queue is empty, rear points to the newNode and the newNode points to rear creating a circular link
        if ( isEmpty() ) {
            rear = newNode;
            newNode.nextLink = rear;
            //If queue is not empty, the newNode becomes rear of the circular queue
        } else {
            //frontNode is a temporary place holder for the front of queue
            Node frontNode = rear.nextLink;
            rear.nextLink = newNode;
            rear = rear.nextLink;
            rear.nextLink = frontNode;
        }
        manyItems++;
    }

    /**
     * removes the front item from the queue, decreases manyItems by 1, and returns the value of the removed item
     * @return value Value of the item removed
     * @postcondition front data has been removed from the queue
     */
    public E remove() {
        //if queue is empty a message is printout and the program is halted
        if ( isEmpty() ) {
            System.out.println("queue is empty");
            return null;
        }
        //frontNode is a temporary place holder for the front of the list
        Node frontNode = rear.nextLink;
        E value = (E)frontNode.dataInfo;

        //if rear of the queue and front are the same (1 item), the item is removed and the rear becomes empty (null)
        if ( rear == frontNode ) {
            rear = null;
            manyItems--;
            return value;
            //if queue size > 1 the front node is removed
        } else {
            rear.nextLink = frontNode.nextLink;
            manyItems--;
            return value;
        }
    }

    /**
     *
     * @return size the number of items in the queue
     */
    public int size() {
        return manyItems;
    }

    /**
     *
     * @return boolean returns true if queue is empty or false if queue has items
     */
    public boolean isEmpty() {
        return ( size() == 0 );
    }

    /**
     * @displays the items in the queue or prints out an empty statement if queue is empty
     */
    public void displayQueue() {
        if ( isEmpty() ) {
            System.out.println("There are no elements in the queue");
            return;
        }

        //temporary node to transverse the loop
        Node front = rear.nextLink;
        System.out.print("Elements in circular queue are: ");
        while (front != rear) {
            System.out.print(front.dataInfo + ", ");
            front = front.nextLink;
        }
        System.out.println(front.dataInfo);
    }

    /**
     * Inner class representation of the node
     * @param <T>
     */
    class Node<T> {
        private T dataInfo;
        private Node nextLink;

        public Node(T dataInfo,Node nextLink) {
            this.dataInfo = dataInfo;
            this.nextLink = nextLink;
        }
    }
}
