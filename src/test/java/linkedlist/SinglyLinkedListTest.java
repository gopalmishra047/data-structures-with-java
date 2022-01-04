package linkedlist;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {

  private SinglyLinkedList<Integer> list;

  @BeforeEach
  void setUp() {
    list = new SinglyLinkedList<>();
    list.add(10);
    list.add(20);
    list.add(30);
    list.add(40);
    list.add(50);
    list.add(60);
  }

  @AfterEach
  void tearDown() {
    list.clear();
  }

  @Test
  void size() {
    assertThat(list.size()).isEqualTo(6);
  }

  @Test
  void isEmpty() {
    assertThat(list.isEmpty()).isFalse();
  }

  @Test
  void add() {
    list.add(70);
    assertThat(list.size()).isEqualTo(7);
    assertThat(list.get(6)).isEqualTo(70);
  }

  @Test
  void clear() {
    list.clear();
    assertThat(list.size()).isEqualTo(0);
  }

  @Test
  void get() {
    assertThat(list.get(1)).isEqualTo(20);
    assertThat(list.get(4)).isEqualTo(50);
  }

  @Test
  void addLast() {
    list.addLast(70);
    assertThat(list.size()).isEqualTo(7);
    assertThat(list.get(6)).isEqualTo(70);
  }

  @Test
  void addFirst() {
    list.addFirst(50);
    assertThat(list.get(0)).isEqualTo(50);
  }
}
