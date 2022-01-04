package linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" + "data=" + data + ", next=" + next + '}';
    }
  }

  /**
   * Returns the size of the list.
   *
   * @return size
   */
  public int size() {
    return this.size;
  }

  /**
   * Check if the list is empty or not.
   *
   * @return size is 0 or not
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Adds a new element into the list.
   *
   * @param element element to add
   */
  public void add(T element) {
    addLast(element);
  }

  /**
   * To clear a list:
   *
   * <pre>
   *   1.Start traversal from head and traversal till last node
   *   2.Take back up of next pointer of every node before clean up.
   *   3.Clean up the data and next pointer.
   *   4.Update the traversal point to next pointer/backup.
   *   5.Clean up the head and tail, and set the size to 0.
   * </pre>
   */
  public void clear() {
    Node<T> trav = head;
    while (trav != null) {
      Node<T> next = trav.next;
      trav.data = null;
      trav.next = null;
      trav = next;
    }
    head = tail = null;
    size = 0;
  }

  /**
   * To find an element at particular index:
   *
   * <pre>
   *   1. Create a traversal point from head. Keep a track of visited node index using a variable.
   *   2. Check for element at every node, if match found, return it, or else set the traversal point to go to next node.
   *   3. Loop till last node i.e. node with next pointer pointing to null..
   * </pre>
   *
   * @param index index to look at
   * @return T element found
   */
  public T get(int index) {
    int visitedNodeIndex = 0;
    Node<T> trav = head;
    while (trav != null) {
      if (visitedNodeIndex == index) {
        return trav.data;
      }
      trav = trav.next;
      visitedNodeIndex++;
    }
    return null;
  }

  /**
   * To insert an element at the ending:
   *
   * <pre>
   *   1. Create a new tail node, and set its next pointer to null.
   *   2. Update the existing tail node's next pointer to point to newly created tail node.
   *   3. Update the new tail node as newly create tail node and the size of the list.
   * </pre>
   *
   * @param element element to add
   */
  public void addLast(T element) {
    if (isEmpty()) {
      addFirst(element);
    } else {
      tail.next = new Node<>(element, null); // Step 1 and 2
      tail = tail.next; // step 3
      size++;
    }
  }

  /**
   * To add an element at first position:
   *
   * <pre>
   *   1. IF the list is empty, create a new node and set it as head and tail node.
   *   2. Or else, create a new node, set its next pointer to point to the previous head.
   *   3. Increase the size of the list by one.
   * </pre>
   *
   * @param element element to add
   */
  public void addFirst(T element) {
    if (isEmpty()) {
      head = tail = new Node<>(element, null);
    } else {
      head = new Node<>(element, head);
    }
    size++;
  }

  /**
   * Returns an iterator to iterate the list.
   *
   * @return iterator
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav.next != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new ConcurrentModificationException();
      }
    };
  }

  /**
   * Prints to formatted string representation of the list.
   *
   * @return formatted string
   */
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
