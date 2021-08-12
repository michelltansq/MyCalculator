package sg.edu.rp.webservices.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    var number: Boolean = false
    var error: Boolean = false
    var decimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        if (error) {
            tvResult.text = (view as Button).text
        } else {
            tvResult.append((view as Button).text)
        }
        number = true
    }

    fun onDecimal(view: View) {
        if (number && !error && !decimal) {
            tvResult.append(".")
            decimal = true
        }
    }

    fun onOperator(view: View) {
        if (number && !error) {
            tvResult.append((view as Button).text)
            number = false
            decimal = false
        }
    }

    fun onAC(view: View) {
        tvResult.text = ""
    }

    fun onResult(view: View) {
        if (number && !error) {
            val result = tvResult.text.toString()
            val expression = ExpressionBuilder(result).build()
            tvResult.text = expression.evaluate().toString()
            decimal = true
        }
    }
}