package dynamicarray;

import java.util.Iterator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DynamicArrayTest {

  private final Array<Integer> array = new Array<>(5);

  @BeforeEach
  void setUp() {
    array.add(2);
    array.add(4);
    array.add(0);
    array.add(9);
    array.add(7);
  }

  @AfterEach
  void tearDown() {
    array.clear();
  }

  @Test
  void set() {
    array.set(2, 11);
    assertThat(array.get(2)).isEqualTo(11);
  }

  @Test
  void get() {
    assertThat(array.get(4)).isEqualTo(7);
    assertThat(array.get(array.size() + 1)).isNull();
  }

  @Test
  void add() {
    assertThat(array.size()).isEqualTo(5);
    array.add(10);
    assertThat(array.get(5)).isEqualTo(10);
    assertThat(array.size()).isEqualTo(6);
  }

  @Test
  void remove() {
    assertThat(array.remove(9)).isTrue();
    assertThat(array.size()).isEqualTo(4);
    assertThat(array.indexOf(9)).isEqualTo(-1);
    assertThat(array.indexOf(7)).isEqualTo(3);
  }

  @Test
  void contains() {
    assertThat(array.contains(4)).isTrue();
    assertThat(array.contains(20)).isFalse();
  }

  @Test
  void indexOf() {
    assertThat(array.indexOf(9)).isEqualTo(3);
    assertThat(array.indexOf(20)).isEqualTo(-1);
  }

  @Test
  void removeAt() {
    assertThat(array.removeAt(1)).isEqualTo(4);
    assertThat(array.size()).isEqualTo(4);
    assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class)
        .isThrownBy(() -> array.removeAt(10))
        .withMessage("Given index is oud of bound.");
  }

  @Test
  void clear() {
    array.clear();
    assertThat(array.size()).isEqualTo(0);
  }

  @Test
  void size() {
    assertThat(array.size()).isEqualTo(5);
  }

  @Test
  void isEmpty() {
    assertThat(array.isEmpty()).isFalse();
    array.clear();
    assertThat(array.isEmpty()).isTrue();
  }

  @Test
  void iterator() {
    Iterator<Integer> iterator = array.iterator();
    for (int i = 0; i < array.size(); i++) {
      assertThat(iterator.hasNext()).isTrue();
      iterator.next();
    }
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(iterator::remove);
  }

  @Test
  void testToString() {
    assertThat(array.toString()).isEqualTo("[2,4,0,9,7]");
  }
}
