package loy.mobile.android_sdk_testing.model

import com.google.gson.annotations.SerializedName

data class GetUserProfileResponse(
    val statusCode: Int,
    val message: String,
    val data: UserModel
)
data class UserModel(
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?,
    val createdBy: String?,
    val updatedBy: String?,
    val deletedBy: String?,
    val id: String?,
    val fullName: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val isEmailVerified: Boolean?,
    val phone: String?,
    val username: String?,
    val guid: String?,
    val isAdmin: Boolean?,
    val status: String?,
    val deviceToken: String?,
    val state: String?,
    val canViewApiDetails: Boolean?,
    val type: String?,
    val realm: String?,
    val gid: String?,
    val termAndConditionDate: String?,
    val languageSetting: String?,
    val avatar: String?,
    val externalId: String?,
    val externalSrc: String?,
    val member: MemberModel?,
)

data class MemberModel(
    val createdAt: String?,
    val updatedAt: String?,
    val deletedAt: String?,
    val createdBy: String?,
    val updatedBy: String?,
    val deletedBy: String?,
    val id: String?,
    val memberCode: String?,
    val gender: String?,
    val age: Int?,
    val pin: String?,
    val passportNumber: String?,
    val creditCardNumber: String?,
    val dateOfBirth: String?,
    val memberName: String?,
    val salutation: String?,
    val middleName: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val enrollmentTouchPoint: String?,
    val enrollingSponsor: String?,
    val userId: String?,
    val citizenIdentityCard: String?,
    val passportExpireDate: String?,
    val passportIssueDate: String?,
    val driverLicenseIssueDate: String?,
    val citizenIdentityCardExpireDate: String?,
    val citizenIdentityCardIssueDate: String?,
    val country: String?,
    val homeTown: String?,
    val permanentAddress: String?,
    val placeOfIssue: String?,
    val syncedEkyc: Boolean?,
    val ekycInReview: Boolean?,
    val comment: String?,
    val referralCode: String?,
    val referralBy: String?,
    val referralAt: String?,
    val dinarMemberId: String?,
    val dinarAccountId: String?,
    val ekycStatus: String?,
    val canEditProfile: Boolean?,
    val syncStatus: String?,
    val skyPlus: String?,
    val oldRankSkyPlus: Int?,
    val memberLjiType: String?,
    val driverLicenseNumber: String?,
    val driverLicenseExpireDate: String?,
    val ekycDocumentType: String?,
)