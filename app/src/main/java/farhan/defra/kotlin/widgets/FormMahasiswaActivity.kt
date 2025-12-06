package farhan.defra.kotlin.widgets

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import farhan.defra.kotlin.R

class FormMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_mahasiswa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tambahkan Variabel
        val etNIM = findViewById<EditText>(R.id.etNIM)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etHandphone = findViewById<EditText>(R.id.etHandphone)
        val spJurusan = findViewById<Spinner>(R.id.spJurusan)
        val spProdi = findViewById<Spinner>(R.id.spProdi)
        val rgJk = findViewById<RadioGroup>(R.id.rgJK)
        val chkBaca = findViewById<CheckBox>(R.id.chkBaca)
        val chkTravelling = findViewById<CheckBox>(R.id.chkTravelling)
        val chkCoding = findViewById<CheckBox>(R.id.chkCoding)
        val tvTglLahir = findViewById<TextView>(R.id.tvTglLahir)
        val etJam = findViewById<TextView>(R.id.etJam)
        val btnKirim = findViewById<Button>(R.id.btnKirim)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // Data Spinner
        val listJurusan = arrayOf(
            "Teknologi Informasi",
            "Teknik Sipil",
            "Teknik Mesin",
            "Teknik Elektro",
            "Administrasi Niaga",
            "Bahasa Inggris",
            "Akuntansi"
        )
        val listProdi = arrayOf(
            "D3 Manajemen Informatika",
            "D3 Teknik Komputer",
            "D3 Sistem Informasi",
            "D4 Animasi",
            "D4 Rekayasa Perangkat Lunak"
        )

        // Adapter Spinner
        val adapterJurusan = ArrayAdapter(this, android.R.layout.simple_spinner_item, listJurusan)
        adapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spJurusan.adapter = adapterJurusan

        val adapterProdi = ArrayAdapter(this, android.R.layout.simple_spinner_item, listProdi)
        adapterProdi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProdi.adapter = adapterProdi

        // Event ketika button di klik
        btnKirim.setOnClickListener {
            // Ambil radio button terpilih
            val selectedGenderId = rgJk.checkedRadioButtonId
            val selectedGender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Belum dipilih"
            }

            // Ambil hobi
            val hobi = mutableListOf<String>()
            if (chkBaca.isChecked) hobi.add("Membaca")
            if (chkTravelling.isChecked) hobi.add("Travelling")
            if (chkCoding.isChecked) hobi.add("Coding")

            val myCalendar = Calendar.getInstance()

            val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val formatIndo = "dd-MMMM-yyyy"
                val stringFormat = SimpleDateFormat(formatIndo, Locale("id", "ID"))
                tvTglLahir.text = stringFormat.format(myCalendar.time)
            }

// tampilkan kalender saat TextView diklik
            tvTglLahir.setOnClickListener {
                DatePickerDialog(
                    this,
                    datePicker,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

// Time Picker
            val currentTime = Calendar.getInstance()
            val timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                currentTime.set(Calendar.MINUTE, minute)

                val formatTime = SimpleDateFormat("HH:mm", Locale("id", "ID"))
                etJam.text = formatTime.format(currentTime.time)
            }

// tampilkan time picker saat TextView diklik
            etJam.setOnClickListener {
                TimePickerDialog(
                    this,
                    timePicker,
                    currentTime.get(Calendar.HOUR_OF_DAY),
                    currentTime.get(Calendar.MINUTE),
                    true
                ).show()
            }


            // Tampilkan hasil
            tvHasil.text = """
                NIM : ${etNIM.text}
                Nama : ${etNama.text}
                Email : ${etEmail.text}
                No HP : ${etHandphone.text}
                Jurusan : ${spJurusan.selectedItem}
                Prodi : ${spProdi.selectedItem}
                Jenis Kelamin : $selectedGender
                Hobi : ${if (hobi.isEmpty()) "Tidak ada" else hobi.joinToString(", ")}
            """.trimIndent()
        }
    }
}
