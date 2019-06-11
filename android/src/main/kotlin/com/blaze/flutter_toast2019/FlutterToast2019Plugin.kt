package com.blaze.flutter_toast2019

import android.content.Context
import android.widget.Toast
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterToast2019Plugin(private val context:Context): MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      // 为了防止命名冲突，可以在它的前面加上域名
      val channel = MethodChannel(registrar.messenger(), "blaze.com/flutter_toast2019")
      //toast需要context ，所以在这里加上了context
      channel.setMethodCallHandler(FlutterToast2019Plugin(registrar.context()))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "toast") {
      // call.arguemnt() 方法支持 Map 和 JSONObject
      val content = call.argument<String>("content")
      val duration = call.argument<String>("duration")
      content?.let {
        Toast.makeText(context,it,if ("short" == duration) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
      }
      result.success(true)
    } else {
      result.notImplemented()
    }
  }
}
