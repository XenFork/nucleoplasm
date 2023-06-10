package union.xenfork.nucleoplasm.normandy.login.utils;

public class LockUtil {
    public static String reChange(String from) {
        char[] froms = from.toCharArray();
        int length = froms.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = froms[i];
            froms[i] = froms[length - 1 - i];
            froms[length - 1 - i] = temp;
        }
        return String.valueOf(froms);
    }

    public static String leftMove(String from) {
        String first = from.substring(0, 3);
        String second = from.substring(3);
        first = reChange(first);
        second = reChange(second);
        from = first + second;
        from = reChange(from);
        return from;
    }

    public static String rightmove(String from) {
        from = reChange(from);
        String first = from.substring(0, 3);
        String second = from.substring(3);
        first = reChange(first);
        second = reChange(second);
        from = first + second;
        return from;
    }
}
