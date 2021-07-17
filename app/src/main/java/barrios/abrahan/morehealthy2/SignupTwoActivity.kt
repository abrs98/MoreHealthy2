package barrios.abrahan.morehealthy2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import barrios.abrahan.morehealthy2.databinding.ActivitySignupTwoBinding

class SignupTwoActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySignupTwoBinding

    private var nombre: String? = null
    private var correo: String? = null
    private var contrasena: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if(bundle != null){
            nombre = bundle.getString("nombre")
            correo = bundle.getString("username")
            contrasena = bundle.getString("contrasena")

        }

        binding.signupBtn.setOnClickListener{
            val intent = Intent(this, SignupThreeActivity::class.java)
            intent.putExtra("genero", findViewById<RadioButton>(binding.generoOptions.checkedRadioButtonId).text.toString())
            intent.putExtra("fechaNacimiento", binding.fechanacimientoTextfield.text.toString())
            intent.putExtra("peso", binding.pesoTextfield.text.toString())
            intent.putExtra("altura", binding.alturaTextfield.text.toString())
            intent.putExtra("name", nombre)
            intent.putExtra("contrasena", contrasena)
            intent.putExtra("username", correo)

            startActivity(intent)
        }
    }
}