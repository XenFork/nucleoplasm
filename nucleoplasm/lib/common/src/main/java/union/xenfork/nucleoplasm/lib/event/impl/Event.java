package union.xenfork.nucleoplasm.lib.event.impl;

import net.minecraft.util.Identifier;

/**
 * @since copy to fabricmc
 * @param <T>
 */
public abstract class Event<T> {
    protected volatile T invoker;
    public final T invoker() {
        return invoker;
    }
    public abstract void register(T listener);

    public static final Identifier DEFAULT_PHASE = new Identifier("nucleoplasm_api", "default");

    public void register(Identifier phase, T listener) {
        // This is done to keep compatibility with existing Event subclasses, but they should really not be subclassing Event.
        register(listener);
    }

    public void addPhaseOrdering(Identifier firstPhase, Identifier secondPhase) {
        // This is not abstract to avoid breaking existing Event subclasses, but they should really not be subclassing Event.
    }
}
