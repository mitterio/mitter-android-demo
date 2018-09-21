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
    fun meAndSam(senderId: String): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("sam")))
                ),
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(senderId))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun meAndJason(senderId: String): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("jason")))
                ),
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(senderId))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun meAndKatie(senderId: String): AppliedAclList {
        return AppliedAclList(
            plusAppliedAcls = listOf(
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(UserIdProvider.getUserId("katie")))
                ),
                AppliedAcl
                (
                    ReadMessagePrivilege(),
                    UserIdAccessorSelector(IdUtils.of(senderId))
                )
            ),
            minusAppliedAcls = emptyList()
        )
    }

    fun getAclListFromUsername(username: String, senderId: String) = when (username) {
        "sam" -> meAndSam(senderId)
        "jason" -> meAndJason(senderId)
        "katie" -> meAndKatie(senderId)
        else -> emptyAclList()
    }
}
