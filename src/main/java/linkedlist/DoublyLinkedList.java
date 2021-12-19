package linkedlist;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DoublyLinkedList<T> implements Iterable<T> {
  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  private static class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" + "data=" + data + '}';
    }
  }

  public int size() {
    return size;
  }

  public void clear() {
    Node<T> trav = head;
    while (trav != null) {
      Node<T> next = trav.next;
      trav.data = null;
      trav.prev = null;
      trav.next = null;
      trav = next;
    }
    head = tail = null;
    size = 0;
  }

  public void add(T element) {
    addLast(element);
  }

  public T get(int index) {
    int searchIndex = 0;
    Node<T> trav = head;
    while (trav != null) {
      if (index == searchIndex) {
        return trav.data;
      }
      trav = trav.next;
      searchIndex++;
    }
    return null;
  }

  public void addFirst(T element) {
    if (isEmpty()) {
      head = tail = new Node<>(element, null, null);
    } else {
      head.prev = new Node<>(element, null, head);
      head = head.prev;
    }
    size++;
  }

  public void addLast(T element) {
    if (isEmpty()) {
      head = tail = new Node<>(element, null, null);
    } else {
      tail.next = new Node<>(element, tail, null);
      tail = tail.next;
    }
    size++;
  }

  public T peekFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Empty list !");
    }
    return head.data;
  }

  public T peekLast() {
    if (isEmpty()) {
      throw new RuntimeException("Empty list !");
    }
    return tail.data;
  }

  public T removeFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Empty list !");
    }
    T data = head.data; // data to be removed;
    head = head.next; // make the element next to current head as new head
    head.prev = null; // set the element previous to head as null since it's head
    if (isEmpty()) {
      tail = null;
    }
    --size;
    return data;
  }

  public T removeLast() {
    if (isEmpty()) {
      throw new RuntimeException("Empty list !");
    }
    T data = tail.data;
    ;
    tail = tail.prev;
    tail.next = null;
    return data;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    Node<T> trav = head;
    while (trav != null) {
      builder.append(trav.data);
      if (trav.next != null) {
        builder.append(",");
      }
      trav = trav.next;
    }

    return builder.append("]").toString();
  }
}
