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
    assertThat(list.get(5)).isEqualTo(10);
    assertThat(list.size()).isEqualTo(6);
  }

  @Test
  void addFirst() {
    list.addFirst(5);
    assertThat(list.get(0)).isEqualTo(5);
    assertThat(list.size()).isEqualTo(6);
  }

  @Test
  void addLast() {
    list.addLast(20);
    assertThat(list.peekLast()).isEqualTo(20);
  }

  @Test
  void insertAt() {
    list.insertAt(14, 4);
    assertThat(list.get(4)).isEqualTo(14);
    assertThat(list.size()).isEqualTo(6);
  }

  @Test
  void deleteAt() {
    list.deleteAt(3);
    assertThat(list.get(3)).isEqualTo(4);
    assertThat(list.size()).isEqualTo(4);
  }

  @Test
  void peekFirst() {
    assertThat(list.peekFirst()).isEqualTo(2);
    list.clear();
    assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(list::peekFirst)
        .withMessage("Empty list !");
  }

  @Test
  void peekLast() {
    assertThat(list.peekLast()).isEqualTo(4);
    list.clear();
    assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(list::peekLast)
        .withMessage("Empty list !");
  }

  @Test
  void removeFirst() {
    assertThat(list.removeFirst()).isEqualTo(2);
    assertThat(list.size()).isEqualTo(4);
    list.clear();
    assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(list::removeFirst)
        .withMessage("Empty list !");
  }

  @Test
  void removeLast() {
    assertThat(list.removeLast()).isEqualTo(4);
    assertThat(list.size()).isEqualTo(4);
  }

  @Test
  void isEmpty() {
    assertThat(list.isEmpty()).isFalse();
    list.clear();
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  void contains() {
    assertThat(list.contains(3)).isTrue();
    assertThat(list.contains(50)).isFalse();
  }

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
