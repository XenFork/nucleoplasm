package union.xenfork.nucleoplasm.normandy.login.utils;

import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

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
        String first = from.substring(0, NucleoplasmServer.cfg.entity.i);
        String second = from.substring(NucleoplasmServer.cfg.entity.i);
        first = reChange(first);
        second = reChange(second);
        from = first + second;
        from = reChange(from);
        return from;
    }

    public static String rightmove(String from) {
       from = reChange(from);
        String first = from.substring(0, NucleoplasmServer.cfg.entity.i);
        String second = from.substring(NucleoplasmServer.cfg.entity.i);
        first = reChange(first);
        second = reChange(second);
        first = first + second;
        return from;
    }
}
