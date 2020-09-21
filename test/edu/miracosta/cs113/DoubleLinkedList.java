package edu.miracosta.cs113;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements List<E>
{
    public static void main (String [] args)
    {
        DoubleLinkedList list = new DoubleLinkedList<String>();

        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");

        System.out.println(list.toString());
        System.out.println(list.size);
        list.clear();
//        System.out.println(list.toString());


//        list.iterator.setIndex(0);
//        System.out.println(list.get(0));
//        System.out.println(list.get(0));
//        System.out.println(list.iterator.index);
//        System.out.println(list.iterator.next());
//        System.out.println(list.iterator.index);
//        list.iterator.remove();
//        System.out.println(list.toString());

//        System.out.println(list.get(0));
//        System.out.println(list.iterator.hasPrevious());
//        System.out.println(list.iterator.previousIndex());
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    private Iterator iterator = new Iterator();

    public DoubleLinkedList() {}

    public String toString()
    {
        Node<E> node = head;
        String string = "";
        if (head == null)
        {
            string = "[]";
        }
        for (int i = 0; i < size; i++)
        {
            if (i == 0 && size == 1)
            {
                string += ("[" + node.data + "]");
            }
            else if (i == 0)
            {
                string += ("[" + node.data + ", ");
            }
            else if (i == size - 1)
            {
                string += (node.data + "]");
            }
            else
            {
                string += (node.data + ", ");
            }
            node = node.next;
        }
        return string;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean contains(Object o)
    {
        Node<E> node = head;
        for (int i = 0; i < size; i++)
        {
            if (String.valueOf(node.data).equals(String.valueOf(o)))
            {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public java.util.Iterator<E> iterator()
    {
        return null;
    }

    @Override
    public Object[] toArray()
    {
        return new Object[0];
    }

    @Override
    public <E> E[] toArray(E[] a)
    {
        return null;
    }

    @Override
    public boolean add(E e)
    {
        add(size, e);
        return true;
    }

    @Override
    public boolean remove(Object o)
    {
        if (contains(o))
        {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < size - 1; i++)
        {
            System.out.println("DEBUG Size:" + size + " I: " + i);
            remove(i + 1);
        }
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        iterator.setIndex(index);
        return iterator.lastItemReturned.data;
    }

    @Override
    public E set(int index, E element)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        iterator.setIndex(index);
        iterator.set(element);
        return element;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        iterator.setIndex(index);
        iterator.add(element);
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        iterator.setIndex(index);
        iterator.remove();
        return iterator.lastItemReturned.getData();
    }

    @Override
    public int indexOf(Object o)
    {
        Node<E> node = head;
        for (int i = 0; i < size; i++)
        {
            if (String.valueOf(node.data).equals(String.valueOf(o)))
            {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        Node<E> node = tail;
        for (int i = size - 1; i >= 0; i--)
        {
            if (String.valueOf(node.data).equals(String.valueOf(o)))
            {
                return i;
            }
            node = node.previous;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new Iterator();
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        return new Iterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return null;
    }

    public class Iterator implements ListIterator<E>
    {
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        public Iterator() {}

        public Iterator(int i)
        {
            // Out of Bounds
            if (i < 0)
            {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            // Case if its the last item
            else if (i == size)
            {
                this.index = size;
                nextItem = null;
            }
            // Start at the top
            else
            {
                nextItem = head;
                for (index = 0; index < i; index++)
                {
                    nextItem = nextItem.next;
                }
            }
        }

//        Inserts the specified element into the list
        public void add(E e)
        {
            // Adds to head if no other items in list
            if (head == null)
            {
                head = new Node<E>(e);
                tail = head;
            }
            // Adds to head
            else if (nextItem == head)
            {
                Node<E> newNode = new Node<E>(e);
                newNode.next = nextItem;
                nextItem.previous = newNode;
                head = newNode;
            }
            // Adds to tail
            else if (nextItem == null)
            {
                Node<E> newNode = new Node<E>(e);
                tail.next = newNode;
                newNode.previous = tail;
                tail = newNode;
            }
            // Adds to the middle of the list
            else
            {
                Node<E> newNode = new Node<E>(e);
                newNode.previous = nextItem.previous;
                nextItem.previous.next = newNode;
                newNode.next = nextItem;
                nextItem.previous = newNode;

            }
            size++;
            index++;
        }

//        Returns true if this list iterator has more elements when traversing the list in the forward direction.
        public boolean hasNext()
        {
            return (nextItem != null);
        }

//        Returns true if this list iterator has more elements when traversing the list in the reverse direction.
        public boolean hasPrevious()
        {
            return (nextItem == null && size != 0) || nextItem.previous != null;
        }

//        Returns the next element in the list and advances the cursor position.
        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

//        Returns the index of the element that would be returned by a subsequent call to next().
        public int nextIndex()
        {
//            if (index == 0)
//            {
//                return index;
//            }
            return index;
        }

//        Returns the previous element in the list and moves the cursor position backwards.
        public E previous()
        {
            if (!hasPrevious())
            {
                throw new NoSuchElementException();
            }
            // if iterator past the last element
            if (nextItem == null)
            {
                nextItem = tail;
            }
            else
            {
                nextItem = nextItem.previous;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

//        Returns the index of the element that would be returned by a subsequent call to previous().
        public int previousIndex()
        {
            return (index - 1);
        }

//        Removes from the list the last element that was returned by next() or previous() (optional operation).
        public void remove()
        {
            if (index == 0)
            {
                lastItemReturned.next.previous = null;
                head = lastItemReturned.next;
            }
            else if (index == size - 1)
            {
                lastItemReturned.previous.next = null;
                tail = lastItemReturned.previous;
            }
            else
            {
                lastItemReturned.previous.next = lastItemReturned.next;
                lastItemReturned.next.previous = lastItemReturned.previous;
            }
//            size--;
        }

//        Replaces the last element returned by next() or previous() with the specified element (optional operation).
        public void set(E e)
        {
            if (lastItemReturned == null)
            {
                throw new NoSuchElementException();
            }
            lastItemReturned.data = e;
        }

        public void setIndex(int index)
        {
            while (this.index != index)
            {
                if (this.index > index)
                {
                    previous();
                }
                else
                {
                    next();
                }
            }
        }
    }

    public static class Node<E>
    {
        public E data;
        public Node<E> next;
        public Node<E> previous;

        private Node() {}

        private Node(E data)
        {
            this.data = data;
        }

        private Node(E data, Node<E> next, Node<E> previous)
        {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public E getData()
        {
            return data;
        }

        public void setData(E data)
        {
            this.data = data;
        }

        public Node<E> getNext()
        {
            return next;
        }

        public void setNext(Node<E> next)
        {
            this.next = next;
        }

        public Node<E> getPrevious()
        {
            return previous;
        }

        public void setPrevious(Node<E> previous)
        {
            this.previous = previous;
        }
    }
}
