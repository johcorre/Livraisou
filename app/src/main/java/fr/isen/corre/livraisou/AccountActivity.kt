package fr.isen.corre.livraisou

import android.app.Dialog
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.Window
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import fr.isen.corre.livraisou.databinding.ActivityAccountBinding
import java.io.File
import android.R
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


class AccountActivity : AppCompatActivity() {

    val TAG = "AccountActivity"
    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var dialog : Dialog
    private lateinit var uid :String
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate( layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        binding.btnSave.setOnClickListener {



            val user = Firebase.auth.currentUser
            val database = Firebase.database

            user?.let {
                showProgressBar()
                //val photoUrl = user.photoUrl
                // Check if user's email is verified
                //val emailVerified = user.isEmailVerified
                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getToken() instead.
                val uid = user.uid
                val userRef = database.getReference(uid)
                // Read from the database
                userRef.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val firstName = snapshot.child("surname").value
                        val lastName = snapshot.child("name").value
                        val phoneNumber = snapshot.child("phoneNum").value
                        val photoURL= snapshot.child("profilPicURL").value
                        val location = snapshot.child("location").value

                        binding.userFirstName.setText(firstName.toString())
                        binding.userFullName.setText(lastName.toString())
                       // binding.userFullName.setText(firstName.add.lastName.toString())
                        binding.userPhoneNumber.setText(phoneNumber.toString())
                        binding.profilPic.setImageDrawable(photoURL as Drawable?)
                        binding.userLocation.setText(location.toString())
                        binding.userEmail.setText(it.email.toString())
                        getUserProfile()
                       // if(photoURL?.isNotEmpty() == true) {
                       //     Picasso.get().load(photoURL).placeholder(R.drawable.good_food).into(binding.photo)
                       // }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        hideProgressBar()
                        Log.w(TAG, "Failed to read value.", error.toException())
                        Toast.makeText( this@AccountActivity,"Failed to get Profile Data",Toast.LENGTH_SHORT).show()


                    }

                })

            }

            if (user != null) {
                // User is signed in
             //   databaseReference.child(uid).setValue(user).addOnCompleteListener {
             //       if (it.isSuccessful) {
             //           uploadProfile()
               //     } else {
               //         hideProgressBar()
                //        Toast.makeText(
                //            this@AccountActivity,
                //            "Failed to update profile",
               //             Toast.LENGTH_SHORT
              //          ).show()
              //      }
             //   }


                //send email verif
               // user!!.sendEmailVerification()
               //     .addOnCompleteListener { task ->
               //         if (task.isSuccessful) {
              //              Log.d(TAG, "Email sent.")
               //         }
              //      }
                //update password
                // user!!.updatePassword(userNewPassword)
                //   .addOnCompleteListener { task ->
                //       if (task.isSuccessful) {
                //          Log.d(TAG, "User password updated.")
                //       }
                //   }

                //send an email to reset password !!!!Mot de passe oublié
                // Firebase.auth.sendPasswordResetEmail(email)
                //     .addOnCompleteListener { task ->
                //         if (task.isSuccessful) {
                //            Log.d(TAG, "Email sent.")
                //       }
                //   }
                uploadProfile()
            } else {
                // No user is signed in
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun uploadProfile(){
        imageUri=Uri.parse("android.resource://$packageName/${R.drawable.profile}")
        storageReference= FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            hideProgressBar()
            Toast.makeText(this@AccountActivity, "Profile succesfuly updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText(this@AccountActivity, "Failed to update the image",Toast.LENGTH_SHORT).show()
        }

    }

    private fun getUserProfile(){

        storageReference= FirebaseStorage.getInstance().getReference("Users/$uid.jpg")
        val localFile = File.createTempFile("tempImage","jpg")
        storageReference.getFile(localFile).addOnSuccessListener{
           // val bitmap= BitmapFactory.decodeFile(localFile.absolutePath)
           // binding.profilPic.setImageBitmap(bitmap)
            hideProgressBar()
        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText( this@AccountActivity,"Failed to retrieve image",Toast.LENGTH_SHORT).show()
        }

    }
    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
       

    }
    private fun showProgressBar(){
        dialog = Dialog(this@AccountActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    private fun hideProgressBar(){
        dialog.dismiss()
    }
}





