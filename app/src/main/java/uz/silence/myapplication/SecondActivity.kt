package uz.silence.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.silence.myapplication.CLASS.Music
import uz.silence.myapplication.Cache.MySharedPreference
import uz.silence.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var bindingS: ActivitySecondBinding
    lateinit var musicList: ArrayList<Music>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingS = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindingS.root)

        MySharedPreference.init(this)

        musicList = MySharedPreference.obyekString

        bindingS.saveId.setOnClickListener {

            if (bindingS.nameEditId.text.trim().isNotEmpty() && bindingS.musicEditId.text.trim()
                    .isNotEmpty()
            ) {

                musicList.add(Music(bindingS.nameEditId.text.toString(), bindingS.musicEditId.text.toString()))
                MySharedPreference.obyekString = musicList

                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                finish()

            } else {
                Toast.makeText(this, "Enter correctly", Toast.LENGTH_SHORT).show()
            }

        }

    }
}