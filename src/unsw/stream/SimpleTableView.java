package unsw.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class SimpleTableView implements TableView {
    private Iterator<User> it;

    public SimpleTableView() {
        this(Arrays.<User>asList().iterator());
    }

    public SimpleTableView(Iterator<User> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public User next() {
        return it.next();
    }

    @Override
    public TableView take(int numberOfItems) {
        SimpleTableView parent = this;

        return new SimpleTableView() {
            private int itemsLeft = numberOfItems;

            @Override
            public boolean hasNext() {
                return itemsLeft > 0 && parent.hasNext();
            }

            @Override
            public User next() {
                if (hasNext()) {
                    itemsLeft--;
                    return parent.next();
                } else
                    throw new NoSuchElementException();
            }
        };
    }

    @Override
    public TableView skip(int numberOfItems) {
        while (numberOfItems > 0 && hasNext()) {
            numberOfItems--;
            next();
        }
        return this;
    }

    @Override
    public Table toTable() {
        List<User> list = new ArrayList<User>();
        it.forEachRemaining(list::add);
        return new Table(list);
    }

    @Override
    public Iterator<User> iterator() {
        // *technically* this is non standard
        // since this should reproduce a unique iterator each time
        // but for our sakes it's fine, since any operation on an
        // iterator will implicitly invalidate the inner iterators
        // invalidating it's original context anyways.
        return this;
    }

    @Override
    public int count() {
        // TODO: Task 2
        return 0;
    }

    @Override
    public <R> R reduce(BiFunction<R, User, R> reducer, R initial) {
        // TODO: Task 2
        return null;
    }

    @Override
    public <R> R parallelReduce(BiFunction<R, User, R> reducer, R initial, int numberOfThreads) {
        // TODO: Task 3
        return null;
    }
}
