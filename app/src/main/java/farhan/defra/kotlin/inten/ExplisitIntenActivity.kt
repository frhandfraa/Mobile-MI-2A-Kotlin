package farhan.defra.kotlin.inten

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R

class ExplisitIntenActivity : AppCompatActivity() {
    private lateinit var btnProses: Button
    private lateinit var etNIDN: EditText
    private lateinit var etNama: EditText
    private lateinit var etUmur: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explisit_inten)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnProses = findViewById(R.id.btnproses)
        etNIDN = findViewById(R.id.etNIDN)
        etNama = findViewById(R.id.etNama)
        etUmur= findViewById(R.id.etUmur)
      }

    override fun onStart() {
        super.onStart()
        proses()
    }
    fun proses(){
        btnProses.setOnClickListener {
            //Pindah Activity
            //startActivity(Intent(this, HasilActivity::class.java))
            //or

            btnProses.setOnClickListener {
                val nidn = etNIDN.text.toString()
                val nama = etNama.text.toString()
                val umur = etUmur.text.toString().toIntOrNull() ?: 0

                val bundle = Bundle()

                val intent = Intent(this, HasilActivity::class.java)
                bundle.putString("pNIDN", nidn)
                bundle.putString("pNama", nama)
                bundle.putInt("pUmur", umur)
                intent.putExtras(bundle)
                startActivity(intent)
            }

        }
    }
}