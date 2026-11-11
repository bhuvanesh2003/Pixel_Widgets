package com.example.mementowidgets;
import android.content.Context;import android.content.SharedPreferences;import org.json.JSONArray;import java.util.ArrayList;import java.util.List;import java.time.LocalDate;
public class Store {
 static SharedPreferences prefs(Context c){return c.getSharedPreferences("settings",Context.MODE_PRIVATE);}
 static LocalDate dob(Context c){try{return LocalDate.parse(prefs(c).getString("dob","2003-01-01"));}catch(Exception e){return LocalDate.of(2003,1,1);}}
 static int lifespan(Context c){return prefs(c).getInt("lifespan",70);}
 static List<String> quotes(Context c){List<String> list=new ArrayList<>();try{JSONArray a=new JSONArray(prefs(c).getString("quotes","[\"The trouble is, you think you have time.\",\"Make today count.\"]"));for(int i=0;i<a.length();i++)list.add(a.getString(i));}catch(Exception ignored){}return list;}
 static void saveQuotes(Context c,List<String> quotes){prefs(c).edit().putString("quotes",new JSONArray(quotes).toString()).apply();}
 static int index(Context c,int n){if(n==0)return 0;return Math.floorMod(prefs(c).getInt("index",0),n);}
}
