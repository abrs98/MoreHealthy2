package barrios.abrahan.morehealthy2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import barrios.abrahan.morehealthy2.databinding.ActivitySignupOneBinding

class SignupOneActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySignupOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if(bundle != null){
            val nombre = bundle.getString("name")
            val correo = bundle.getString("email")

            findViewById<EditText>(R.id.nombre_textfield).setText(nombre)
            findViewById<EditText>(R.id.username_textfield).setText(correo)
        }

        binding.signupBtn.setOnClickListener{
            val intent = Intent(this, SignupTwoActivity::class.java)
            intent.putExtra("username", binding.usernameTextfield.text.toString())
            intent.putExtra("nombre", binding.nombreTextfield.text.toString())
            intent.putExtra("contrasena", binding.contrasenaTextfield.text.toString())
            startActivity(intent)
        }
    }
}