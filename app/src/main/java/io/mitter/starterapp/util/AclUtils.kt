package io.mitter.starterapp.util

import io.mitter.android.Mitter
import io.mitter.data.domain.annotations.IdUtils
import io.mitter.models.acolyte.AppliedAcl
import io.mitter.models.acolyte.AppliedAclList
import io.mitter.models.acolyte.emptyAclList
import io.mitter.models.mardle.accesscontrol.ReadMessagePrivilege
import io.mitter.models.mardle.accesscontrol.UserIdAccessorSelector
import io.mitter.starterapp.data.UserIdProvider

object AclUtils {
    fun onlySam(): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("sam")))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun onlyJason(): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("jason")))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun onlyKatie(): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("katie")))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun getAclListFromUsername(username: String) = when (username) {
        "sam" -> onlySam()
        "jason" -> onlyJason()
        "katie" -> onlyKatie()
        else -> emptyAclList()
    }
}
