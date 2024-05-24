package com.example.myproject.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.myproject.models.User
import com.example.myproject.navigation.HOME_URL
import com.example.myproject.navigation.LOGIN_URL
import com.example.myproject.navigation.SIGNUP_URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController:NavHostController, var context: Context){
    val mAuth:FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    fun signup(name:String, email:String, password:String,confpassword:String){
        progress.show()

        if (email.isBlank() || password.isBlank() ||confpassword.isBlank()){
            progress.dismiss()
            Toast.makeText(context,"Please email and password cannot be blank",Toast.LENGTH_LONG).show()
        }else if (password != confpassword){
            Toast.makeText(context,"Password do not match",Toast.LENGTH_LONG).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    val userdata=User(name,email,password,mAuth.currentUser!!.uid)
                    val regRef=FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show()
                            navController.navigate(LOGIN_URL)

                        }else{
                            Toast.makeText(context,"${it.exception!!.message}",Toast.LENGTH_LONG).show()
                            navController.navigate(SIGNUP_URL)
                        }
                    }
                }else{
                    navController.navigate(SIGNUP_URL)
                }

            } }

    }

    fun login(email: String, password: String){
        progress.show()

        if (email.isBlank() || password.isBlank()){
            progress.dismiss()
            Toast.makeText(context,"Please email and password cannot be blank",Toast.LENGTH_LONG).show()
        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(HOME_URL)
                }else{
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun logout(){
        mAuth.signOut()
        navController.navigate(LOGIN_URL)
    }

    fun isLoggedIn(): Boolean = mAuth.currentUser != null

}