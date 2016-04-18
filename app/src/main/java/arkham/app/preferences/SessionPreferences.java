package arkham.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferences {

    private static String PREF_NAME = "Memory";
    private static String AUTH_TOKEN = "authToken ";

    public static boolean saveSessionToken(String auth_token , Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(AUTH_TOKEN , auth_token);
        return editor.commit();
    }

    public static String getSessionToken(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(
                PREF_NAME, 0);
        return savedSession.getString(AUTH_TOKEN , null);
    }
}
