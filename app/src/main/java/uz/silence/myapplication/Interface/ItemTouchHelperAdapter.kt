package uz.silence.myapplication.Interface


interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)

}