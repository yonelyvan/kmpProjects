package com.example.dailypulse

import kotlinx.coroutines.CoroutineScope
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "BaseViewModel")
expect open class BaseViewModel() {

    val scope: CoroutineScope

}