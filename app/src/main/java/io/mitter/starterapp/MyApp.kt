package io.mitter.starterapp

import android.app.Application
import android.util.Log
import io.mitter.android.Mitter
import io.mitter.android.domain.model.MitterConfig
import io.mitter.android.domain.model.UserAuth
import io.mitter.models.mardle.messaging.*
import org.greenrobot.eventbus.EventBus

class MyApp : Application() {
    lateinit var mitter: Mitter
    override fun onCreate() {
        super.onCreate()

        val mitterConfig = MitterConfig(
            applicationId = "c5add92b-c42e-447b-b0e1-24c9e9044dce"
        )

        val jasonAuth = UserAuth(
            userId = "8ed92f3c-0696-4513-a842-085e3cee589e",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6Imw5VW1DUUcyWDJYbzM2enAiLCJ1c2VydG9rZW4iOiI0ZzNkcWIxMTk0NDZvYXQ2cmdpcTg2aTF1YSJ9.4Ibj24NlkdtPjEH8Vi1nlGAqSJCgQy3RWqNyqXgOKQHR7gT-cd9ZH_b68o3b3Gk5qyG_-eqC8pMD8F0JdpzwBg"
        )

        val katieAuth = UserAuth(
            userId = "cb02bc00-979e-4db2-8625-116178c4ad95",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6IndjdlNZcHVoR3Jmdnc4MHEiLCJ1c2VydG9rZW4iOiJyY2Vvbmw2NGdlZW40cTRwaXI5bXNuMW5wciJ9.NEIQtpbyNMyYErVaO_16KKPDhDHXFtXG_UlQIvgSGSQB8zU3790aIc-8UqtgR0TJu73mWnLlZfBLS3XES_h9rg"
        )

        mitter = Mitter(
            context = this,
            mitterConfig = mitterConfig,
            userAuth = katieAuth
        )

        mitter.registerOnPushMessageReceivedListener(object : Mitter.OnPushMessageReceivedCallback {
            override fun onChannelStreamData(channelId: String, streamId: String, streamData: ContextFreeMessage) {

            }

            override fun onNewChannel(channel: Channel) {

            }

            override fun onNewChannelTimelineEvent(channelId: String, timelineEvent: TimelineEvent) {

            }

            override fun onNewMessage(channelId: String, message: Message) {
                EventBus.getDefault().post(message)
            }

            override fun onNewMessageTimelineEvent(messageId: String, timelineEvent: TimelineEvent) {

            }

            override fun onParticipationChangedEvent(channelId: String, participantId: String, newStatus: ParticipationStatus, oldStatus: ParticipationStatus?) {

            }
        })
    }
}
