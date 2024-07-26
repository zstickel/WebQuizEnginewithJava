package engine;
import java.util.concurrent.atomic.AtomicInteger;

public class IdCounter {

    private AtomicInteger counter = new AtomicInteger(0);

    public int getNextId() {
        return counter.incrementAndGet();
    }
}
