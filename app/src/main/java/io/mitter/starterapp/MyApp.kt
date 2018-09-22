package io.mitter.starterapp

import android.app.Application
import android.util.Log
import io.mitter.android.Mitter
import io.mitter.android.domain.model.LoggingLevel
import io.mitter.android.domain.model.MitterConfig
import io.mitter.android.domain.model.UserAuth
import io.mitter.models.mardle.messaging.*
import org.greenrobot.eventbus.EventBus

class MyApp : Application() {
    lateinit var mitter: Mitter
    override fun onCreate() {
        super.onCreate()

        val mitterConfig = MitterConfig(
            applicationId = "c5add92b-c42e-447b-b0e1-24c9e9044dce",
            loggingLevel = LoggingLevel.FULL
        )

        val jasonAuth = UserAuth(
            userId = "8ed92f3c-0696-4513-a842-085e3cee589e",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6Inpldlo3dzR6ZzZ3ZTcyZDkiLCJ1c2VydG9rZW4iOiJkcTgwMGVybWk0NnRrZGdpc25tdHBzcDVpZiJ9.vBEl0YwGfym8UwE0IkrfdKgKOs3yV56dgqkdnrcupE9nhP1xBSXXCpLiHoG0oVlGK9SF4PFijbMtBnnZ2S6GxQ"
        )

        val katieAuth = UserAuth(
            userId = "cb02bc00-979e-4db2-8625-116178c4ad95",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6Ik9DbmlHdG5Ld2lrdnp2aDgiLCJ1c2VydG9rZW4iOiIyN3FnMnM4dTJ1dmpxaG90aHZyaXRvZG03bSJ9.hCriXojV2jCMVhP4tgPO5sFzyH-PxP34A5j-uqcw2JZfTAPv3Ru8KTJV9IzRCGGjAN2LbO9mMyfcsuFGDho2_Q"
        )

        val samAuth = UserAuth(
            userId = "5cfe3da1-4467-49b8-8325-3e85cec31c5a",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6IlFVZ3lvekJWTlBDN2dsbUciLCJ1c2VydG9rZW4iOiJqNnJzbjFlbjkyZmg2YXYwODFranQ2dmhjOSJ9.1NdurHBucCIcJoJt9LFxkLZdgPIdkn_9xQgugyro7if8YIwxBjoOXohQTnXlnhn1Qh-6HvsXaphL-FJukHJdNQ"
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
