package io.mitter.starterapp.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.mitter.android.Mitter
import io.mitter.android.error.model.base.ApiError
import io.mitter.models.delman.DeliveryEndpoint
import io.mitter.starterapp.MyApp

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        val mitter = (application as MyApp).mitter

        token?.let {
            mitter.registerFcmToken(
                token = it,
                onValueAvailableCallback = object : Mitter.OnValueAvailableCallback<DeliveryEndpoint> {
                    override fun onError(apiError: ApiError) {
                        Log.d("MSA", "Error registering token")
                    }

                    override fun onValueAvailable(value: DeliveryEndpoint) {
                        Log.d("MSA", "Registered delivery endpoint: $value")
                    }
                }
            )
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        remoteMessage?.let {
            if (it.data.isNotEmpty()) {
                val mitter = (application as MyApp).mitter
                val messagingPipelinePayload = mitter.parseFcmMessage(it.data)

                if (mitter.isMitterMessage(messagingPipelinePayload)) {
                    mitter.processPushMessage(messagingPipelinePayload)
                }
            }
        }
    }
}
