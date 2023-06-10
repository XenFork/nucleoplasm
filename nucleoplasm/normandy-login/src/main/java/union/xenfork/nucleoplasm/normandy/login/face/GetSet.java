package union.xenfork.nucleoplasm.normandy.login.face;

public interface GetSet {
    default void setLogin(boolean is_login) {
        throw new AssertionError();
    }
}
