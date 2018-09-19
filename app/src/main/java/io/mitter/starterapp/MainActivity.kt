package io.mitter.starterapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.mitter.android.Mitter
import io.mitter.android.error.model.base.ApiError
import io.mitter.data.domain.user.User

class MainActivity : AppCompatActivity() {

    private lateinit var mitter: Mitter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mitter = (application as MyApp).mitter

        val users = mitter.Users()
        val channels = mitter.Channels()
        val messaging = mitter.Messaging()

        users.getCurrentUser(
            onValueAvailableCallback = object : Mitter.OnValueAvailableCallback<User> {
                override fun onError(apiError: ApiError) {
                    Log.d("MSA", "Error while fetching user: $apiError")
                }

                override fun onValueAvailable(value: User) {
                    Log.d("MSA", "User is: ${value.screenName.screenName}")
                }
            }
        )
    }
}
