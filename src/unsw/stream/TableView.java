package unsw.stream;

import java.util.Iterator;
import java.util.function.BiFunction;

public interface TableView extends Iterator<User>, Iterable<User>
{
    /**
     * Grab a subset of the table view
     * @param numberOfItems The number of items to take, the rest are ignored
     */
    public TableView take(int numberOfItems);

    /**
     * Grab a subset of the table view
     * @param numberOfItems The number of items to skip, the rest are taken
     */
    public TableView skip(int numberOfItems);

    /**
     * Count number of records left
     */
    public int count();

    /**
     * Reduce the view into a value.
     * 
     * For example the `sum` method for Fruit ages would look like;
     * `reduce((acc, fruit) -> acc + fruit.age(), 0)`
     * 
     * reducer:
     *  - First argument is the current accumulated value
     *  - Second argument is the next item in the stream
     *  - Should return the next accumulated value.
     * 
     * For example applying sum over ages (1, 2, 3, 4) is equal to
     * ((((0 + 1) + 2) + 3) + 4)
     */
    public<R> R reduce(BiFunction<R, User, R> reducer, R initial);

    public<R> R parallelReduce(BiFunction<R, User, R> reducer, R initial, int numberOfThreads);

    /**
     * Convert the remaining records into a table.
     */
    public Table toTable();
}
