package gap.com.testapp1.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferenceHelper {

    val currentUsername = "username"
    val currentNumber = "currentNumber"
    val max = "max"
    val counter = "counter"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.usernameCurrent
        get() = getString(currentUsername, "")
        set(value) {
            editMe {
                it.putString(currentUsername, value)
            }
        }

    var SharedPreferences.numberCurrent
        get() = getInt(currentNumber, 0)
        set(value) {
            editMe {
                it.putInt(currentNumber, value)
            }
        }

    var SharedPreferences._max
        get() = getInt(max, 0)
        set(value) {
            editMe {
                it.putInt(max, value)
            }
        }

    var SharedPreferences._counter
        get() = getInt(counter, 0)
        set(value) {
            editMe {
                it.putInt(counter, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}

