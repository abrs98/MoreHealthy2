package barrios.abrahan.morehealthy2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import barrios.abrahan.morehealthy2.data.User
import barrios.abrahan.morehealthy2.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private val bd = FirebaseFirestore.getInstance()
    private var step: Number = 1
    private lateinit var user: User

    private lateinit var nombre: String
    private lateinit var usuario: String
    private lateinit var contrasena: String
    private lateinit var genero: String
    private lateinit var fechaNacimiento: String
    private lateinit var peso: Number
    private lateinit var altura: Number
    private lateinit var nivel: Number



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupOneFragment = SignupOneFragment()
        val signupTwoFragment = SignupTwoFragment()
        val signupThreeFragment = SignupThreeFragment()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,signupOneFragment)
            commit()
        }

        val bundle = intent.extras
        if(bundle != null){
            val nombre = bundle.getString("name")
            val correo = bundle.getString("email")

            findViewById<EditText>(R.id.nombre_textfield).setText(nombre)
            findViewById<EditText>(R.id.username_textfield).setText(correo)
        }

        binding.signupBtn.setOnClickListener{
            if (step == 1){
                usuario = findViewById<EditText>(R.id.username_textfield).text.toString()
                nombre = findViewById<EditText>(R.id.nombre_textfield).text.toString()
                contrasena = findViewById<EditText>(R.id.contrasena_textfield).text.toString()


                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,signupTwoFragment)
                    commit()
                }
            }else if (step == 2){
                genero = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.genero_options).checkedRadioButtonId).text.toString()
                peso = findViewById<EditText>(R.id.peso_textfield).text.toString().toInt()
                altura = findViewById<EditText>(R.id.altura_textfield).text.toString().toDouble()

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,signupThreeFragment)
                    commit()
                }
            }else if (step == 3){
                when (findViewById<RadioButton>(findViewById<RadioGroup>(R.id.nivel_options).checkedRadioButtonId).text.toString()) {
                    "Sedentario" -> nivel = 0
                    "Ligera" -> nivel = 1
                    "Moderada" -> nivel = 2
                    "Alta" -> nivel = 3
                }

                user = User(nombre,usuario,genero,fechaNacimiento,peso,altura, nivel)

                bd.collection("users").document(user.username).set(
                    hashMapOf("nombre" to user.nombre,
                        "genero" to user.genero,
                        "fechaNacimiento" to user.fechaNacimiento,
                        "peso" to user.peso,
                        "altura" to user.altura,
                        "nivel" to user.nivel))
                signupFirebase(findViewById<EditText>(R.id.username_textfield).text.toString(),
                    findViewById<EditText>(R.id.contrasena_textfield).text.toString())

            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {
        finish();
        startActivity(getIntent());
    }

    private fun signupFirebase(usuario: String, contrasena:String) {
        auth.createUserWithEmailAndPassword(usuario, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }


}