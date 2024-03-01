package edu.uw.ischool.jorenish.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import java.text.DecimalFormat
import android.widget.EditText
import java.text.NumberFormat
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editamount: EditText
    private lateinit var tipbutton: Button
    private val formatter: NumberFormat = DecimalFormat("$#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editamount = findViewById(R.id.editamount)
        tipbutton = findViewById(R.id.tipbutton)

        tipbutton.setOnClickListener {
            val serviceCharge = editamount.text.replace(Regex("[^\\d.]"), "").toDouble()
            val tipAmount = serviceCharge * 0.15
            val toastMessage = "Tip: ${formatter.format(tipAmount)}"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }

        editamount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                editamount.removeTextChangedListener(this)
                val cleaninput = s.toString().replace(Regex("[^\\d.]"), "")
                tipbutton.isEnabled = false
                if (cleaninput.isNotEmpty() && cleaninput != ".") {
                    val amount = cleaninput.toDouble()
                    val formattedAmount: String = formatter.format(amount)
                    tipbutton.isEnabled = amount > 0
                    editamount.setText(formattedAmount)
                    editamount.setSelection(formattedAmount.length)
                }
                editamount.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}