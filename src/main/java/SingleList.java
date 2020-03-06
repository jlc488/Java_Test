public class SingleList {
    private Node head;

    /**
     * Adds a value to the end of the list.
     *
     * @param value the value to add.
     * @return the position that the value was added at.
     */
    public int add(final String value) {
        int idx = 1;
        Node n = head;
        Node tmpNode = new Node(value);

        if (n == null) {
            this.head = tmpNode;
            return 0;
        }

        while (n.next != null) {
            n = n.next;
            idx++;
        }
        n.next = tmpNode;

        return idx;
    }

    /**
     * Adds a value to the specified position in the list.
     *
     * @param position the index of where to add the value.
     * @param value    the value to add.
     * @throws IllegalArgumentException if the position > size + 1 or < 0.
     * @see #size
     */
    public void addAt(final int position,
                      final String value) {
        int size = this.size();
        if (position < 0 || position > size + 1) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(value);

        if (position == 0) {
            if (head != null) {
                newNode.next = this.head;
            }
            this.head = newNode;
        } else {
            Node currNode = this.head;
            Node previous = null;

            int idx = 0;

            while (idx < position) {
                previous = currNode;
                currNode = currNode.next;
                if (currNode == null) {
                    break;
                }
                idx++;
            }
            newNode.next = currNode;
            previous.next = newNode;
        }

    }

    /**
     * Removed the first occurrence of the value from the list.
     *
     * @param value the value to remove.
     * @return the position the value was removed from, or -1 if value was not present.
     */
    public int remove(final String value) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Remove the item at the specificed position.
     *
     * @param position the index of the item to remove.
     * @return the value that was removed.
     * @throws IllegalArgumentException if the position > size + 1 or < 0.
     * @see #size
     */
    public String removeAt(final int position) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Remove all occurrences of value.
     *
     * @param value the value to remove.
     * @return the number of the removed items.
     */
    public int removeAll(final String value) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Get the value at the specified position.
     *
     * @param position the index of the item to get.
     * @return the value at the position.
     * @throws IllegalArgumentException if the list is empty, the position > size + 1 or < 0.
     * @see #size
     */
    public String getAt(final int position) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Get the position of the first occurrence of the specified value.
     *
     * @param value the value to find the position of.
     * @return the index of the value, or -1 if the value is not present.
     */
    public int find(final String value) {
        boolean flag = false;
        int idx = 0;
        Node n = head;

        while (n != null) {
            if (n.value == value) {
                flag = true;
                break;
            }
            idx++;
            n = n.next;
        }

        return flag ? idx : -1;
    }

    /**
     * Reset the list so that it has no values.
     */
    public void clear() {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Get the number of values in the list.
     *
     * @return the number of values.
     */
    public int size() {
        int ret = 1;

        if (head != null) {
            Node n = head.next;

            while (n != null) {
                n = n.next;
                ret++;
            }
        } else {
            ret = 0;
        }

        return ret;
    }

    /**
     * @return
     */
    public String toString() {
        String ret = "";
        Node n = head;
        int idx = 0;

        while (n != null) {
            if (idx > 0) {
                ret += " -> ";
            }
            ret += n.value;
            n = n.next;
            idx++;
        }

        return ret;
    }

    private static class Node {
        private String value;
        private Node next;

        Node(final String val) {
            value = val;
        }
    }
}
