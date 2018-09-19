package io.mitter.starterapp

import android.app.Application
import io.mitter.android.Mitter
import io.mitter.android.domain.model.MitterConfig
import io.mitter.android.domain.model.UserAuth
import io.mitter.models.mardle.messaging.*

class MyApp : Application() {
    lateinit var mitter: Mitter
    override fun onCreate() {
        super.onCreate()

        val mitterConfig = MitterConfig(
            applicationId = "c5add92b-c42e-447b-b0e1-24c9e9044dce"
        )

        val userAuth = UserAuth(
            userId = "8ed92f3c-0696-4513-a842-085e3cee589e",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6ImJtRlI5bWNQaDhkQnJHaWIiLCJ1c2VydG9rZW4iOiJiM2dvY2ZhZ3ZyNWlrNHJkbXJlc29wNnNlcyJ9.dlE1QOYmUJpqoh1kORm3hEI3KbBM0v8kKZQnQQwXR6TZuFiCaDQrJMlp-2dgNP1CTYCPMFYoqGctRWQ5JyNiOQ"
        )

        mitter = Mitter(
            context = this,
            mitterConfig = mitterConfig,
            userAuth = userAuth
        )

        mitter.registerOnPushMessageReceivedListener(object : Mitter.OnPushMessageReceivedCallback {
            override fun onChannelStreamData(channelId: String, streamId: String, streamData: ContextFreeMessage) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNewChannel(channel: Channel) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNewChannelTimelineEvent(channelId: String, timelineEvent: TimelineEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNewMessage(channelId: String, message: Message) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNewMessageTimelineEvent(messageId: String, timelineEvent: TimelineEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onParticipationChangedEvent(channelId: String, participantId: String, newStatus: ParticipationStatus, oldStatus: ParticipationStatus?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
