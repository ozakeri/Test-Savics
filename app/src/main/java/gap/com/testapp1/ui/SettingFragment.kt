package gap.com.testapp1.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import gap.com.testapp1.R
import gap.com.testapp1.utils.PreferenceHelper._max
import gap.com.testapp1.utils.PreferenceHelper.customPreference
import gap.com.testapp1.utils.PreferenceHelper.numberCurrent
import gap.com.testapp1.utils.PreferenceHelper.usernameCurrent

class SettingFragment : Fragment() {

    //  val sharedPreference =  requireActivity().getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
    val CUSTOM_PREF_NAME = "User_data"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        /*-----init-----*/
        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        val currentUsername: EditText = root.findViewById(R.id.editTextTextPersonName4)
        val currentNumber: EditText = root.findViewById(R.id.editTextTextPersonName5)
        val max: EditText = root.findViewById(R.id.editTextTextPersonName6)
        val save: Button = root.findViewById(R.id.button_save)


        /*-----save data click button SAVE-----*/
        save.setOnClickListener {

            if (TextUtils.isEmpty(currentUsername.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(currentNumber.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(max.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputUsername: String = currentUsername.text.toString()
            val inputNumber: String = currentNumber.text.toString()
            val inputMax: String = max.text.toString()

            val prefs = customPreference(requireActivity(), CUSTOM_PREF_NAME)
            prefs.usernameCurrent = inputUsername
            prefs.numberCurrent = inputNumber.toInt()
            prefs._max = inputMax.toInt()

            println(prefs.usernameCurrent.toString())
            println(prefs.numberCurrent.toString())
            println(prefs._max.toString())

            Toast.makeText(activity, "save Values", Toast.LENGTH_LONG).show()
        }

        return root
    }
}