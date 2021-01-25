package gap.com.testapp1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gap.com.testapp1.R
import gap.com.testapp1.model.User
import gap.com.testapp1.utils.PreferenceHelper
import gap.com.testapp1.utils.PreferenceHelper._counter
import gap.com.testapp1.utils.PreferenceHelper._max
import org.json.JSONObject

class HomeFragment : Fragment() {

    val user: User? = null
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    val arrayList: ArrayList<String> = ArrayList()
    val CUSTOM_PREF_NAME = "User_data"
    val rootObject = JSONObject()
    var string: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*-----init-----*/
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val listView: ListView = root.findViewById(R.id.listView)
        val edt_name: EditText = root.findViewById(R.id.editTextTextPersonName)
        val edt_age: EditText = root.findViewById(R.id.editTextTextPersonName2)
        val edt_email: EditText = root.findViewById(R.id.editTextTextPersonName3)
        val radioGroup: RadioGroup = root.findViewById(R.id.radioGroup)
        val floatingActionButton: FloatingActionButton =
            root.findViewById(R.id.floatingActionButton)


        /*-----floatingActionButton click listener-----*/
        floatingActionButton.setOnClickListener {

            if (TextUtils.isEmpty(edt_name.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(edt_age.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(edt_email.text.toString())) {
                Toast.makeText(activity, "Empty field not allowed!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = PreferenceHelper.customPreference(requireActivity(), CUSTOM_PREF_NAME)
            println(prefs._max.toString())

            if ( prefs._counter == 0){
                arrayList.clear()
            }

            if (prefs._max <  prefs._counter) {
                Toast.makeText(activity, "MAX of patients per session reached", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputName: String = edt_name.text.toString()
            val inputAge: String = edt_age.text.toString()
            val inputEmail: String = edt_email.text.toString()
            val selectedOption: Int = radioGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                radioButton = root.findViewById(selectedOption)
                string = radioButton.text.toString()
            }


            /*-----add to user-----*/
            val user = User(inputName, inputAge.toInt(), inputEmail, string.toString())

            rootObject.put("fullName", user.name)
            rootObject.put("gender", user.gender)
            rootObject.put("age", user.age)

            println("Patient $rootObject")

            val i: Int = arrayList.size + 1
            val s: String = i.toString() + " " + "Pantient" + " " + rootObject;
            arrayList.add(s)

            edt_name.text.clear()
            edt_age.text.clear()
            edt_email.text.clear()

            val adapter =
                ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, arrayList)
            listView.adapter = adapter

            prefs._counter = prefs._counter + 1
        }

        return root
    }
}