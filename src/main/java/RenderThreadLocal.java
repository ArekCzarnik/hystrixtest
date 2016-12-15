import com.eclipsesource.v8.V8;
import io.netty.util.concurrent.FastThreadLocal;

public class RenderThreadLocal {

    private static final FastThreadLocal<V8> V8_THREAD_LOCAL = new FastThreadLocal<V8>() {
        @Override
        protected V8 initialValue() {
            System.out.println("init v8-Render:" + Thread.currentThread().getName());
            final V8 v8Runtime = V8.createV8Runtime();
            return v8Runtime;
        }
    };

    public V8 get() {
        return V8_THREAD_LOCAL.get();
    }


}
