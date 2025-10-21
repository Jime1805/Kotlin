package com.example.actv4_imc.ImcCalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actv4_imc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeigth: Int = 70
    private var currentAge: Int = 30
    private var currentHeight: Int = 120



    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button


    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener {_, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeigth += 1
            setWeight()
        }
        btnSubstractWeight.setOnClickListener {
            currentWeigth -= 1
            setWeight()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeigth / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun setWeight(){
        tvWeight.text = currentWeigth.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        }else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}