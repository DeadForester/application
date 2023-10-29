package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val linkToReg: TextView = findViewById(R.id.link_to_reg)
        val userLogin : EditText = findViewById(R.id.user_login_auth)
        val userPassword : EditText = findViewById(R.id.user_password_auth)
        val button : Button = findViewById(R.id.button_auth)

        linkToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()


            if (login == "" || password == "")
                Toast.makeText(this , "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val p0 = DataBase(this, null)
                val isAuth = p0.getUser(login, password)

                if (isAuth) {
                    Toast.makeText(
                        this,
                        "Пользователь с логином $login АВТОРИЗОВАН",
                        Toast.LENGTH_LONG
                    ).show()

                    userLogin.text.clear()
                    userPassword.text.clear()
                } else
                    Toast.makeText(
                        this,
                        "Пользователь с логином $login НЕ АВТОРИЗОВАН",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
}