import org.junit.Test;

public class RenderTest {

    @Test
    public void render() {
        System.out.println("current Thread:"+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {

            final RenderCommand renderCommand = new RenderCommand();
            final String text = renderCommand.execute();

        }
    }
}
