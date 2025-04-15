import java.util.List;

class SupportChainBuilder {
    public static SupportHandler build(List<SupportHandler> handlers) {
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNext(handlers.get(i + 1));
        }
        return handlers.get(0);
    }
}
