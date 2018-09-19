package io.mitter.starterapp

import android.app.Application
import io.mitter.android.Mitter
import io.mitter.android.domain.model.MitterConfig
import io.mitter.models.mardle.messaging.*

class MyApp : Application() {
    lateinit var mitter: Mitter
    override fun onCreate() {
        super.onCreate()

        val mitterConfig = MitterConfig(
            applicationId = "c5add92b-c42e-447b-b0e1-24c9e9044dce"
        )

        mitter = Mitter(
            context = this,
            mitterConfig = mitterConfig
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
