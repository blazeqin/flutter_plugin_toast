import 'dart:async';

import 'package:flutter/services.dart';

class FlutterToast2019 {
  static const MethodChannel _channel =
      const MethodChannel('blaze.com/flutter_toast2019');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('toast');
    return version;
  }

  static Future<bool> toast(String msg, ToastDuration duration) async{
    var argument = {
      'content':msg,
      'duration':duration.toString()
    };
    var success = await _channel.invokeMethod('toast',argument);
    return success;
  }
}

enum ToastDuration {
  short,long
}
