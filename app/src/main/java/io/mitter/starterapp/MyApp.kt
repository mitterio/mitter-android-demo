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
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6IlJkZHZCNXJ5RGdPNUpvSkoiLCJ1c2VydG9rZW4iOiI5cXA1MjQyY2N1NW9lbHI3OTRzc2ltbDY3OCJ9.aonmZhZWCyIHJR6nxlNn_KSgAvdWlB4vtZgfdXbvlIvXBM5oNaUzpF3YbfAlZeyPr8_uMf8HCcoh4dVFr-lYFw"
        )

        val katieAuth = UserAuth(
            userId = "cb02bc00-979e-4db2-8625-116178c4ad95",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6Ilg2ZXZKZDVHSmx1SU5URWEiLCJ1c2VydG9rZW4iOiJwdnA2MGFwa3NpYjgzY21yOGI1N2g0YmhpYSJ9.hVw0h3hmtOQlx9phWrJ7zK9an9GmXv9H481OjrbIPOmE58g86EqozHLhASwl0jdiqC5KjU1_nrIK40pmNEvY9w"
        )

        val samAuth = UserAuth(
            userId = "5cfe3da1-4467-49b8-8325-3e85cec31c5a",
            userAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtaXR0ZXItaW8iLCJ1c2VyVG9rZW5JZCI6IlhoSnV5dGw0OG5OSlBJVDYiLCJ1c2VydG9rZW4iOiIyamozaTdhNTA5Yzc3c2Vva2dscGdiZjBpNiJ9.BwPGV2kTdclHdRaIzqjR6yAascqvYE52tH2iLK2aDBaBZA841SM0gW0WdblxLYeAyyM-XKXIEHwM2K0xQwoh7w"
        )

        mitter = Mitter(
            context = this,
            mitterConfig = mitterConfig,
            userAuth = samAuth
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
