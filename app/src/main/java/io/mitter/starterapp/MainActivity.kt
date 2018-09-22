package io.mitter.starterapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.mitter.android.Mitter
import io.mitter.android.error.model.base.ApiError
import io.mitter.data.domain.user.User
import io.mitter.models.acolyte.emptyAclList
import io.mitter.models.mardle.messaging.Message
import io.mitter.starterapp.adapter.ChatRecyclerViewAdapter
import io.mitter.starterapp.util.AclUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var mitter: Mitter
    private val channelId: String = "ccf76043-84bb-42b6-842b-57ce84d9cd06"

    private val messageList = mutableListOf<Message>()
    private lateinit var chatRecyclerViewAdapter: ChatRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mitter = (application as MyApp).mitter
        EventBus.getDefault().register(this)

        val users = mitter.Users()
        val channels = mitter.Channels()
        val messaging = mitter.Messaging()

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        chatRecyclerView.layoutManager = linearLayoutManager

        messaging.getMessagesInChannel(
            channelId = channelId,
            onValueAvailableCallback = object : Mitter.OnValueAvailableCallback<List<Message>> {
                override fun onError(apiError: ApiError) {
                    Log.d("MSA", "Error in getting messages")
                }

                override fun onValueAvailable(value: List<Message>) {
                    messageList.addAll(value.reversed())
                    chatRecyclerViewAdapter = ChatRecyclerViewAdapter(
                        messageList = messageList,
                        currentUserId = mitter.getUserId()
                    )

                    chatRecyclerView?.adapter = chatRecyclerViewAdapter
                }
            }
        )

        sendButton.setOnClickListener {
            var typedInput = inputMessage?.text.toString()
            var appliedAcls = emptyAclList()

            if (typedInput.contains('@')) {
                val username = typedInput.substring(1, typedInput.indexOf(' '))
                typedInput = typedInput.substringAfter(' ')
                appliedAcls = AclUtils.getAclListFromUsername(username, mitter.getUserId())
            }

            messaging.sendTextMessage(
                channelId = channelId,
                message = typedInput,
                appliedAcls = appliedAcls,
                onValueUpdatedCallback = object : Mitter.OnValueUpdatedCallback {
                    override fun onError(apiError: ApiError) {

                    }

                    override fun onSuccess() {
                        inputMessage?.text?.clear()
                    }
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNewMessage(message: Message) {
        messageList.add(message)
        chatRecyclerViewAdapter.notifyItemInserted(messageList.size - 1)
        chatRecyclerView.scrollToPosition(messageList.size - 1)
    }
}
