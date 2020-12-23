public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (size != 0) {
            if (index == 0) {
                addToFront(data);
            } else if (index == size) {
                addToBack(data);
            } else {
                LinkedListNode<T> atIndex = head;
                for (int a = 0; a < index; a++) {
                    atIndex = atIndex.getNext();
                }
                LinkedListNode<T> newLinkedNode =
                        new LinkedListNode<T>(data,
                                atIndex.getPrevious(), atIndex);
                atIndex.getPrevious().setNext(newLinkedNode);
                atIndex.setPrevious(newLinkedNode);
                size++;
            }
        } else {
            LinkedListNode<T> a = new LinkedListNode<T>(data, null, null);
            head = a;
            tail = a;
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (size != 0) {
            LinkedListNode<T> a = new LinkedListNode<T>(data, null, head);
            head.setPrevious(a);
            head = a;
        } else {
            LinkedListNode<T> a = new LinkedListNode<T>(data, null, null);
            head = a;
            tail = a;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (size != 0) {
            LinkedListNode<T> newLinkedNode =
                    new LinkedListNode<T>(data, tail, null);
            tail.setNext(newLinkedNode);
            tail = newLinkedNode;
        } else {
            LinkedListNode<T> newLinkedNode =
                    new LinkedListNode<T>(data, null, null);
            tail = newLinkedNode;
            head = newLinkedNode;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        T object = null;
        if (index < size - 1 && index > 0) {
            LinkedListNode<T> atIndex = head;
            for (int a = 0; a < index; a++) {
                atIndex = atIndex.getNext();
            }
            atIndex.getPrevious().setNext(atIndex.getNext());
            atIndex.getNext().setPrevious(atIndex.getPrevious());
            object = atIndex.getData();
            size--;
        } else if (index == 0) {
            object = removeFromFront();
        } else if (index == size - 1) {
            object = removeFromBack();
        }
        if (size == 1) {
            tail = head;
        }
        return object;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        T object;
        if (size > 1) {
            object = head.getData();
            head.getNext().setPrevious(null);
            head = head.getNext();
        } else {
            object = head.getData();
            head = null;
            tail = null;
        }
        size--;
        if (size == 1) {
            tail = head;
        }
        return object;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        T object;
        if (size > 1) {
            object = tail.getData();
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
        } else {
            object = head.getData();
            head = null;
            tail = null;
        }
        size--;
        if (size == 1) {
            tail = head;
        }
        return object;
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        boolean result = false;
        LinkedListNode<T> object = tail;
        for (int a = size - 1; a >= 0; a--) {
            if (object.getData().equals(data)) {
                removeAtIndex(a);
                result = true;
            }
            object = object.getPrevious();
        }
        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        }
        LinkedListNode<T> object = head;
        for (int a = 0; a < index; a++) {
            object = object.getNext();
        }
        return object.getData();
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        LinkedListNode<T> object = head;
        for (int a = 0; a < size; a++) {
            array[a] = object.getData();
            object = object.getNext();
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}