package com.test.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class TestPlugin extends CordovaPlugin {

 @Override
 public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
  if (action.equals("coolMethod")) {
   String message = args.getString(0);
   this.coolMethod(message, callbackContext);
   return true;
  }
  if (action.equals("methodWithMultipleArguments")) {

   methodWithMultipleArguments(args, callbackContext);
   return true;
  }

  return false;
 }

 private void coolMethod(String message, CallbackContext callbackContext) {
  if (message != null && message.length() > 0) {
   callbackContext.success(message);
  } else {
   callbackContext.error("Expected one non-empty string argument.");
  }
 }

 private void methodWithMultipleArguments(JSONArray args, CallbackContext callbackContext) {
  String firstArg = args.getString(0);
  String secondArg = args.getString(1);
  Log.d(LOG_TAG, firstArg);
  //Perform some operation
  callbackContext.success();

 }

 private void listBT(CallbackContext callbackContext) {
  BluetoothAdapter mBluetoothAdapter = null;
  String errMsg = null;
  try {
   mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
   if (mBluetoothAdapter == null) {
    errMsg = "No bluetooth adapter available";
    Log.e(LOG_TAG, errMsg);
    callbackContext.error(errMsg);
    return;
   }
   if (!mBluetoothAdapter.isEnabled()) {
    Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    this.cordova.getActivity().startActivityForResult(enableBluetooth, 0);
   }
   Set < BluetoothDevice > pairedDevices = mBluetoothAdapter.getBondedDevices();
   if (pairedDevices.size() > 0) {
    JSONArray json = new JSONArray();
    for (BluetoothDevice device: pairedDevices) {
     Log.i("Second_bluetooth_connection", device.getName() + " " + device.getAddress());
     String name = device.getName();
     String mac = device.getAddress();
     JSONObject p = new JSONObject();
     p.put("name", name);
     p.put("address", mac);

     json.put(p);
    }
    callbackContext.success(json);
   } else {
    callbackContext.error("No Bluetooth Device Found");
   }
  } catch (Exception e) {
   errMsg = e.getMessage();
   Log.e(LOG_TAG, errMsg);
   e.printStackTrace();
   callbackContext.error(errMsg);
  }
 }
}