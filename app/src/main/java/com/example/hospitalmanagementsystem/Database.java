package com.example.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private String users = "users";
    private String cart = "cart";
    private String orderplace = "orderplace";
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //onCreate method is use for create table

        //create table for users
        String qry1 = "create table users(username text,email,text,password text)";
        sqLiteDatabase.execSQL(qry1);

        //create table for cart
        String qry2 = "create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        //create table for order
        String qry3 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //onUpgrade method is use for upgrade the table

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + users);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + cart);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + orderplace);
        onCreate(sqLiteDatabase);
    }
    //table of users start
    public void register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);

        //for insert the data
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int login(String username,String password){
        int result = 0;
        String str[] = new  String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);

        //check there are some data exist or not
        if(c.moveToFirst()){
            result = 1;
        }
        return  result;
    }
    //table of users end

    //table of cart start
    public void addCart(String username,String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);

        //for insert the data
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    public int checkCart(String username,String product){
        int result = 0;
        String str[] = new  String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and product=?",str);

        //check there are some data exist or not
        if(c.moveToFirst()){
            result = 1;
        }
        db.close();
        return  result;
    }

    public void removeCart(String username,String otype){

        String str[] = new  String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();

        db.delete("cart","username=? and otype=?",str);
        db.close();

    }

    public ArrayList getCartData(String username,String otype){
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String str[] = new  String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username=? and otype=?",str);

        if(c.moveToFirst()){
           do {
            String product = c.getString(1);
            String price = c.getString(2);
            arrayList.add(product+"$"+price);
           }while (c.moveToNext());
        }
        db.close();
        return arrayList;

    }
    //table of cart end

    //table of orderplace start
    public void addOrder(String username,String fullname,String address,String contactno,int pincode,String date,String time,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contactno);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);

        //for insert the data
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String str[] = new  String[1];
        str[0] = username;

        Cursor c = db.rawQuery("select * from orderplace where username=?",str);

        if(c.moveToFirst()){
            do {
                arrayList.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public int checkAppintmentExists(String username,String fullname,String address,String contactno,String date,String time){
        int result = 0;
        String str[] = new  String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contactno;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username=? and fullname=? and address=? and contactno=? and  date=? and time=?",str);

        //check there are some data exist or not
        if(c.moveToFirst()){
            result = 1;
        }
        db.close();
        return  result;
    }

    //table of orderplace end

}
