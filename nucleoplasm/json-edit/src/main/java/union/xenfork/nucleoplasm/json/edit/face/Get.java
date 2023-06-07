package union.xenfork.nucleoplasm.json.edit.face;

public interface Get<T> {
    default T get() {
        throw new AssertionError();
    }

    default void set(T t) {}
}
