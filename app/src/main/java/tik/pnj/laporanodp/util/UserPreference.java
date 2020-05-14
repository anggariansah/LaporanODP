package tik.pnj.laporanodp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {

    private static final String PREFERENCES_NAME = "OdpPref";
    private static final String ID_KEY = "IdKey";
    private static final String IS_USER_LOGIN = "UserLogin";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context c;

    public UserPreference(Context context) {
        c = context;
        pref = c.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(int id) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putInt(ID_KEY, id);
        editor.apply();
    }

    public void deleteLoginSession() {
        editor.remove(ID_KEY);
        editor.remove(IS_USER_LOGIN);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public int getUserId(){
        return pref.getInt(ID_KEY, 0);
    }


}
