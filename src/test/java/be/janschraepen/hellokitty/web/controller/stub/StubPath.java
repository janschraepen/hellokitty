package be.janschraepen.hellokitty.web.controller.stub;

import javax.validation.Path;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by jan on 05/06/15.
 */
public class StubPath implements Path {

    @Override
    public Iterator<Node> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Node> action) {

    }

    @Override
    public Spliterator<Node> spliterator() {
        return null;
    }

    @Override
    public String toString() {
        return "path:field";
    }
}
