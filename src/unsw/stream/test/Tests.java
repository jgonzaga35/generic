package unsw.stream.test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unsw.stream.Table;
import unsw.stream.User;

public class Tests {
    // After doing Task 1 comment this out.
    @Test
    public void SimpleTest() {
        List<User> users = new ArrayList<User>();
        users.add(new User(true, "A", "Devs"));
        users.add(new User(true, "B", "Devs"));
        users.add(new User(false, "C", "Testers"));
        users.add(new User(true, "D", "Business Analysts"));
        users.add(new User(true, "E", "CEO"));

        Table table = new Table(users);
        assertIterableEquals(Arrays.asList(), table.toView().skip(5));
        assertIterableEquals(users, table.toView().skip(0));
        assertIterableEquals(Arrays.asList(), table.toView().take(0));
        assertIterableEquals(users, table.toView().take(5));

        // it's okay to use streams in tests only
        assertIterableEquals(users.stream().skip(2).collect(Collectors.toList()), table.toView().skip(2));
        assertIterableEquals(users.stream().takeWhile(x -> x.userId().equals("C") == false).collect(Collectors.toList()), table.toView().take(2));

        assertIterableEquals(users.stream().skip(2).takeWhile(x -> x.userId().equals("D") == false).collect(Collectors.toList()), table.toView().skip(2).take(1));
    }

    // After doing Task 1 uncomment all tests below this line
    /*

    @Test
    public void Task2_Count() {
        // TODO:
    }

    */ // remove this after Task 1.
}
