import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newWorkStealingPool(3);
        final GraphBuilder gb = new GraphBuilder(executorService, Figure.WHITE, new GoField(), 0);
        final Future<Set<GoField>> future = executorService.submit(gb);
        try {
            final Set<GoField> fields = future.get(4, TimeUnit.SECONDS);
            //Uncomment the lines of code, as below, for more informative output.
            /*int i = 1;
            for (GoField field: fields){
                System.out.printf("#%d  %s \n", i, field.toString());
                i++;
            }*/
            System.out.printf("Total %d fields\n", fields.size());

            if (fields.size() != 126) {
                throw new RuntimeException("The amount of unique fields is not correct");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new RuntimeException("Task was not finished withing 4 seconds!");
        } finally {
            executorService.shutdownNow();
        }
    }
}