package com.example.mementowidgets;
import android.appwidget.AppWidgetManager;import android.appwidget.AppWidgetProvider;import android.content.ComponentName;import android.content.Context;import android.content.Intent;import android.app.PendingIntent;import android.widget.RemoteViews;import java.util.List;
public class QuoteWidget extends AppWidgetProvider {
 static final String PREV="com.example.mementowidgets.PREV",NEXT="com.example.mementowidgets.NEXT";
 public static void refresh(Context c){AppWidgetManager m=AppWidgetManager.getInstance(c);for(int id:m.getAppWidgetIds(new ComponentName(c,QuoteWidget.class)))update(c,m,id);}
 static void update(Context c,AppWidgetManager m,int id){List<String> q=Store.quotes(c);int i=Store.index(c,q.size());RemoteViews v=new RemoteViews(c.getPackageName(),R.layout.quote_widget);v.setTextViewText(R.id.quote_text,q.isEmpty()?"Add your first quote in the app.":"“"+q.get(i)+"”");v.setTextViewText(R.id.quote_counter,q.isEmpty()?"0 / 0":(i+1)+" / "+q.size());v.setOnClickPendingIntent(R.id.quote_prev,pending(c,PREV,21));v.setOnClickPendingIntent(R.id.quote_next,pending(c,NEXT,22));v.setOnClickPendingIntent(R.id.quote_text,PendingIntent.getActivity(c,23,new Intent(c,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE));m.updateAppWidget(id,v);}
 static PendingIntent pending(Context c,String action,int code){Intent i=new Intent(c,QuoteWidget.class).setAction(action);return PendingIntent.getBroadcast(c,code,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);}
 @Override public void onReceive(Context c,Intent i){super.onReceive(c,i);String a=i.getAction();if(PREV.equals(a)||NEXT.equals(a)){int n=Store.quotes(c).size();if(n>0){int step=NEXT.equals(a)?1:-1;Store.prefs(c).edit().putInt("index",Math.floorMod(Store.index(c,n)+step,n)).apply();refresh(c);}}}
 @Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){for(int id:ids)update(c,m,id);}
}
