package gap.com.testapp1.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import gap.com.testapp1.R
import gap.com.testapp1.utils.PreferenceHelper
import gap.com.testapp1.utils.PreferenceHelper._counter
import gap.com.testapp1.utils.PreferenceHelper._max
import gap.com.testapp1.utils.PreferenceHelper.numberCurrent
import gap.com.testapp1.utils.PreferenceHelper.usernameCurrent

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_setting), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*-----show Toast-----*/
        val prefs = PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
        if (prefs.usernameCurrent!= null){
            Toast.makeText(applicationContext,"Hi " + prefs.usernameCurrent, Toast.LENGTH_LONG).show()
        }

        /*-----action Share-----*/
        navView.menu.findItem(R.id.nav_share).setOnMenuItemClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share your result with your friend"))
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*-----reset-----*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {

                val prefs = PreferenceHelper.customPreference(this, CUSTOM_PREF_NAME)
                prefs.usernameCurrent = ""
                prefs.numberCurrent = 0
                prefs._max = 0
                prefs._max = 0
                prefs._counter = 0
                Toast.makeText(applicationContext, "Data Cleared", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}