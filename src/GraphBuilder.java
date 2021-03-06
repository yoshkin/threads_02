import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class GraphBuilder implements Callable<Set<GoField>> {

    private final ExecutorService executorService;

    private final Figure nextFigure;

    private final GoField currentField;

    private final int deepLevel;

    public GraphBuilder(
            final ExecutorService executorService,
            final Figure currentFigure,
            final GoField currentField,
            final int deepLevel) {
        this.executorService = executorService;
        this.currentField = currentField;
        this.nextFigure = currentFigure == Figure.WHITE ? Figure.BLACK : Figure.WHITE;
        this.deepLevel = deepLevel;
    }

    @Override
    public Set<GoField> call() throws ExecutionException, InterruptedException {
        // BEGIN (write your solution here) #1
        if (isCurrentFieldFinal()) {
            Set<GoField> finalSet = new HashSet<>();
            finalSet.add(currentField);
            return finalSet;
        }

        final List<Future<Set<GoField>>> futures = new ArrayList<>();
        final Set<GoField> children = new HashSet<>();

        for (int y = 0; y < GoField.FIELD_SIZE; y++) {
            for (int x = 0; x < GoField.FIELD_SIZE; x++) {
                if (currentField.figures[y][x] != null) {
                    continue;
                }

                final GraphBuilder newGraphBuilder
                        = new GraphBuilder(
                        executorService,
                        nextFigure,
                        new GoField(currentField, nextFigure, y, x),
                        deepLevel + 1);
                if (isAsync()) {
                    final Future<Set<GoField>> future
                            = executorService.submit(newGraphBuilder);
                    futures.add(future);
                } else {
                    children.addAll(newGraphBuilder.call());
                }
            }
        }

        if (!futures.isEmpty()) {
            for (Future<Set<GoField>> future : futures) {
                try {
                    children.addAll(future.get());
                } catch (final InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return children;
        // END #4
    }

    private boolean isAsync() {
        return deepLevel <= 2;
    }

    private boolean isCurrentFieldFinal() {
        for (Figure[] line : currentField.figures) {
            for (Figure figure : line) {
                if (figure == null) {
                    return false;
                }
            }
        }
        return true;
    }
}