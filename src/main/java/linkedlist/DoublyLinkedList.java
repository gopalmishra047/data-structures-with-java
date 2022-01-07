package linkedlist;

import java.util.Iterator;

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

  public T get(int position) {
    int searchPosition = 0;
    Node<T> trav = head;
    while (trav != null) {
      if (position == searchPosition) {
        return trav.data;
      }
      trav = trav.next;
      searchPosition++;
    }
    return null;
  }

  public void addFirst(T element) {
    // 1. create a new node and initialise both previous and next pointers as null
    Node<T> newNode = new Node<>(element, null, null);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      // 2. Update the next/right pointer of the new node to point to the current head node.
      newNode.next = head;
      // 3. Update the current head left/prev pointer to point to the new node;
      head.prev = newNode;
      // 4. Make the new node as updated head.
      head = newNode;
    }
    // 5. Increase the size of the list by 1.
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

  public void insertAt(T element, int position) {
    if (position < 0 || position > this.size) {
      throw new RuntimeException("List contains only " + this.size + " elements !");
    }
    if (position == 0) {
      addFirst(element);
    } else if (position == this.size) {
      addLast(element);
    } else {
      Node<T> temp = head;
      for (int i = 1; i < position; i++) {
        temp = temp.next;
      }
      Node<T> newNode = new Node<>(element, temp, temp.next);
      newNode.next.prev = newNode;
      temp.next = newNode;
    }
    size++;
  }

  public void deleteAt(int position) {
    if (position < 1 || position > this.size) {
      throw new RuntimeException("List contains only " + this.size + " elements !");
    }
    Node<T> nodeAtPosition = getNodeAt(position);
    nodeAtPosition.prev.next = nodeAtPosition.next;
    nodeAtPosition.next.prev = nodeAtPosition.prev;
    size--;
  }

  public Node<T> getNodeAt(int position) {
    int visitedNode = 0;
    Node<T> trav = head;
    while (trav != null) {
      if (visitedNode == position) {
        return trav;
      }
      trav = trav.next;
      visitedNode++;
    }
    return null;
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
    T toRemove = head.data; // data to be removed;
    head = head.next; // make the element next to current head as new head
    head.prev = null; // set the element previous to head as null since it's head
    size--;
    return toRemove;
  }

  public T removeLast() {
    if (isEmpty()) {
      throw new RuntimeException("Empty list !");
    }
    T toRemove = tail.data;
    tail = tail.prev;
    tail.next = null;
    size--;
    return toRemove;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public boolean contains(T element) {
    for (T next : this) {
      if (element == next) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
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
