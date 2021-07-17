package barrios.abrahan.morehealthy2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import barrios.abrahan.morehealthy2.data.User
import barrios.abrahan.morehealthy2.databinding.ActivitySignupThreeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupThreeActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupThreeBinding
    private lateinit var auth: FirebaseAuth
    private val bd = FirebaseFirestore.getInstance()
    private lateinit var user: User

    private var nombre: String? = null
    private var usuario: String? = null
    private var contrasena: String? = null
    private var genero: String? = null
    private var fechaNacimiento: String? = null
    private var peso: String? = null
    private var altura: String? = null
    private var nivel: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupThreeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val bundle = intent.extras
        if(bundle != null){
            nombre = bundle.getString("name")
            usuario = bundle.getString("username")
            contrasena = bundle.getString("contrasena")
            genero = bundle.getString("genero").toString()
            fechaNacimiento = bundle.getString("fechaNacimiento")
            peso = bundle.getString("peso")
            altura = bundle.getString("altura")
        }

        binding.signupBtn.setOnClickListener{
                when (findViewById<RadioButton>(findViewById<RadioGroup>(R.id.nivel_options).checkedRadioButtonId).text.toString()) {
                    "Sedentario" -> nivel = "0"
                    "Ligera" -> nivel = "1"
                    "Moderada" -> nivel = "2"
                    "Alta" -> nivel = "3"
                }

                user = User(nombre,usuario,genero,fechaNacimiento,peso,altura, nivel)

                bd.collection("users").document(user.username.toString()).set(
                    hashMapOf("nombre" to user.nombre,
                        "genero" to user.genero,
                        "fechaNacimiento" to user.fechaNacimiento,
                        "peso" to user.peso,
                        "altura" to user.altura,
                        "nivel" to user.nivel))
                signupFirebase(usuario.toString(),
                    contrasena.toString())

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = auth.currentUser
        //if(currentUser != null){
        //    reload();
        //}
    }


    private fun signupFirebase(usuario: String, contrasena:String) {
        auth.createUserWithEmailAndPassword(usuario, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")


                    Toast.makeText(baseContext,"Exito!",Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
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