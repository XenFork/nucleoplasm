package union.xenfork.nucleoplasm.lib.event.impl;

import net.minecraft.util.Identifier;

import java.util.function.Function;

public class EventFactory {
    private EventFactory() { }

    public static <T> Event<T> createArrayBacked(Class<? super T> type, Function<T[], T> invokerFactory) {
        return EventFactoryImpl.createArrayBacked(type, invokerFactory);
    }

    public static <T> Event<T> createArrayBacked(Class<T> type, T emptyInvoker, Function<T[], T> invokerFactory) {
        return createArrayBacked(type, listeners -> {
            if (listeners.length == 0) {
                return emptyInvoker;
            } else if (listeners.length == 1) {
                return listeners[0];
            } else {
                return invokerFactory.apply(listeners);
            }
        });
    }

    public static <T> Event<T> createWithPhases(Class<? super T> type, Function<T[], T> invokerFactory, Identifier... defaultPhases) {
        EventFactoryImpl.ensureContainsDefault(defaultPhases);
        EventFactoryImpl.ensureNoDuplicates(defaultPhases);

        Event<T> event = createArrayBacked(type, invokerFactory);

        for (int i = 1; i < defaultPhases.length; ++i) {
            event.addPhaseOrdering(defaultPhases[i-1], defaultPhases[i]);
        }

        return event;
    }

}
