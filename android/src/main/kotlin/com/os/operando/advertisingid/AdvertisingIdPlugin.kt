package com.os.operando.advertisingid

import android.os.Handler
import android.os.Looper
<<<<<<< HEAD
import com.google.android.gms.ads.identifier.AdvertisingIdClient
=======
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
>>>>>>> 034cf11eee8db5fffef8190e1ea040e31d8df036
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import kotlin.concurrent.thread

/** HelloPlugin */
public class AdvertisingIdPlugin: FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel : MethodChannel
    private lateinit var binding : FlutterPlugin.FlutterPluginBinding

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "advertising_id")
        channel.setMethodCallHandler(this);
        binding = flutterPluginBinding;
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "advertising_id")
            channel.setMethodCallHandler(AdvertisingIdPlugin())
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getAdvertisingId" -> thread {
                try {
                    val id = AdvertisingIdClient.getAdvertisingIdInfo(registrar.context()).id
                    Handler(Looper.getMainLooper()).post {
                        result.success(id)
                    }
                } catch (e: Exception) {
                    Handler(Looper.getMainLooper()).post {
<<<<<<< HEAD
                        result.error(e.javaClass.canonicalName, e.localizedMessage, null)
=======
                    result.error(e.javaClass.canonicalName, e.localizedMessage, null)
>>>>>>> 034cf11eee8db5fffef8190e1ea040e31d8df036
                    }
                }
            }
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
