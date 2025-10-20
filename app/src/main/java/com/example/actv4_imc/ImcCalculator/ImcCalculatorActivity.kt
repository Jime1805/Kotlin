package com.example.actv4_imc.ImcCalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actv4_imc.R

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListeners()
    }

    private fun initComponent(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
    }
    private fun initListeners() {
        viewMale.setOnClickListener {setGenderColor()}
        viewFemale.setOnClickListener {setGenderColor()}

    }
    private fun setGenderColor(){
        val maleColor = if (isMaleSelected) {
            R.color.background_component_selected}
        else {
            R.color.background_component
        }

        viewMale.setCardBackgroundColor(maleColor)
        viewFemale.setCardBackgroundColor()
    }
}