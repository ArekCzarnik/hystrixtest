import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.netflix.hystrix.HystrixCommand;

public class RenderCommand extends HystrixCommand<String> {

    private static final String RESSOURCE_NAME = RenderCommand.class.getSimpleName();
    public static final int EXECUTION_TIMEOUT = 30000;
    private final RenderThreadLocal renderer;

    public RenderCommand() {
        super(HystrixUtils.buildHystrixProperties(RESSOURCE_NAME, EXECUTION_TIMEOUT, 4, 8, 10, true));
        this.renderer = new RenderThreadLocal();
    }

    @Override
    protected String run() {
        final V8 v8 = renderer.get();
        System.out.println("V8 hello World...:"+Thread.currentThread().getName());
        return v8.executeStringScript("'Hello from JS!'");
    }

}