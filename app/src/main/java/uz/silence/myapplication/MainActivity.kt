package uz.silence.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.silence.myapplication.CLASS.Music
import uz.silence.myapplication.Cache.MySharedPreference
import uz.silence.myapplication.MusicAdapter.MusicAdapter
import uz.silence.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var userList: ArrayList<Music>
    lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nextId.setOnClickListener {

            startActivity(Intent(this, SecondActivity::class.java))

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {


        MySharedPreference.init(this)

        userList = MySharedPreference.obyekString

        musicAdapter = MusicAdapter(this, userList)
        binding.rv.adapter = musicAdapter

        var itemTouchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {

                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

                return makeMovementFlags(dragFlags, swipeFlags)

            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {


                musicAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)

                return true


            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                musicAdapter.onItemDismiss(viewHolder.adapterPosition)
                Toast.makeText(this@MainActivity, "${viewHolder.layoutPosition}", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "${viewHolder.isRecyclable}", Toast.LENGTH_SHORT).show()

                musicAdapter.notifyItemRemoved(viewHolder.layoutPosition)
                musicAdapter.notifyItemChanged(viewHolder.layoutPosition)

                MySharedPreference.obyekString = userList

            }
        }

        var itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rv)


        musicAdapter.notifyDataSetChanged()
        super.onStart()
    }

}