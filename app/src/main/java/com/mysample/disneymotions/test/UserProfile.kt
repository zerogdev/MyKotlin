package com.mysample.disneymotions.test

import java.util.*

/**
 * 불가능한 상태를 불가능하게 만들기
 * 참고: https://dev.to/psfeng/make-impossible-states-impossible-kotlin-edition-189e
 */

//const val ACCOUNT_TYPE_BASIC = 0
//const val ACCOUNT_TYPE_PREMIUM = 1
//const val ACCOUNT_TYPE_PRIVILEGE = 2

//class UserProfile(
//    val name: String, //숫자가 있을수도있다
//    val age: Long,  //long이 될수도
//    val accountType: Int,
//    val email: String?,
//    val phoneNumber: String?,
//    val address: String?
//)

//private 로 개인생성자를 사용해서 보호
data class UserName private constructor(val name:String) {
    companion object{
        fun createUsername(name: String): UserName {
            if (name.isEmpty()) throw IllegalArgumentException()
            return UserName(name)
        }
    }
}

interface IUserName {
    val name: String

    companion object {

        fun create(name: String): IUserName {
            if (name.isEmpty()) throw IllegalArgumentException()
            return UserName2(name)
        }

        fun create2(name: String): Optional<IUserName> {
            if (name.isEmpty()) return Optional.empty() //throw 대신 optional 을 사용할 수 있음
            return Optional.of(UserName2(name))
        }

        fun create3(name: String): IUserName? {

            if (name.isEmpty()) return null //null 사용
           return UserName2(name)
        }
    }
}

private data class UserName2(override val name: String) : IUserName


/**
 * 최종 버전
 */

//타입을 enum으로 만듬
enum class AccountType {
    ACCOUNT_TYPE_BASIC,
    ACCOUNT_TYPE_PREMIUM,
    ACCOUNT_TYPE_PRIVILEGE
}

//상태를 갖는 타입형 클래스
sealed class ContactInfo
data class Email(val email: String) : ContactInfo()
data class PhoneNumber(val number: String) : ContactInfo()
data class Address(val address: String) : ContactInfo()

data class UserProfile(
    val name: UserName, //name의 유효성을 체크하면서 생성되도록 제한
    val accountType: AccountType, //유효한 값만 추가 할수 있도록 enum
    val contactInfo: List<ContactInfo> //유효한 값만 추가 할수 있도록 sealed class
)