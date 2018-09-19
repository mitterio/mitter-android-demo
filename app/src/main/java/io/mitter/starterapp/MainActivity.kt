package io.mitter.starterapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.mitter.android.Mitter
import io.mitter.android.error.model.base.ApiError
import io.mitter.data.domain.user.User
import io.mitter.models.mardle.messaging.Message
import io.mitter.starterapp.adapter.ChatRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mitter: Mitter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mitter = (application as MyApp).mitter

        val users = mitter.Users()
        val channels = mitter.Channels()
        val messaging = mitter.Messaging()

        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        messaging.getMessagesInChannel(
            channelId = "6dedfac5-8060-4ad3-8d69-20a72fb86899",
            onValueAvailableCallback = object : Mitter.OnValueAvailableCallback<List<Message>> {
                override fun onError(apiError: ApiError) {
                    Log.d("MSA", "Error in getting messages")
                }

                override fun onValueAvailable(value: List<Message>) {
                    chatRecyclerView.adapter = ChatRecyclerViewAdapter(
                        messageList = value,
                        currentUserId = mitter.getUserId()
                    )
                }
            }
        )
    }
}
