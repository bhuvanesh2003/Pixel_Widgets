package com.example.mementowidgets;
import android.content.BroadcastReceiver;import android.content.Context;import android.content.Intent;
public class DayReceiver extends BroadcastReceiver { @Override public void onReceive(Context c,Intent i){LifeWidget.refresh(c);} }
