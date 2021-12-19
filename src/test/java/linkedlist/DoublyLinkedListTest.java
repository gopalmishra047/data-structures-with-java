package linkedlist;

import java.util.Iterator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

  DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

  @BeforeEach
  void setUp() {
    list.add(2);
    list.add(7);
    list.add(3);
    list.add(9);
    list.add(4);
  }

  @AfterEach
  void tearDown() {
    list.clear();
  }

  @Test
  void size() {
    assertThat(list.size()).isEqualTo(5);
  }

  @Test
  void clear() {
    list.clear();
    assertThat(list.size()).isZero();
  }

  @Test
  void add() {
    assertThat(list.size()).isEqualTo(5);
    list.add(10);
    assertThat(list.get(3)).isEqualTo(9);
    assertThat(list.size()).isEqualTo(6);
  }

  @Test
  void addFirst() {}

  @Test
  void addLast() {}

  @Test
  void peekFirst() {}

  @Test
  void peekLast() {}

  @Test
  void removeFirst() {}

  @Test
  void removeLast() {}

  @Test
  void isEmpty() {}

  @Test
  void iterator() {
    Iterator<Integer> iterator = list.iterator();
    for (int i = 0; i < list.size(); i++) {
      assertThat(iterator.hasNext()).isTrue();
      iterator.next();
    }
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(iterator::remove);
  }
}