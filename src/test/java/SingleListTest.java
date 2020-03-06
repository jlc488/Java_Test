import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleListTest {
    private static <T> void checkList(final SingleList list,
                                      final int expectedSize,
                                      final T expectedToString) {
        final int size;
        final String toString;

        size = list.size();
        toString = list.toString();

        assertThat(size, equalTo(expectedSize));
        assertThat(toString, equalTo(expectedToString));
    }

    private static void addAtBad(final SingleList list,
                                 final int position,
                                 final String value,
                                 final String expectedMessage) {
        final IllegalArgumentException ex;

        ex = assertThrows(IllegalArgumentException.class, () -> list.addAt(position, value));
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    private static void removeAtBad(final SingleList list,
                                    final int position,
                                    final String expectedMessage) {
        final IllegalArgumentException ex;

        ex = assertThrows(IllegalArgumentException.class, () -> list.removeAt(position));
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    private static void getAtBad(final SingleList list,
                                 final int position,
                                 final String expectedMessage) {
        final IllegalArgumentException ex;

        ex = assertThrows(IllegalArgumentException.class, () -> list.getAt(position));
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }

    private static <T> void getAt(final SingleList list,
                                  final int position,
                                  final T expectedValue) {
        final String value;

        value = list.getAt(position);
        assertThat(value, equalTo(expectedValue));
    }

    private static void find(final SingleList list,
                             final String value,
                             final int expectedPosition) {
        final int position;

        position = list.find(value);
        assertThat(position, equalTo(expectedPosition));
    }

    @Test
    void add() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("abc"), equalTo(0));
        checkList(listA, 1, "abc");
        checkList(listB, 0, "");

        assertThat(listA.add("def"), equalTo(1));
        checkList(listA, 2, "abc -> def");
        checkList(listB, 0, "");

        assertThat(listA.add("ghi"), equalTo(2));
        checkList(listA, 3, "abc -> def -> ghi");
        checkList(listB, 0, "");
    }

    @Ignore
    void addAtBad() {
        final SingleList list;

        list = new SingleList();
        addAtBad(list, 1, "", "position must be <= 0, was: 1");
        addAtBad(list, 2, "X", "position must be <= 0, was: 2");

        list.add("Hello");
        addAtBad(list, 2, "123", "position must be <= 1, was: 2");

        addAtBad(list, -1, "123", "position must be >= 0, was: -1");
        addAtBad(list, -2, "123", "position must be >= 0, was: -2");
    }

    @Test
    void addAt() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        checkList(listA, 0, "");
        checkList(listB, 0, "");

        listA.addAt(0, "1");
        checkList(listA, 1, "1");
        checkList(listB, 0, "");

        listA.addAt(0, "2");
        checkList(listA, 2, "2 -> 1");
        checkList(listB, 0, "");

        listA.addAt(0, "3");
        checkList(listA, 3, "3 -> 2 -> 1");
        checkList(listB, 0, "");

        listA.addAt(3, "4");
        checkList(listA, 4, "3 -> 2 -> 1 -> 4");
        checkList(listB, 0, "");

        listA.addAt(1, "5");
        checkList(listA, 5, "3 -> 5 -> 2 -> 1 -> 4");
        checkList(listB, 0, "");

        listA.addAt(1, "6");
        checkList(listA, 6, "3 -> 6 -> 5 -> 2 -> 1 -> 4");
        checkList(listB, 0, "");

        listA.addAt(5, "7");
        checkList(listA, 7, "3 -> 6 -> 5 -> 2 -> 1 -> 7 -> 4");
        checkList(listB, 0, "");
    }

    @Ignore
    void removeValue() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.remove("123"), equalTo(-1));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("123"), equalTo(0));
        checkList(listA, 1, "123");
        checkList(listB, 0, "");

        assertThat(listA.remove("123"), equalTo(0));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.remove("123"), equalTo(-1));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("123"), equalTo(0));
        checkList(listA, 1, "123");
        checkList(listB, 0, "");

        assertThat(listA.add("456"), equalTo(1));
        checkList(listA, 2, "123 -> 456");
        checkList(listB, 0, "");

        listA.addAt(1, "789");
        checkList(listA, 3, "123 -> 789 -> 456");
        checkList(listB, 0, "");

        assertThat(listA.add("456"), equalTo(3));
        checkList(listA, 4, "123 -> 789 -> 456 -> 456");
        checkList(listB, 0, "");

        assertThat(listA.remove(""), equalTo(-1));
        checkList(listA, 4, "123 -> 789 -> 456 -> 456");
        checkList(listB, 0, "");

        assertThat(listA.remove("456"), equalTo(2));
        checkList(listA, 3, "123 -> 789 -> 456");
        checkList(listB, 0, "");

        assertThat(listA.remove("789"), equalTo(1));
        checkList(listA, 2, "123 -> 456");
        checkList(listB, 0, "");

        assertThat(listA.remove("456"), equalTo(1));
        checkList(listA, 1, "123");
        checkList(listB, 0, "");

        assertThat(listA.remove("123"), equalTo(0));
        checkList(listA, 0, "");
        checkList(listB, 0, "");
    }

    @Ignore
    void removeAtBad() {
        final SingleList list;

        list = new SingleList();

        removeAtBad(list, 0, "cannot removeAt from an empty list");

        list.add("Hello");
        removeAtBad(list, 1, "position must be < 1, was: 1");
        removeAtBad(list, 2, "position must be < 1, was: 2");

        removeAtBad(list, -1, "position must be >= 0, was: -1");
        removeAtBad(list, -2, "position must be >= 0, was: -2");
    }

    @Ignore
    void removeAt() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.remove("a"), equalTo(-1));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("a"), equalTo(0));
        checkList(listA, 1, "a");
        checkList(listB, 0, "");

        assertThat(listA.removeAt(0), equalTo("a"));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("a"), equalTo(0));
        checkList(listA, 1, "a");
        checkList(listB, 0, "");

        assertThat(listA.add("b"), equalTo(1));
        checkList(listA, 2, "a -> b");
        checkList(listB, 0, "");

        assertThat(listA.add("c"), equalTo(2));
        checkList(listA, 3, "a -> b -> c");
        checkList(listB, 0, "");

        assertThat(listA.add("d"), equalTo(3));
        checkList(listA, 4, "a -> b -> c -> d");
        checkList(listB, 0, "");

        assertThat(listA.removeAt(1), equalTo("b"));
        checkList(listA, 3, "a -> c -> d");
        checkList(listB, 0, "");

        assertThat(listA.removeAt(2), equalTo("d"));
        checkList(listA, 2, "a -> c");
        checkList(listB, 0, "");

        assertThat(listA.removeAt(0), equalTo("a"));
        checkList(listA, 1, "c");
        checkList(listB, 0, "");

        assertThat(listA.removeAt(0), equalTo("c"));
        checkList(listA, 0, "");
        checkList(listB, 0, "");
    }

    @Ignore
    void removeAll() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.removeAll(""), equalTo(0));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add(null), equalTo(0));
        checkList(listA, 1, "null");
        checkList(listB, 0, "");

        assertThat(listA.removeAll(""), equalTo(0));
        checkList(listA, 1, "null");
        checkList(listB, 0, "");

        assertThat(listA.removeAll(null), equalTo(1));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.removeAll(null), equalTo(0));
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("one"), equalTo(0));
        checkList(listA, 1, "one");
        checkList(listB, 0, "");

        assertThat(listA.add("one"), equalTo(1));
        checkList(listA, 2, "one -> one");
        checkList(listB, 0, "");

        assertThat(listA.add("one"), equalTo(2));
        checkList(listA, 3, "one -> one -> one");
        checkList(listB, 0, "");

        assertThat(listA.add("two"), equalTo(3));
        checkList(listA, 4, "one -> one -> one -> two");
        checkList(listB, 0, "");

        assertThat(listA.add("one"), equalTo(4));
        checkList(listA, 5, "one -> one -> one -> two -> one");
        checkList(listB, 0, "");

        assertThat(listA.add("three"), equalTo(5));
        checkList(listA, 6, "one -> one -> one -> two -> one -> three");
        checkList(listB, 0, "");

        assertThat(listA.add("two"), equalTo(6));
        checkList(listA, 7, "one -> one -> one -> two -> one -> three -> two");
        checkList(listB, 0, "");

        assertThat(listA.removeAll("one"), equalTo(4));
        checkList(listA, 3, "two -> three -> two");
        checkList(listB, 0, "");

        assertThat(listA.removeAll("three"), equalTo(1));
        checkList(listA, 2, "two -> two");
        checkList(listB, 0, "");

        assertThat(listA.removeAll("three"), equalTo(0));
        checkList(listA, 2, "two -> two");
        checkList(listB, 0, "");

        assertThat(listA.removeAll("two"), equalTo(2));
        checkList(listA, 0, "");
        checkList(listB, 0, "");
    }

    @Ignore
    void getAtBad() {
        final SingleList list;

        list = new SingleList();

        getAtBad(list, 0, "cannot getAt from an empty list");

        list.add("Hello");
        getAtBad(list, 1, "position must be < 1, was: 1");
        getAtBad(list, 2, "position must be < 1, was: 2");

        getAtBad(list, -1, "position must be >= 0, was: -1");
        getAtBad(list, -2, "position must be >= 0, was: -2");
    }

    @Ignore
    void getAt() {
        final SingleList list;

        list = new SingleList();
        checkList(list, 0, "");

        list.add("ABC");
        getAt(list, 0, "ABC");
        checkList(list, 1, "ABC");

        list.add("DEF");
        getAt(list, 0, "ABC");
        getAt(list, 1, "DEF");
        checkList(list, 2, "ABC -> DEF");

        list.addAt(0, null);
        getAt(list, 0, null);
        getAt(list, 1, "ABC");
        getAt(list, 2, "DEF");
        checkList(list, 3, "null -> ABC -> DEF");

        list.addAt(0, "GHI");
        getAt(list, 0, "GHI");
        getAt(list, 1, null);
        getAt(list, 2, "ABC");
        getAt(list, 3, "DEF");
        checkList(list, 4, "GHI -> null -> ABC -> DEF");
    }

    @Ignore
    void find() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        find(listA, "X", -1);
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        listA.add("X");
        find(listA, "X", 0);
        find(listB, "X", -1);
        checkList(listA, 1, "X");
        checkList(listB, 0, "");

        listA.add("Y");
        find(listA, "Y", 1);
        checkList(listA, 2, "X -> Y");
        checkList(listB, 0, "");

        listA.add(null);
        find(listA, "X", 0);
        find(listA, "Y", 1);
        find(listA, null, 2);
        checkList(listA, 3, "X -> Y -> null");
        checkList(listB, 0, "");

        listA.addAt(0, "Z");
        find(listA, "Z", 0);
        find(listA, "X", 1);
        find(listA, "Y", 2);
        find(listA, null, 3);
        checkList(listA, 4, "Z -> X -> Y -> null");
        checkList(listB, 0, "");
    }

    @Ignore
    void clear() {
        final SingleList listA;
        final SingleList listB;

        listA = new SingleList();
        listB = new SingleList();

        listA.add("Hello");
        checkList(listA, 1, "Hello");
        checkList(listB, 0, "");

        listA.clear();
        checkList(listA, 0, "");
        checkList(listB, 0, "");

        assertThat(listA.add("Hello"), equalTo(0));
        assertThat(listA.add("Evil"), equalTo(1));
        assertThat(listA.add("World"), equalTo(2));
        checkList(listA, 3, "Hello -> Evil -> World");
        checkList(listB, 0, "");

        listA.clear();
        checkList(listA, 0, "");
        checkList(listB, 0, "");
    }

    @Ignore
    void size() {
        // already tested in other methods
    }

    @Ignore
    void testToString() {
        // already tested in other methods
    }
}