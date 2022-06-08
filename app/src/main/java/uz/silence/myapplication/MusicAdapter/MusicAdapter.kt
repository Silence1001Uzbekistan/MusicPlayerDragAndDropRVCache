package uz.silence.myapplication.MusicAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.silence.myapplication.CLASS.Music
import uz.silence.myapplication.Interface.ItemTouchHelperAdapter
import uz.silence.myapplication.R
import java.util.*

class MusicAdapter(var context: Context, var musicList: ArrayList<Music>) :
    RecyclerView.Adapter<MusicAdapter.Vh>(), ItemTouchHelperAdapter {

    inner class Vh(var itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(music: Music) {

            itemView.findViewById<TextView>(R.id.nameId).text = music.name
            itemView.findViewById<TextView>(R.id.musicId).text = music.music

            itemView.animation = AnimationUtils.loadAnimation(context,R.anim.my_anim)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(musicList[position])

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {

            for (i in fromPosition until toPosition) {
                Collections.swap(musicList, i, i + 1)
            }

        } else {

            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(musicList, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)

    }

    override fun onItemDismiss(position: Int) {

        musicList.removeAt(position)
        notifyItemRemoved(position)

    }

}