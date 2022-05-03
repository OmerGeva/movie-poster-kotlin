package com.example.movieposter

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var theatre: String = "Initial Value"
    var ticketNumber: Int = 0
    var price: Int = 0
    val ticketPrice = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val slideInAnim = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_in
        )
        val clipart = findViewById<ImageView>(R.id.movie_clipart)
        clipart.setOnClickListener () {
            clipart.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
        }
        val description = findViewById<TextView>(R.id.descriptionText)
        val checkOutBtn = findViewById<Button>(R.id.showTicketBtn)
        val img = findViewById<ImageView>(R.id.indianajonesimage)
        img.startAnimation(slideInAnim)
        rebuildBuilder()
    }

    fun getTicketPrice(tickets: Int): Int {
        return tickets * 5
    }

    fun getTheatreName(): String {
        return this.theatre
    }

    fun rebuildBuilder() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val numberPicker = findViewById<NumberPicker>(R.id.number_picker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);

        val ticketBtn = findViewById<Button>(R.id.showTicketBtn)

        ticketBtn.setOnClickListener {
            val numberPicker = findViewById<NumberPicker>(R.id.number_picker);
            numberPicker.setMaxValue(10);
            numberPicker.setMinValue(0);

            builder.apply {
                setTitle("Ticket information")
                setMessage("Your selected theatre is: ${getTheatreName()}, you ordered ${numberPicker.value} tickets, and your price is  $${numberPicker.value * 20}")
                setPositiveButton("Got it.") { p0, p1->
                }
            }
            val dialog = builder.create()

            dialog.show()
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_tlv ->
                    if (checked) {
                        this.theatre = "Tel Aviv Cinema"
                    }
                R.id.radio_herzelia ->
                    if (checked) {
                        this.theatre = "Herzelia Cinema"
                    }
            }
        }
        rebuildBuilder()
    }
}