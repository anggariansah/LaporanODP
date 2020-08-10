package tik.pnj.laporanodp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    private static final String PREFERENCES_NAME = "OdpPref";
    private static final String ID_KEY = "IdKey";
    private static final String ROLE_KEY = "role";
    private static final String IS_USER_LOGIN = "UserLogin";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context c;

    public UserPreference(Context context) {
        c = context;
        pref = c.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String role) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(ID_KEY, id);
        editor.putString(ROLE_KEY, role);
        editor.apply();
    }


    public void deleteLoginSession() {
        editor.remove(ID_KEY);
        editor.remove(IS_USER_LOGIN);
        editor.remove(ROLE_KEY);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public String getUserId() {
        return pref.getString(ID_KEY, "");
    }

    public String getUserRole() {
        return pref.getString(ROLE_KEY, "");
    }

}
