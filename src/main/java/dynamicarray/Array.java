package dynamicarray;

import java.util.Iterator;

@SuppressWarnings("unchecked")
class Array<T> implements Iterable<T> {

  private T[] array;
  private int length;
  private int capacity;

  public Array(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity should be greater than 0");
    }
    this.capacity = capacity;
    array = (T[]) new Object[capacity];
  }

  public void set(int index, T element) {
    array[index] = element;
  }

  public T get(int index) {
    return array[index];
  }

  public void add(T element) {
    if (length + 1 >= capacity) { // Check if new length would exceed current capacity
      if (capacity == 0) {
        capacity = 1;
      } else {
        capacity = capacity * 2; // double the size
      }
      T[] newArray = (T[]) new Object[capacity]; // create a new array with new size
      for (int i = 0; i < length; i++) {
        newArray[i] = array[i]; // copy the old array into new one
      }
      array = newArray; // set the new array as the current array
    }
    array[length++] = element; // add the new element into array
  }

  public boolean remove(T element) {
    int index = indexOf(element);
    removeAt(index);
    return true;
  }

  public boolean contains(T element) {
    return indexOf(element) == 1;
  }

  public int indexOf(T element) {
    for (int i = 0; i < length; i++) {
      if (element == null) {
        if (array[i] == null) {
          return i;
        }
      } else {
        if (element.equals(array[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  public T removeAt(int index) {
    if (index < 0 || index >= length) {
      throw new ArrayIndexOutOfBoundsException("Given index is oud of bound.");
    }
    T element = array[index];
    T[] newArray = (T[]) new Object[length - 1];
    for (int i = 0, j = 0; i < length; i++, j++) {
      if (i == index) {
        j--; // skip over index by fixing j temporarily
      } else {
        newArray[j] = array[i];
      }
    }
    array = newArray;
    capacity = --length;
    return element;
  }

  public void clear() {
    for (int i = 0; i < length; i++) {
      array[i] = null;
    }
    length = 0;
  }

  public int size() {
    return length;
  }

  public boolean isEmpty() {
    return length == 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new java.util.Iterator<>() {
      int index = 0;

      @Override
      public boolean hasNext() {
        return index < length;
      }

      @Override
      public T next() {
        return array[index++];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    for (int i = 0; i < length - 1; i++) {
      builder.append(array[i]).append(",");
    }
    return builder.append(array[length - 1]).append("]").toString();
  }
}
