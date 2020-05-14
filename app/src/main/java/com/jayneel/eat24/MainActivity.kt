package com.jayneel.eat24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    //add_animation
    //variables




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        //animation
        //var topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //var bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //hooks
       // var image= findViewById<R.id.>()
        //var logo= findViewById(R.id.textView);
        //var slogan= findViewById(R.id.textView2);

        //image.setAnimation(topAnim);
        //logo.setAnimation(bottomAnim);
        //slogan.setAnimation(bottomAnim);


        Handler().postDelayed({
            startActivity(Intent(this,login::class.java))
            finish()
        },3000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
