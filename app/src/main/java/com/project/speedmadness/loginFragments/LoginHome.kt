package com.project.speedmadness.loginFragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.project.speedmadness.R

class LoginHome : Fragment() {
    // TODO: Rename and change types of parameters

    interface FragmentChequearSiHaySession {
        fun chequearSession():Boolean
    }

    private val requestNum = 100
    private lateinit var viewFragment:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        viewFragment = view

        val buttonIngresar: Button = view.findViewById(R.id.buttonIngresarLoginHome)
        buttonIngresar.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_loginHome_to_loginIngresar) })

        val buttonRegistrar: Button = view.findViewById(R.id.buttonRegistrarLoginHome)
        buttonRegistrar.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_loginHome_to_loginRegistrar) })

        val prefs: SharedPreferences? = activity?.getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email:String? = prefs?.getString("email", null)
        val provider:String? = prefs?.getString("provider", null)
        if(yaHaySessionActiva(email, provider)){
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            val contra:String? = prefs?.getString("credencial", null)
            if (email != null && contra != null) {
                auth.signInWithEmailAndPassword(email, contra)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {

                        } else Toast.makeText(activity,"No se pudo auntentificar el usuario",Toast.LENGTH_LONG)
                    }
            }
            navController.navigate(R.id.action_loginHome_to_homePage)
        }
    }


    private fun ingresarAHome(){
        val navController = Navigation.findNavController(viewFragment)
        val buttonIngresar: Button = viewFragment.findViewById(R.id.buttonIngresarLoginIngresar)
        buttonIngresar.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_loginIngresar_to_homePage) })

    }


    private fun yaHaySessionActiva(email:String?, provider:String?):Boolean{
        return !email.isNullOrEmpty()&&!provider.isNullOrEmpty()
    }
}