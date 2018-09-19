package io.mitter.starterapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.mitter.models.mardle.messaging.Message
import io.mitter.starterapp.R
import kotlinx.android.synthetic.main.item_message_other.view.*
import kotlinx.android.synthetic.main.item_message_self.view.*

class ChatRecyclerViewAdapter(
    private val messageList: List<Message>,
    private val currentUserId: String
) : RecyclerView.Adapter<ChatRecyclerViewAdapter.ViewHolder>() {
    private val MESSAGE_SELF_VIEW = 0
    private val MESSAGE_OTHER_VIEW = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (viewType == MESSAGE_SELF_VIEW) R.layout.item_message_self else R.layout.item_message_other
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMessage(messageList[position])
    }

    override fun getItemViewType(position: Int) = if (messageList[position].senderId.identifier() == currentUserId)
        MESSAGE_SELF_VIEW else MESSAGE_OTHER_VIEW

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindMessage(message: Message) {
            with(message) {
                if (senderId.identifier() == currentUserId) {
                    itemView?.selfMessageText?.text = textPayload
                } else {
                    itemView?.otherMessageText?.text = textPayload
                }
            }
        }
    }
}
