package farhan.defra.kotlin.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R
import java.text.SimpleDateFormat
import java.util.*

class FormPegawaiActivity : AppCompatActivity() {
    private lateinit var etNIP: EditText
    private lateinit var etNama: EditText
    private lateinit var etTempatLahir: EditText
    private lateinit var tvTglLahir: TextView
    private lateinit var etJam: TextView
    private lateinit var etUmur: EditText
    private lateinit var etNIK: EditText
    private lateinit var etKK: EditText
    private lateinit var btnKirim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_pegawai)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Deklarasi view
        etNIP = findViewById(R.id.etNIP)
        etNama = findViewById(R.id.etNama)
        etTempatLahir = findViewById(R.id.etTempatLahir)
        tvTglLahir = findViewById(R.id.tvTglLahir)
        etJam = findViewById(R.id.etJam)
        etUmur = findViewById(R.id.etUmur)
        etNIK = findViewById(R.id.etNIK)
        etKK = findViewById(R.id.etKK)
        btnKirim = findViewById(R.id.btnKirim)

        // DatePicker
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val formatIndo = "dd-MMMM-yyyy"
            val sdf = SimpleDateFormat(formatIndo, Locale("id", "ID"))
            tvTglLahir.text = sdf.format(myCalendar.time)
        }

        tvTglLahir.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // TimePicker
        val currentTime = Calendar.getInstance()
        val timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentTime.set(Calendar.MINUTE, minute)
            val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
            etJam.text = formatTime.format(currentTime.time)
        }

        etJam.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }

        // Ambil data dari activity lain (jika ada)
      //  getData()

        // Kirim data ke activity lain
        kirim()
    }

    private fun getData() {
        val bundle = intent.extras
        if (bundle != null) {
            etNIP.setText(bundle.getString("pNIP"))
            etNama.setText(bundle.getString("pNama"))
            etTempatLahir.setText(bundle.getString("pTempatLahir"))
            tvTglLahir.text = bundle.getString("pTglLahir")
            etJam.text = bundle.getString("pJam")
            etUmur.setText(bundle.getString("pUmur"))
            etNIK.setText(bundle.getString("pNIK"))
            etKK.setText(bundle.getString("pKK"))
        }
    }

    private fun kirim() {
        btnKirim.setOnClickListener {
            val nip = etNIP.text.toString()
            val nama = etNama.text.toString()
            val tempatLahir = etTempatLahir.text.toString()
            val tglLahir = tvTglLahir.text.toString()
            val jam = etJam.text.toString()
            val umur = etUmur.text.toString()
            val nik = etNIK.text.toString()
            val kk = etKK.text.toString()

            val bundle = Bundle()
            bundle.putString("pNIP", nip)
            bundle.putString("pNama", nama)
            bundle.putString("pTempatLahir", tempatLahir)
            bundle.putString("pTglLahir", tglLahir)
            bundle.putString("pJam", jam)
            bundle.putString("pUmur", umur)
            bundle.putString("pNIK", nik)
            bundle.putString("pKK", kk)

            // Ganti TargetActivity dengan activity tujuan
            val intent = Intent(this, HasilFormPegawaiActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}
